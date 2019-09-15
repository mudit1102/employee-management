package com.work.management.entity;

import javax.persistence.*;
import java.util.Date;
import java.util.List;


@Entity
@Table(name="PROJECTS")
public class Projects {
    String name;

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    String id;
    Integer owner;
    Date startDate;
    Date endDate;
    Date createdAtTimeStamp;
    Date lastUpdatedTimeStamp;
    Integer lastUpdatedBy;
    Integer createdBy;
    List<String> teamIds;

    public Projects() {
    }

    public String getName() {
        return name;
    }

    public String getId() {
        return id;
    }

    public Integer getOwner() {
        return owner;
    }

    public Date getStartDate() {
        return startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public Date getCreatedAtTimeStamp() {
        return createdAtTimeStamp;
    }

    public Date getLastUpdatedTimeStamp() {
        return lastUpdatedTimeStamp;
    }

    public Integer getLastUpdatedBy() {
        return lastUpdatedBy;
    }

    public Integer getCreatedBy() {
        return createdBy;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setOwner(Integer owner) {
        this.owner = owner;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setCreatedAtTimeStamp(Date createdAtTimeStamp) {
        this.createdAtTimeStamp = createdAtTimeStamp;
    }

    public void setLastUpdatedTimeStamp(Date lastUpdatedTimeStamp) {
        this.lastUpdatedTimeStamp = lastUpdatedTimeStamp;
    }

    public void setLastUpdatedBy(Integer lastUpdatedBy) {
        this.lastUpdatedBy = lastUpdatedBy;
    }

    public void setCreatedBy(Integer createdBy) {
        this.createdBy = createdBy;
    }

    public List<String> getTeamIds() {
        return teamIds;
    }

    public void setTeamIds(List<String> teamIds) {
        this.teamIds = teamIds;
    }
}
