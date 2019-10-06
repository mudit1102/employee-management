package com.work.management.web.rest.assembler;

import com.work.management.dto.EmployeeDto;
import com.work.management.web.rest.resource.EmployeeResource;
import org.springframework.beans.BeanUtils;

public final class EmployeeAssembler {

  public static EmployeeDto convert(EmployeeResource employeeResource) {
    EmployeeDto employeeDto = new EmployeeDto();
    BeanUtils.copyProperties(employeeResource, employeeDto);
    return employeeDto;

  }

  public static EmployeeResource convert(EmployeeDto employeeDto) {
    EmployeeResource employeeResource = new EmployeeResource();
    BeanUtils.copyProperties(employeeDto, employeeResource);
    return employeeResource;
  }
}
