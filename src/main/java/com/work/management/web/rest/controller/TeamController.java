package com.work.management.web.rest.controller;

import com.work.management.service.team.TeamService;
import com.work.management.web.rest.assembler.TeamAssembler;
import com.work.management.web.rest.resource.TeamResource;
import com.work.management.web.rest.resource.TeamResponse;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
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
@RequestMapping("/workmanagement/v1/teams")
final class TeamController {

  private final TeamService teamService;

  @Autowired
  TeamController(TeamService teamService) {
    this.teamService = teamService;
  }

  @RequestMapping(method = RequestMethod.POST, value = "/register", produces = "application/json; charset=UTF-8")
  @ApiOperation(value = "Create a Team", response = TeamResource.class)
  ResponseEntity<TeamResource> registerTeams(
      @Valid @RequestBody @ApiParam(value = "Team Entity", required = true) TeamResource teamResource) {
    teamService.save(TeamAssembler.convert(teamResource));
    return new ResponseEntity<>(teamResource, HttpStatus.CREATED);
  }

  @RequestMapping(method = RequestMethod.GET, value = "/{name}", produces = "application/json; charset=UTF-8")
  @ApiOperation(value = "Get Team info by name", response = TeamResponse.class)
  ResponseEntity<TeamResponse> getTeamInfoByName(
      @Valid @ApiParam(value = "Team Name", required = true) @PathVariable("name") String name) {
    return new ResponseEntity<>(TeamAssembler.convert(teamService.getTeamInfoByName(name)),
        HttpStatus.OK);
  }
}
