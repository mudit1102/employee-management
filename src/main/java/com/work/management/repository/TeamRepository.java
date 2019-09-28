package com.work.management.repository;

import com.work.management.entity.Team;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;


public interface TeamRepository extends JpaRepository<Team, String> {

  Optional<Team> findByName(String name);

  Optional<Team> findById(String id);

}
