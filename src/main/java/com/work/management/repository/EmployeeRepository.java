package com.work.management.repository;

import com.work.management.entity.Employees;
import com.work.management.entity.Projects;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface EmployeeRepository extends JpaRepository<Employees,Integer> {
    Optional<Employees> findById(Integer id);
    Optional<Employees> findByPhoneNumber(String phoneNumber);
    Optional<Employees> findByUserName(String userName);
}
