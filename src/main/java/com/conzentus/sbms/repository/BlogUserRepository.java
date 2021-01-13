package com.conzentus.sbms.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.conzentus.sbms.domain.User;

@Repository
public interface BlogUserRepository extends JpaRepository<User, Long> {

}
