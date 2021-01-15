package com.cozentus.sbms.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.cozentus.sbms.domain.User;

@Repository
public interface BlogUserRepository extends JpaRepository<User, Long> {

	public boolean existsByEmailId(String email);

	public boolean existsByUserName(String userName);

	public boolean existsBycontactNo(String contactNo);
	
	public User findByUserName(String userName);
	
	public List<User> findByRoleId(Long roleId);
	
	public List<User> findByStatus(String requestStatus);
	
	@Query("Select u FROM User u WHERE u.role.id = 2 OR u.role.id = 3")
	public List<User> findAllUsers();
}
