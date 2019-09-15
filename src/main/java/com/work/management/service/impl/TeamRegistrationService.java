package com.work.management.service.impl;

import com.work.management.entity.Teams;
import com.work.management.repository.TeamRepository;
import com.work.management.resource.TeamResource;
import com.work.management.service.DataPersistingService;
import com.work.management.utils.ExceptionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Objects;

@Service
public class TeamRegistrationService implements DataPersistingService<TeamResource, Teams> {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private TeamRepository teamRepository;

    @Override
    public TeamResource preHandle(TeamResource document) {
        logger.info("PreHandling team{}", document.getName());
        if(Objects.nonNull(teamRepository.findByName(document.getName()))){
            ExceptionUtils.throwEntityAlreadyExistsException("Team already exists with this name,choose a different name");
        }
        return document;
    }

    @Override
    public Teams handle(TeamResource document) {
        logger.info("Handling team{} ", document.getName());
        Teams teams = new Teams();
        teams.setName(document.getName());
        teams.setManager(document.getManager());
        teams.setEmployeesId(document.getEmployeesId());
        teams.setCreatedAtTimeStamp(new Date());
        teams.setLastUpdatedTimeStamp(new Date());
        teams.setCreatedBy(document.getManager());
        teams.setCreatedBy(document.getManager());
        teams.setLastUpdatedBy(document.getManager());
        return teams;
    }

    @Override
    public void postHandle(Teams document) {
        logger.info("PostHandling team{}", document.getName());
        teamRepository.save(document);
    }
}
