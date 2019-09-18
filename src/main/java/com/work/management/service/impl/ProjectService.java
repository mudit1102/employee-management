package com.work.management.service.impl;

import com.work.management.entity.Project;
import com.work.management.repository.EmployeeRepository;
import com.work.management.repository.ProjectRepository;
import com.work.management.web.rest.resource.ProjectResource;
import com.work.management.service.DataPersistingService;
import com.work.management.utils.ExceptionUtils;
import lombok.NoArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Objects;

@Service
public final class ProjectService implements DataPersistingService<ProjectResource, Project> {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());


    private final ProjectRepository projectRepository;
    private final EmployeeRepository employeeRepository;

    public ProjectService(ProjectRepository projectRepository, EmployeeRepository employeeRepository) {
        this.projectRepository = projectRepository;
        this.employeeRepository = employeeRepository;
    }


    @Override
    public ProjectResource preHandle(ProjectResource document) {
        logger.info("PreHandling project{}", document.getName());
        if (Objects.nonNull(projectRepository.findByName(document.getName()))) {
            ExceptionUtils.throwEntityAlreadyExistsException("Project already exists with this name,choose a different name");
        }
        return document;
    }

    @Override
    public Project handle(ProjectResource document) {
        logger.info("Handling project{}", document.getName());
        return Project.builder().name(document.getName()).owner(document.getOwner()).
                startDate(document.getStartDate()).endDate(document.getEndDate()).createdAtTimeStamp(new Date()).
                createdBy(document.getOwner()).lastUpdatedBy(document.getOwner()).lastUpdatedTimeStamp(new Date()).build();
    }

    @Override
    public void postHandle(Project document) {
        logger.info("PostHandling project{}", document.getName());
        projectRepository.save(document);
    }
}
