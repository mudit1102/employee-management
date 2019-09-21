package com.work.management.web.rest.controller;

import com.work.management.dto.ProjectDto;
import com.work.management.entity.Project;
import com.work.management.service.project.ProjectService;
import com.work.management.web.rest.assembler.ProjectAssembler;
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
final class ProjectController {

    private final ProjectService<ProjectDto, Project> projectService;

    @Autowired
    ProjectController(ProjectService projectService) {
        this.projectService = projectService;
    }

    @RequestMapping(method = RequestMethod.POST, value = "/", produces = "application/json; charset=UTF-8")
    @ApiOperation(value = "Create a project")
    public ResponseEntity<ProjectResource> registerProject(@Valid @RequestBody @ApiParam("Project Entity") ProjectResource projectResource) {
        projectService.save(ProjectAssembler.convert(projectResource));
        return new ResponseEntity<>(projectResource, HttpStatus.CREATED);
    }
}
