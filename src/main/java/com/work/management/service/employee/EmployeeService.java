package com.work.management.service.employee;

import com.work.management.dto.BulkEmployeeDto;
import com.work.management.dto.EmployeeDocumentDto;
import com.work.management.dto.EmployeeDto;
import com.work.management.dto.FilterEmployeeDto;
import com.work.management.entity.Employee;
import java.util.List;

public interface EmployeeService {

  EmployeeDto save(EmployeeDto employeeDto);

  EmployeeDto getEmployeeByUserName(String username);

  EmployeeDto updateEmployeeEntity(EmployeeDto employeeDto);

  List<Employee> bulkUpdate(BulkEmployeeDto bulkEmployeeDto);

  String createDocument(final EmployeeDocumentDto employeeDocumentDto);

  List<EmployeeDocumentDto> filterEmployeeDocument(final FilterEmployeeDto filterEmployeeDto);
}
