package com.work.management.web.rest.controller;

import com.work.management.entity.Team;
import com.work.management.service.DataPersistingService;
import com.work.management.web.rest.resource.TeamResource;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping(value = "workmanagement/v1/teams")
public final class TeamController {

    private final DataPersistingService<TeamResource, Team> dataPersistingService;

    @Autowired
    public TeamController(DataPersistingService dataPersistingService) {
        this.dataPersistingService = dataPersistingService;
    }

    @RequestMapping(method = RequestMethod.POST, value = "/", produces = "application/json; charset=UTF-8")
    @ApiOperation(value = "Create a team")
    public ResponseEntity<TeamResource> registerTeams(@Valid @RequestBody TeamResource teamResource) {
        dataPersistingService.process(teamResource);
        return new ResponseEntity<>(teamResource, HttpStatus.CREATED);
    }
}
