package com.cozentus.sbms.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cozentus.sbms.domain.Blog;

@Repository
public interface BlogRepository extends JpaRepository<Blog, Long>{

}
