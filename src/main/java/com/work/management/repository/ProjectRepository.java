package com.work.management.repository;

import com.work.management.entity.Projects;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;


public interface ProjectRepository  extends JpaRepository<Projects,String>{
    Optional<Projects> findById(String id);
    Optional<Projects> findByName(String name);
}
