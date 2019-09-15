package com.work.management.controller;

import com.work.management.resource.ProjectResource;
import com.work.management.service.impl.ProjectRegistrationService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("workmanagement/v1/projects")
public class ProjectRegisterController {

    @Autowired
    private ProjectRegistrationService projectCreationService;

    @RequestMapping(method = RequestMethod.POST,value = "/",produces = "application/json; charset=UTF-8")
    @ApiOperation(value = "Create a project")
    public ResponseEntity<ProjectResource>  registerProject(@Valid @RequestBody @ApiParam("Project Entity") ProjectResource projectResource){
        projectCreationService.process(projectResource);
        return new ResponseEntity<>(projectResource, HttpStatus.CREATED);
    }
}
