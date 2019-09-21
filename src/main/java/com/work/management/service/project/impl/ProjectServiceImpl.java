package com.work.management.service.project.impl;

import com.work.management.entity.Project;
import com.work.management.dto.ProjectDto;
import com.work.management.repository.EmployeeRepository;
import com.work.management.repository.ProjectRepository;
import com.work.management.service.project.ProjectService;
import com.work.management.web.rest.resource.ProjectResource;
import com.work.management.utils.ExceptionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Objects;

@Service
final class ProjectServiceImpl implements ProjectService<ProjectDto, Project> {

    private static final Logger LOGGER = LoggerFactory.getLogger(ProjectServiceImpl.class);
    private final ProjectRepository projectRepository;
    private final EmployeeRepository employeeRepository;

    @Autowired
    ProjectServiceImpl(ProjectRepository projectRepository, EmployeeRepository employeeRepository) {
        this.projectRepository = projectRepository;
        this.employeeRepository = employeeRepository;
    }

    @Override
    public void save(ProjectDto projectDto) {
        LOGGER.info("Saving project{}", projectDto.getName());
        if (Objects.nonNull(projectRepository.findByName(projectDto.getName()))) {
            ExceptionUtils.throwEntityAlreadyExistsException("Project already exists with this name,choose a different name");
            return;
        }

        Project project = Project.builder().name(projectDto.getName()).owner(projectDto.getOwner())
                .startDate(projectDto.getStartDate()).endDate(projectDto.getEndDate())
                .createdBy(projectDto.getOwner()).lastUpdatedBy(projectDto.getOwner()).build();

        projectRepository.save(project);
        LOGGER.info("Saved project{}", projectDto.getName());
    }
}
