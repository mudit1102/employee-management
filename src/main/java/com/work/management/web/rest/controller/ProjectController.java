package com.work.management.web.rest.controller;

import com.work.management.entity.Project;
import com.work.management.service.DataPersistingService;
import com.work.management.web.rest.resource.EmployeeResource;
import com.work.management.web.rest.resource.ProjectResource;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("workmanagement/v1/projects")
public final class ProjectController {

    private final DataPersistingService<ProjectResource, Project> dataPersistingService;

    @Autowired
    public ProjectController(DataPersistingService dataPersistingService) {
        this.dataPersistingService = dataPersistingService;
    }

    @RequestMapping(method = RequestMethod.POST, value = "/", produces = "application/json; charset=UTF-8")
    @ApiOperation(value = "Create a project")
    public ResponseEntity<ProjectResource> registerProject(@Valid @RequestBody @ApiParam("Project Entity") ProjectResource projectResource) {
        dataPersistingService.process(projectResource);
        return new ResponseEntity<>(projectResource, HttpStatus.CREATED);
    }
}
