package com.work.management.service.impl;

import com.work.management.entity.Projects;
import com.work.management.repository.EmployeeRepository;
import com.work.management.repository.ProjectRepository;
import com.work.management.resource.ProjectResource;
import com.work.management.service.DataPersistingService;
import com.work.management.utils.ExceptionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Objects;
import java.util.Optional;

@Service
public class ProjectRegistrationService implements DataPersistingService<ProjectResource,Projects> {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private ProjectRepository projectRepository;

    @Autowired
    private EmployeeRepository employeeRepository;

    @Override
    public ProjectResource preHandle(ProjectResource document) {
        logger.info("PreHandling project{}", document.getName());
        if(Objects.nonNull(projectRepository.findByName(document.getName()))){
            ExceptionUtils.throwEntityAlreadyExistsException("Project already exists with this name,choose a different name");
        }
        return document;
    }

    @Override
    public Projects handle(ProjectResource document) {
        logger.info("Handling project{}", document.getName());
        Projects project = new Projects();
        project.setName(document.getName());
        project.setStartDate(document.getStartDate());
        project.setEndDate(document.getEndDate());
        project.setOwner(employeeRepository.findByUserName(document.getOwner()).get().getId());
        project.setCreatedAtTimeStamp(new Date());
        project.setCreatedBy(employeeRepository.findByUserName(document.getOwner()).get().getId());
        project.setLastUpdatedBy(employeeRepository.findByUserName(document.getOwner()).get().getId());
        project.setTeamIds(document.getTeamIds());
        return project;
    }

    @Override
    public void postHandle(Projects document) {
        logger.info("PostHandling project{}", document.getName());
        projectRepository.save(document);
    }
}
