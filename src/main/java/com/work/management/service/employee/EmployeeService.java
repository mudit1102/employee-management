package com.work.management.service.employee;

import com.google.common.collect.ImmutableList;
import com.work.management.dto.BulkEmployeeDto;
import com.work.management.dto.EmployeeDto;
import com.work.management.entity.Employee;

public interface EmployeeService {

  EmployeeDto save(EmployeeDto employeeDto);

  EmployeeDto getEmployeeByUserName(String username);

  EmployeeDto updateEmployeeEntity(EmployeeDto employeeDto);

  ImmutableList<Employee> bulkUpdate(BulkEmployeeDto bulkEmployeeDto);
}
