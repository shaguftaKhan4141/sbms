package com.cozentus.sbms.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cozentus.sbms.domain.Role;

public interface RoleRepository extends JpaRepository<Role, Long> {

	public Role findByName(String name);
	
}
