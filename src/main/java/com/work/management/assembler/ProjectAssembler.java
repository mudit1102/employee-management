package com.work.management.assembler;

import com.work.management.entity.Projects;
import com.work.management.resource.ProjectResource;
import org.springframework.beans.BeanUtils;

public class ProjectAssembler {
    public static ProjectResource convert(Projects projects){
        ProjectResource projectResource = new ProjectResource();
        BeanUtils.copyProperties(projects, projectResource);
        return projectResource;
    }
}
