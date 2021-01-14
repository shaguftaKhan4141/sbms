package com.cozentus.sbms.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cozentus.sbms.domain.Topic;

@Repository
public interface TopicRepository extends JpaRepository<Topic, Long>{

}
