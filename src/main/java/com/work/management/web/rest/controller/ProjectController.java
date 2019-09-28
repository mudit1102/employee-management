package com.work.management.web.rest.controller;

import com.work.management.service.project.ProjectService;
import com.work.management.web.rest.assembler.ProjectAssembler;
import com.work.management.web.rest.resource.ProjectResource;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("workmanagement/v1/projects")
final class ProjectController {

  private final ProjectService projectService;

  @Autowired
  ProjectController(ProjectService projectService) {
    this.projectService = projectService;
  }

  @RequestMapping(method = RequestMethod.POST, value = "/register", produces = "application/json; charset=UTF-8")
  @ApiOperation(value = "Create a project")
  public ResponseEntity<ProjectResource> registerProject(
      @Valid @RequestBody @ApiParam("Project Entity") ProjectResource projectResource) {
    projectService.save(ProjectAssembler.convert(projectResource));
    return new ResponseEntity<>(projectResource, HttpStatus.CREATED);
  }
}
