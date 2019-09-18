package com.work.management.web.rest.controller;

import com.work.management.web.rest.resource.ProjectResource;
import com.work.management.service.impl.ProjectService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("workmanagement/v1/projects")
public class ProjectController {


    private final ProjectService projectCreationService;

    @Autowired
    public ProjectController(ProjectService projectCreationService) {
        this.projectCreationService = projectCreationService;
    }

    @RequestMapping(method = RequestMethod.POST, value = "/", produces = "application/json; charset=UTF-8")
    @ApiOperation(value = "Create a project")
    public ResponseEntity<ProjectResource> registerProject(@Valid @RequestBody @ApiParam("Project Entity") ProjectResource projectResource) {
        projectCreationService.process(projectResource);
        return new ResponseEntity<>(projectResource, HttpStatus.CREATED);
    }
}
