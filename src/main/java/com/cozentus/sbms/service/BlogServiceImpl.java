package com.cozentus.sbms.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cozentus.sbms.authentication.IAuthenticationFacade;
import com.cozentus.sbms.domain.Blog;
import com.cozentus.sbms.dto.BlogRequestDto;
import com.cozentus.sbms.dto.BlogResponseDto;
import com.cozentus.sbms.enumeration.BlogStatus;
import com.cozentus.sbms.enumeration.Role;
import com.cozentus.sbms.error.InvalidDataException;
import com.cozentus.sbms.error.NotFoundException;
import com.cozentus.sbms.error.UserNotAuthorizedException;
import com.cozentus.sbms.mapper.BlogMapper;
import com.cozentus.sbms.repository.BlogRepository;
import com.cozentus.sbms.util.CommonUtils;

@Service
public class BlogServiceImpl implements BlogService {

	@Autowired
	private BlogRepository blogRepository;

	@Autowired
	private IAuthenticationFacade authenticationFacade;
	
	@Override
	public BlogResponseDto getBlog(Long blogId) throws NotFoundException {
		Blog blog = blogRepository.findById(blogId)
				.orElseThrow(() -> new NotFoundException("No Blog found for id : " + blogId));
		return BlogMapper.blogToBlogResponseDto(blog);
	}

	@Override
	public BlogResponseDto createBlog(BlogRequestDto blogDto) {
		Blog blog = BlogMapper.blogRequestDtoToBlogForCreate(blogDto, authenticationFacade.getUserName());
		return BlogMapper.blogToBlogResponseDto(blogRepository.save(blog));
	}

	// this does not updates the status of Blog
	@Override
	public BlogResponseDto updateBlog(BlogRequestDto blogDto, Long id) throws NotFoundException {

		Blog blog = blogRepository.findById(id)
				.orElseThrow(() -> new NotFoundException("No Blog found for id : " + id));
		Blog updatedBlog = BlogMapper.blogRequestDtoToBlogForUpdate(blog, blogDto, authenticationFacade.getUserName());

		return BlogMapper.blogToBlogResponseDto(blogRepository.save(updatedBlog));
	}

	@Override
	public void updateBlogStatus(Long blogId, String status) throws NotFoundException, UserNotAuthorizedException, InvalidDataException {
        
		if(CommonUtils.isInEnum(status, BlogStatus.class)) {
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

	}

	@Override
	public void deleteBlog(Long id) throws NotFoundException {

		if (!blogRepository.existsById(id)) {
			throw new NotFoundException("No Blog found for id : " + id);
		}

		blogRepository.deleteById(id);
	}
}
