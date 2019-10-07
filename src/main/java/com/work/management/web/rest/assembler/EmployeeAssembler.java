package com.work.management.web.rest.assembler;

import com.work.management.dto.BulkEmployeeDto;
import com.work.management.dto.BulkEmployeeDtoResp;
import com.work.management.dto.EmployeeDto;
import com.work.management.web.rest.resource.BulkEmployeeRequest;
import com.work.management.web.rest.resource.BulkEmployeeResponse;
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

  public static BulkEmployeeResponse convert(BulkEmployeeDtoResp bulkEmployeeDtoResp) {
    BulkEmployeeResponse bulkEmployeeResponse = new BulkEmployeeResponse();
    BeanUtils.copyProperties(bulkEmployeeDtoResp, bulkEmployeeResponse);
    return bulkEmployeeResponse;
  }

  public static BulkEmployeeDto convert(BulkEmployeeRequest bulkEmployeeRequest) {
    BulkEmployeeDto bulkEmployeeDto = new BulkEmployeeDto();
    BeanUtils.copyProperties(bulkEmployeeRequest, bulkEmployeeDto);
    return bulkEmployeeDto;
  }
}
