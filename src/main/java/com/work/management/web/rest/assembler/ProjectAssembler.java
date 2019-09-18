package com.work.management.web.rest.assembler;

import com.work.management.entity.Project;
import com.work.management.web.rest.resource.ProjectResource;
import org.springframework.beans.BeanUtils;

public final class ProjectAssembler {
    public static ProjectResource convert(Project projects){
        ProjectResource projectResource = new ProjectResource();
        BeanUtils.copyProperties(projects, projectResource);
        return projectResource;
    }
}
