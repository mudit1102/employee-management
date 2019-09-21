package com.work.management.repository;

import com.work.management.entity.Team;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;


public interface TeamRepository extends JpaRepository<Team, String> {

    Optional<Team> findByName(String name);

    Optional<Team> findById(String id);

}
