package com.work.management.web.rest.controller;

import com.work.management.entity.Employee;
import com.work.management.service.employee.EmployeeService;
import com.work.management.web.rest.assembler.EmployeeAssembler;
import com.work.management.web.rest.resource.BulkEmployeeRequest;
import com.work.management.web.rest.resource.EmployeeResource;
import com.work.management.web.rest.resource.EmployeeResponse;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import java.util.List;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/workmanagement/v1/employees")
final class EmployeeController {

  private final EmployeeService employeeService;

  @Autowired
  EmployeeController(EmployeeService employeeService) {
    this.employeeService = employeeService;
  }

  @RequestMapping(method = RequestMethod.POST, value = "/register", produces = "application/json; charset=UTF-8")
  @ApiOperation(value = "Create an Employee", response = EmployeeResource.class)
  ResponseEntity<EmployeeResponse> registerEmployee(
      @Valid @RequestBody @ApiParam(value = "Employee Entity", required = true) EmployeeResource employeeResource) {

    return new ResponseEntity<>(EmployeeAssembler
        .convert(employeeService.save(EmployeeAssembler.convert(employeeResource))),
        HttpStatus.CREATED);
  }

  @RequestMapping(method = RequestMethod.GET, value = "/{userName}", produces = "application/json; charset=UTF-8")
  @ApiOperation(value = "Get an employee by its user name", response = EmployeeResponse.class)
  ResponseEntity<EmployeeResponse> getEmployeeByUserName(
      @Valid @ApiParam(value = "User Name", required = true) @PathVariable("userName") String userName) {
    return new ResponseEntity<>(
        EmployeeAssembler.convert(employeeService.getEmployeeByUserName(userName)), HttpStatus.OK);
  }

  @RequestMapping(method = RequestMethod.PUT, value = "/update", produces = "application/json; charset=UTF-8")
  @ApiOperation(value = "Update existing employee details", response = EmployeeResponse.class)
  ResponseEntity<EmployeeResponse> updateEmployeeDetails(
      @Valid @RequestBody @ApiParam(value = "Update employee details", required = true) EmployeeResource employeeResource) {
    employeeService.updateEmployeeEntity(EmployeeAssembler.convert(employeeResource));
    return new ResponseEntity<>(EmployeeAssembler
        .convert(employeeService.updateEmployeeEntity(EmployeeAssembler.convert(employeeResource))),
        HttpStatus.OK);
  }

  @RequestMapping(method = RequestMethod.PUT, value = "/bulkUpdate", produces = "application/json; charset=UTF-8")
  @ApiOperation(value = "Bulk updates on employees details")
  ResponseEntity<List<Employee>> employeeBulkUpdate(
      @Valid @RequestBody @ApiParam(value = "Bulk update on employee details", required = true) BulkEmployeeRequest bulkEmployeeRequest) {
    return new ResponseEntity<>(
        employeeService.bulkUpdate(EmployeeAssembler.convert(bulkEmployeeRequest)),
        HttpStatus.OK);
  }
}
