package com.work.management.repository;

import com.work.management.entity.Project;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;


public interface ProjectRepository extends JpaRepository<Project, String> {

  Optional<Project> findById(String id);

  Optional<Project> findByName(String name);

}
