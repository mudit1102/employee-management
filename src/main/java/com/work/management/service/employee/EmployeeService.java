package com.work.management.service.employee;

import com.work.management.dto.EmployeeDto;

public interface EmployeeService {

  EmployeeDto save(EmployeeDto employeeDto);

  EmployeeDto getEmployeeByUserName(String username);

  EmployeeDto updateEmployeeEntity(EmployeeDto employeeDto);
}
