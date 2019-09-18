package com.work.management.service.impl;

import com.work.management.entity.Team;
import com.work.management.repository.TeamRepository;
import com.work.management.web.rest.resource.TeamResource;
import com.work.management.service.DataPersistingService;
import com.work.management.utils.ExceptionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Objects;

@Service
public class TeamService implements DataPersistingService<TeamResource, Team> {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());


    private final TeamRepository teamRepository;

    @Autowired
    public TeamService(TeamRepository teamRepository) {
        this.teamRepository = teamRepository;
    }

    @Override
    public TeamResource preHandle(TeamResource document) {
        logger.info("PreHandling team{}", document.getName());
        if (Objects.nonNull(teamRepository.findByName(document.getName()))) {
            ExceptionUtils.throwEntityAlreadyExistsException("Team already exists with this name,choose a different name");
        }
        return document;
    }

    @Override
    public Team handle(TeamResource document) {
        logger.info("Handling team{} ", document.getName());
        return Team.builder().name(document.getName()).manager(document.getManager()).createdAtTimeStamp(new Date()).
                createdBy(document.getManager()).lastUpdatedTimeStamp(new Date()).lastUpdatedBy(document.getManager()).build();
    }

    @Override
    public void postHandle(Team document) {
        logger.info("PostHandling team{}", document.getName());
        teamRepository.save(document);
    }
}
