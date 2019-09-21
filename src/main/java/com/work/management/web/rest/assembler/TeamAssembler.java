package com.work.management.web.rest.assembler;

import com.google.common.collect.ImmutableList;
import com.work.management.dto.TeamDto;
import com.work.management.entity.Team;
import com.work.management.web.rest.resource.TeamResource;

public final class TeamAssembler {
    public static TeamDto convert(TeamResource teamResource) {
        TeamDto teamDto = TeamDto.builder().manager(teamResource.getManager()).name(teamResource.getName()).employeeIds(ImmutableList.<String>builder().addAll(teamResource.getEmployeeIds()).build())
                .build();
        return teamDto;
    }
}
