package com.work.management.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.Date;
import java.util.List;

@Entity
public class Teams {

    private String name;
    @Id
    @GeneratedValue
    private String id;
    private List<Integer> employeesId;
    private Integer manager;
    private Date createdAtTimeStamp;
    private Date lastUpdatedTimeStamp;
    private Integer lastUpdatedBy;
    private Integer createdBy;

    public String getName() {
        return name;
    }

    public String getId() {
        return id;
    }

    public List<Integer> getEmployeesId() {
        return employeesId;
    }

    public Integer getManager() {
        return manager;
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

    public void setId(String id) {
        this.id = id;
    }

    public void setEmployeesId(List<Integer> employeesId) {
        this.employeesId = employeesId;
    }

    public void setManager(Integer manager) {
        this.manager = manager;
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
}
