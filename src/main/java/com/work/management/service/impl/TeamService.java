package com.work.management.service.impl;

import com.work.management.entity.Team;
import com.work.management.entity.TeamDto;
import com.work.management.repository.TeamRepository;
import com.work.management.web.rest.resource.TeamResource;
import com.work.management.service.DataPersistingService;
import com.work.management.utils.ExceptionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Objects;

@Service
final class TeamService implements DataPersistingService<TeamResource, Team> {

    private final Logger LOGGER = LoggerFactory.getLogger(this.getClass());
    private final TeamRepository teamRepository;

    @Autowired
    TeamService(TeamRepository teamRepository) {
        this.teamRepository = teamRepository;
    }

    @Override
    public TeamResource preHandle(TeamResource document) {
        LOGGER.info("PreHandling team{}", document.getName());
        if (Objects.nonNull(teamRepository.findByName(document.getName()))) {
            ExceptionUtils.throwEntityAlreadyExistsException("Team already exists with this name,choose a different name");
        }
        return document;
    }

    @Override
    public Team handle(TeamResource document) {
        LOGGER.info("Handling team{} ", document.getName());
        TeamDto teamDto = new TeamDto();
        BeanUtils.copyProperties(document, teamDto);
        return Team.builder().name(teamDto.getName()).manager(teamDto.getManager()).createdAtTimeStamp(new Date()).
                createdBy(teamDto.getManager()).lastUpdatedTimeStamp(new Date()).lastUpdatedBy(teamDto.getManager()).employeeIds(teamDto.getEmployeeIds()).build();
    }

    @Override
    public void postHandle(Team document) {
        LOGGER.info("PostHandling team{}", document.getName());
        teamRepository.save(document);
    }
}
