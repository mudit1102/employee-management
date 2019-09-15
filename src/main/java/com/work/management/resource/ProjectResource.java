package com.work.management.resource;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;

import javax.validation.constraints.NotBlank;
import java.util.Date;
import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class ProjectResource {
    @NotBlank
    String name;

    @NotBlank
    String owner;

    @NotBlank
    Date startDate;

    @NotBlank
    Date endDate;

    @NotBlank
    List<String> teamIds;

    public String getName() {
        return name;
    }

    public String getOwner() {
        return owner;
    }

    public Date getStartDate() {
        return startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public void setTeamIds(List<String> teamIds) {
        this.teamIds = teamIds;
    }

    public List<String> getTeamIds() {
        return teamIds;
    }

}
