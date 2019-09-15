package com.work.management.repository;

import com.work.management.entity.Teams;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface TeamRepository extends JpaRepository<Teams,String> {

    Optional<Teams> findByName(String name);
    Optional<Teams> findById(String id);

}
