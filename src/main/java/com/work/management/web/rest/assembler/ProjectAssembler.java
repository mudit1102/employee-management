package com.work.management.web.rest.assembler;

import com.work.management.dto.ProjectDto;
import com.work.management.web.rest.resource.ProjectResource;
import org.springframework.beans.BeanUtils;

public final class ProjectAssembler {

  public static ProjectDto convert(ProjectResource projectResource) {
    ProjectDto projectDto = new ProjectDto();
    BeanUtils.copyProperties(projectResource, projectDto);
    return projectDto;

  }
}
