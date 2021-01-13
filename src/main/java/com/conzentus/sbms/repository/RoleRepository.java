package com.conzentus.sbms.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.conzentus.sbms.domain.Role;

public interface RoleRepository extends JpaRepository<Role, Long> {

	public Role findByName(String name);
}
