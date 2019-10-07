package com.work.management.service.employee;

import com.work.management.dto.BulkEmployeeDto;
import com.work.management.dto.BulkEmployeeDtoResp;
import com.work.management.dto.EmployeeDto;

public interface EmployeeService {

  EmployeeDto save(EmployeeDto employeeDto);

  EmployeeDto getEmployeeByUserName(String username);

  EmployeeDto updateEmployeeEntity(EmployeeDto employeeDto);

  BulkEmployeeDtoResp bulkUpdate(BulkEmployeeDto bulkEmployeeDto);
}
