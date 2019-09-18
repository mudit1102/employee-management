package com.work.management.repository;

import com.work.management.entity.Project;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;


public interface ProjectRepository  extends JpaRepository<Project,String> {
    Optional<Project> findById(String id);
    Optional<Project> findByName(String name);
}
