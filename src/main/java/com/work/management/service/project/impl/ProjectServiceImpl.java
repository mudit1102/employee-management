package com.work.management.service.project.impl;

import com.work.management.dto.ProjectDto;
import com.work.management.entity.Project;
import com.work.management.repository.EmployeeRepository;
import com.work.management.repository.ProjectRepository;
import com.work.management.service.project.ProjectService;
import com.work.management.utils.ExceptionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
final class ProjectServiceImpl implements ProjectService {

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
    if (projectRepository.findByName(projectDto.getName()).isPresent()) {
      ExceptionUtils.throwEntityAlreadyExistsException(
          "Project already exists with this name,choose a different name");
      return;
    }

    Project project = new Project();
    BeanUtils.copyProperties(projectDto, project);
    project.setCreatedBy(projectDto.getOwner());
    project.setLastUpdatedBy(projectDto.getOwner());

    projectRepository.save(project);
  }
}
