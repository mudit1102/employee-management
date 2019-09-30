package com.work.management.service.employee;

import com.work.management.dto.EmployeeDto;

public interface EmployeeService {

  void save(com.work.management.dto.EmployeeDto document);

  EmployeeDto getEmployeeByUserName(String username);
}
