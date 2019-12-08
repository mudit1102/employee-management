package com.work.management.web.rest.assembler;

import com.work.management.dto.TeamDto;
import com.work.management.web.rest.resource.TeamResource;
import com.work.management.web.rest.resource.TeamResponse;
import org.springframework.beans.BeanUtils;

public final class TeamAssembler {

  public static TeamDto convert(TeamResource teamResource) {
    TeamDto teamDto = new TeamDto();
    BeanUtils.copyProperties(teamResource, teamDto);
    return teamDto;
  }

  public static TeamResponse convert(TeamDto teamDto) {
    TeamResponse teamResponse = new TeamResponse();
    BeanUtils.copyProperties(teamDto, teamResponse);
    return teamResponse;
  }
}
