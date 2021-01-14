package com.cozentus.sbms.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cozentus.sbms.domain.User;

@Repository
public interface BlogUserRepository extends JpaRepository<User, Long> {

	public boolean existsByEmailId(String email);

	public boolean existsByUserName(String userName);

	public boolean existsBycontactNo(String contactNo);
	
	public List<User> findByRoleId(Long roleId);
}
