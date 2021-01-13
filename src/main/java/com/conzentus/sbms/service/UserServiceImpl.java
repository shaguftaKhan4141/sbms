package com.conzentus.sbms.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.conzentus.sbms.domain.User;
import com.conzentus.sbms.repository.BlogUserRepository;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	BlogUserRepository userRepository;

	@Override
	public User signupUser(User user) {
		return userRepository.save(user);
	}
}
