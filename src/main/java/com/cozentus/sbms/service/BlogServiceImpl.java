package com.cozentus.sbms.service;

import java.io.IOException;
import java.util.List;
import java.util.Set;
import java.util.concurrent.Flow.Publisher;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.cozentus.sbms.authentication.IAuthenticationFacade;
import com.cozentus.sbms.domain.Blog;
import com.cozentus.sbms.domain.Topic;
import com.cozentus.sbms.domain.User;
import com.cozentus.sbms.dto.BlogRequestDto;
import com.cozentus.sbms.dto.BlogResponseDto;
import com.cozentus.sbms.enumeration.BlogStatus;
import com.cozentus.sbms.enumeration.Role;
import com.cozentus.sbms.error.InvalidDataException;
import com.cozentus.sbms.error.NotFoundException;
import com.cozentus.sbms.error.UserNotAuthorizedException;
import com.cozentus.sbms.event.EventData;
import com.cozentus.sbms.mapper.BlogMapper;
import com.cozentus.sbms.repository.BlogRepository;
import com.cozentus.sbms.repository.BlogUserRepository;
import com.cozentus.sbms.repository.TopicRepository;
import com.cozentus.sbms.util.CommonUtils;

@Service
public class BlogServiceImpl implements BlogService {

	@Autowired
	private BlogRepository blogRepository;
	
	@Autowired
	private TopicRepository topicRepository;
	
	@Autowired
	private BlogUserRepository blogUserRepository;

	@Autowired
	private IAuthenticationFacade authenticationFacade;
	
	@Autowired
	AwsService awsService;
	
	@Autowired
    private ApplicationEventPublisher  publisher;
	
	@Override
	public BlogResponseDto getBlog(Long blogId) throws NotFoundException {
		Blog blog = blogRepository.findById(blogId)
				.orElseThrow(() -> new NotFoundException("No Blog found for id : " + blogId));
		return BlogMapper.blogToBlogResponseDto(blog);
	}

	@Override
	public BlogResponseDto createBlog(BlogRequestDto blogDto) throws NotFoundException {
		Topic topic = topicRepository.findById(blogDto.getTopicId())
				.orElseThrow(() -> new NotFoundException("No Topic found for id : " + blogDto.getTopicId()));
		
		Blog blog = BlogMapper.blogRequestDtoToBlogForCreate(blogDto, topic, authenticationFacade.getUserName());

		return BlogMapper.blogToBlogResponseDto(blogRepository.save(blog));
	}
	
	@Override
	public void saveOrUpdateDocument(MultipartFile file, Long id) throws NotFoundException, IOException {
		
		Blog blog = blogRepository.findById(id)
				.orElseThrow(() -> new NotFoundException("No Blog found for id : " + id));
		
		if(blog.getStatus().equals(BlogStatus.SYNOPSIS_APPROVED.toString())) {
			blog.setBlogLink(awsService.save(file));
		}
		
		blogRepository.save(blog);
		Set<User> subscribers = blog.getTopic().getSubscriber();
		User author = blogUserRepository.findById(blog.getAuthorId())
				.orElseThrow(() -> new NotFoundException("No Author found for id : " + blog.getAuthorId()));
		
		subscribers.forEach(subscriber -> publisher.publishEvent(new EventData(subscriber.getUserName(), author.getUserName(), subscriber.getEmailId(), file)));
	}

	// this does not updates the status of Blog
	@Override
	public BlogResponseDto updateBlog(BlogRequestDto blogDto, Long id) throws NotFoundException {

		Blog blog = blogRepository.findById(id)
				.orElseThrow(() -> new NotFoundException("No Blog found for id : " + id));
		Topic topic = topicRepository.findById(blogDto.getTopicId())
				.orElseThrow(() -> new NotFoundException("No Topic found for id : " + blogDto.getTopicId()));
		
		Blog updatedBlog = BlogMapper.blogRequestDtoToBlogForUpdate(blog, topic, blogDto, authenticationFacade.getUserName());

		return BlogMapper.blogToBlogResponseDto(blogRepository.save(updatedBlog));
	}

	@Override
	public void updateBlogStatus(Long blogId, String status) throws NotFoundException, UserNotAuthorizedException, InvalidDataException {
        
		if(!CommonUtils.isInEnum(status, BlogStatus.class)) {
			throw new InvalidDataException("Invalid Blog status!");
		}
		
		Blog blog = blogRepository.findById(blogId)
				.orElseThrow(() -> new NotFoundException("No Blog found for id : " + blogId));

		boolean hasAuthority = status.equals(BlogStatus.SYNOPSIS_APPROVED.toString())
				&& authenticationFacade.hasAuthority(Role.ADMIN);

		if (!hasAuthority) {
			throw new UserNotAuthorizedException();
		}

		blog.setStatus(BlogStatus.valueOf(status).toString());
		blogRepository.save(blog);

	}

	@Override
	public void deleteBlog(Long id) throws NotFoundException {

		if (!blogRepository.existsById(id)) {
			throw new NotFoundException("No Blog found for id : " + id);
		}

		blogRepository.deleteById(id);
	}
}
