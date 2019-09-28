package com.work.management.web.rest.assembler;

import com.work.management.dto.TeamDto;
import com.work.management.web.rest.resource.TeamResource;
import org.springframework.beans.BeanUtils;

public final class TeamAssembler {

  public static TeamDto convert(TeamResource teamResource) {
    TeamDto teamDto = new TeamDto();
    BeanUtils.copyProperties(teamResource, teamDto);
    return teamDto;
  }
}
