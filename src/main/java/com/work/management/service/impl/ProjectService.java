package com.work.management.service.impl;

import com.work.management.entity.Project;
import com.work.management.entity.ProjectDto;
import com.work.management.repository.EmployeeRepository;
import com.work.management.repository.ProjectRepository;
import com.work.management.web.rest.resource.ProjectResource;
import com.work.management.service.DataPersistingService;
import com.work.management.utils.ExceptionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Objects;

@Service
final class ProjectService implements DataPersistingService<ProjectResource, Project> {

    private final Logger LOGGER = LoggerFactory.getLogger(this.getClass());
    private final ProjectRepository projectRepository;
    private final EmployeeRepository employeeRepository;

    ProjectService(ProjectRepository projectRepository, EmployeeRepository employeeRepository) {
        this.projectRepository = projectRepository;
        this.employeeRepository = employeeRepository;
    }


    @Override
    public ProjectResource preHandle(ProjectResource document) {
        LOGGER.info("PreHandling project{}", document.getName());
        if (Objects.nonNull(projectRepository.findByName(document.getName()))) {
            ExceptionUtils.throwEntityAlreadyExistsException("Project already exists with this name,choose a different name");
        }
        return document;
    }

    @Override
    public Project handle(ProjectResource document) {
        LOGGER.info("Handling project{}", document.getName());
        ProjectDto projectDto = new ProjectDto();
        BeanUtils.copyProperties(document, projectDto);
        return Project.builder().name(projectDto.getName()).owner(projectDto.getOwner()).
                startDate(projectDto.getStartDate()).endDate(projectDto.getEndDate()).createdAtTimeStamp(new Date()).
                createdBy(projectDto.getOwner()).lastUpdatedBy(projectDto.getOwner()).lastUpdatedTimeStamp(new Date()).build();
    }

    @Override
    public void postHandle(Project document) {
        LOGGER.info("PostHandling project{}", document.getName());
        projectRepository.save(document);
    }
}
