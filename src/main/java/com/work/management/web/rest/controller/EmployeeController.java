package com.work.management.web.rest.controller;

import com.work.management.entity.Employee;
import com.work.management.service.DataPersistingService;
import com.work.management.web.rest.resource.EmployeeResource;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping(value = "workmanagement/v1/employees")
public final class EmployeeController {

    private final com.work.management.service.DataPersistingService<EmployeeResource, Employee> DataPersistingService;

    @Autowired
    public EmployeeController(DataPersistingService dataPersistingService) {
        DataPersistingService = dataPersistingService;
    }

    @RequestMapping(method = RequestMethod.POST, value = "/", produces = "application/json; charset=UTF-8")
    @ApiOperation(value = "Create a project")
    public ResponseEntity<EmployeeResource> registerEmployee(@Valid @RequestBody @ApiParam("Project Entity") EmployeeResource employeeResource) {
        DataPersistingService.process(employeeResource);
        return new ResponseEntity<>(employeeResource, HttpStatus.CREATED);
    }
}
