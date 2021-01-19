package com.conzentus.sbms.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import com.conzentus.sbms.TestData;
import com.cozentus.sbms.authentication.AuthenticationFacade;
import com.cozentus.sbms.domain.Blog;
import com.cozentus.sbms.dto.BlogRequestDto;
import com.cozentus.sbms.dto.BlogResponseDto;
import com.cozentus.sbms.error.NotFoundException;
import com.cozentus.sbms.repository.BlogRepository;
import com.cozentus.sbms.service.BlogServiceImpl;


@ExtendWith(MockitoExtension.class)
public class BlogServiceTest {

    @Mock
    private BlogRepository blogRepository;
    
	@Mock
	AuthenticationFacade authenticationFacade;

    @InjectMocks
    private BlogServiceImpl blogService;
    
    @Test
	void findBlogById() throws NotFoundException {
    	Blog mockBlog = TestData.getMockBlog();
    	when(blogRepository.findById(Mockito.any(Long.class))).thenReturn(Optional.of(mockBlog));
    	BlogResponseDto response = blogService.getBlog(1L);
    	assertEquals(mockBlog.getAuthorId(), response.getAuthorId());
    }
    
    @Test
	void createblogTest() {
    	Blog mockBlog = TestData.getMockBlog();
    	when(blogRepository.save(Mockito.any(Blog.class))).thenReturn(mockBlog);
    	when(authenticationFacade.getUserName()).thenReturn("test");
    	BlogResponseDto response = blogService.createBlog(TestData.getMockBlogRequestDto());

    	assertEquals(mockBlog.getAuthorId(), response.getAuthorId());  	
    }
    

    
    @Test
    void deleteById() throws NotFoundException {
    	when(blogRepository.existsById(Mockito.any(Long.class))).thenReturn(true);
    	blogService.deleteBlog(1L);
		Mockito.verify(blogRepository, Mockito.times(1)).deleteById(Mockito.any(Long.class));
    }
}
