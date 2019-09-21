package com.work.management.web.rest.assembler;

import com.work.management.dto.ProjectDto;
import com.work.management.entity.Project;
import com.work.management.web.rest.resource.ProjectResource;
import org.springframework.beans.BeanUtils;

public final class ProjectAssembler {

    public static ProjectDto convert(ProjectResource projectResource) {
        ProjectDto projectDto = ProjectDto.builder().name(projectResource.getName()).owner(projectResource.getOwner())
                .startDate(projectResource.getStartDate()).endDate(projectResource.getEndDate()).build();
        return projectDto;

    }
}
