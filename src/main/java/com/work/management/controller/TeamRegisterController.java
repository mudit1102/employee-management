package com.work.management.controller;

import com.work.management.resource.TeamResource;
import com.work.management.service.impl.TeamRegistrationService;
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
@RequestMapping(value ="workmanagement/v1/teams")
public class TeamRegisterController {

    @Autowired
    private TeamRegistrationService teamRegistrationService;

    @RequestMapping(method = RequestMethod.POST,value = "/",produces = "application/json; charset=UTF-8")
    @ApiOperation(value="Create a team")
    public ResponseEntity<TeamResource> registerTeams(@Valid @RequestBody TeamResource teamResource){
        teamRegistrationService.process(teamResource);
        return new ResponseEntity<>(teamResource, HttpStatus.CREATED);
    }
}
