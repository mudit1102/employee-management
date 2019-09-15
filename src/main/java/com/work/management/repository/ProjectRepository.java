package com.work.management.repository;

import com.work.management.entity.Projects;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ProjectRepository  extends JpaRepository<Projects,String>{
    Optional<Projects> findById(String id);
    Optional<Projects> findByName(String name);
}
