package com.work.management.web.rest.controller;

import com.work.management.service.team.TeamService;
import com.work.management.web.rest.assembler.TeamAssembler;
import com.work.management.web.rest.resource.TeamResource;
import io.swagger.annotations.ApiOperation;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("workmanagement/v1/teams")
final class TeamController {

  private final TeamService teamService;

  @Autowired
  TeamController(TeamService teamService) {
    this.teamService = teamService;
  }

  @RequestMapping(method = RequestMethod.POST, value = "/register", produces = "application/json; charset=UTF-8")
  @ApiOperation(value = "Create a team")
  public ResponseEntity<TeamResource> registerTeams(@Valid @RequestBody TeamResource teamResource) {
    teamService.save(TeamAssembler.convert(teamResource));
    return new ResponseEntity<>(teamResource, HttpStatus.CREATED);
  }
}
