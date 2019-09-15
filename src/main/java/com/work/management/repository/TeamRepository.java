package com.work.management.repository;

import com.work.management.entity.Teams;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface TeamRepository extends JpaRepository<Teams,String> {

    Optional<Teams> findByName(String name);
    Optional<Teams> findById(String id);

}
