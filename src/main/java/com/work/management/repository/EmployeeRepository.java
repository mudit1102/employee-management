package com.work.management.repository;

import com.work.management.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;


public interface EmployeeRepository extends JpaRepository<Employee, Integer> {

    Optional<Employee> findById(Integer id);

    Optional<Employee> findByPhoneNumber(String phoneNumber);

    Optional<Employee> findByUserName(String userName);
}
