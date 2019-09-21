package com.work.management.service.employee;

import com.work.management.dto.EmployeeDto;

public interface EmployeeService<EmployeeDto, Employee> {
    void save(com.work.management.dto.EmployeeDto document);
}
