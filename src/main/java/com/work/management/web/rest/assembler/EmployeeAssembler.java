package com.work.management.web.rest.assembler;

import com.work.management.dto.BulkEmployeeDto;
import com.work.management.dto.EmployeeDocumentDto;
import com.work.management.dto.EmployeeDto;
import com.work.management.dto.FilterEmployeeDto;
import com.work.management.web.rest.resource.BulkEmployeeRequest;
import com.work.management.web.rest.resource.EmployeeDocumentResource;
import com.work.management.web.rest.resource.EmployeeResource;
import com.work.management.web.rest.resource.EmployeeResponse;
import com.work.management.web.rest.resource.FilterEmployeeRequest;
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

  public static EmployeeDocumentDto convert(EmployeeDocumentResource employeeDocumentResource) {
    EmployeeDocumentDto employeeDocumentDto = new EmployeeDocumentDto();
    BeanUtils.copyProperties(employeeDocumentResource, employeeDocumentDto);
    return employeeDocumentDto;
  }

  public static FilterEmployeeDto convert(FilterEmployeeRequest filterEmployeeRequest) {
    FilterEmployeeDto filterEmployeeDto = new FilterEmployeeDto();
    BeanUtils.copyProperties(filterEmployeeRequest, filterEmployeeDto);
    return filterEmployeeDto;
  }
}
