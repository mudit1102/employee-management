package com.work.management.service.team.impl;

import com.work.management.dto.TeamDto;
import com.work.management.entity.Team;
import com.work.management.repository.TeamRepository;
import com.work.management.service.team.TeamService;
import com.work.management.utils.ExceptionUtils;
import com.work.management.web.rest.resource.TeamResource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Objects;

@Service
final class TeamServiceImpl implements TeamService<TeamDto, Team> {

    private static final Logger LOGGER = LoggerFactory.getLogger(TeamServiceImpl.class);
    private final TeamRepository teamRepository;

    @Autowired
    TeamServiceImpl(TeamRepository teamRepository) {
        this.teamRepository = teamRepository;
    }

    @Override
    public void save(TeamDto teamDto) {
        LOGGER.info("Saving team{}", teamDto.getName());
        if (Objects.nonNull(teamRepository.findByName(teamDto.getName()))) {
            ExceptionUtils.throwEntityAlreadyExistsException("Team already exists with this name,choose a different name");
            return;
        }

        Team team = Team.builder().name(teamDto.getName()).manager(teamDto.getManager())
                .createdBy(teamDto.getManager()).lastUpdatedBy(teamDto.getManager()).employeeIds(teamDto.getEmployeeIds())
                .build();

        teamRepository.save(team);
        LOGGER.info("Saved team{}", teamDto.getName());
        teamDto.setId(team.getId());
    }
}
