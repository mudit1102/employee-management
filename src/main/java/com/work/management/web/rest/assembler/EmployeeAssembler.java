package com.work.management.web.rest.assembler;

import com.work.management.dto.BulkEmployeeDto;
import com.work.management.dto.EmployeeDto;
import com.work.management.entity.Employee;
import com.work.management.web.rest.resource.BulkEmployeeRequest;
import com.work.management.web.rest.resource.EmployeeResource;
import com.work.management.web.rest.resource.EmployeeResponse;
import org.springframework.beans.BeanUtils;

public final class EmployeeAssembler {

  public static EmployeeDto convert(EmployeeResource employeeResource) {
    EmployeeDto employeeDto = new EmployeeDto();
    BeanUtils.copyProperties(employeeResource, employeeDto);
    return employeeDto;

  }

  public static EmployeeResponse convert(EmployeeDto employeeDto) {
    EmployeeResponse employeeResponse = new EmployeeResponse();
    BeanUtils.copyProperties(employeeDto, employeeResponse);
    return employeeResponse;
  }

  public static BulkEmployeeDto convert(BulkEmployeeRequest bulkEmployeeRequest) {
    BulkEmployeeDto bulkEmployeeDto = new BulkEmployeeDto();
    BeanUtils.copyProperties(bulkEmployeeRequest, bulkEmployeeDto);
    return bulkEmployeeDto;
  }

  public static EmployeeDto convert(Employee employee) {
    EmployeeDto employeeDto = new EmployeeDto();
    BeanUtils.copyProperties(employee, employeeDto);
    return employeeDto;
  }
}
