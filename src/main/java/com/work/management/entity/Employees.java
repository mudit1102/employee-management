package com.work.management.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.Date;

@Entity
public class Employees {
    private String firstName;
    private String lastName;
    private String userName;
    private Integer id;
    private Integer manager;
    private String teamID;
    private String phoneNumber;
    private Date createdAtTimeStamp;
    private Date lastUpdatedTimeStamp;
    private Integer lastUpdatedBy;
    private Integer createdBy;

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getUsername() {
        return userName;
    }

    @Id
    @GeneratedValue
    public Integer getId() {
        return id;
    }

    public Integer getManager() {
        return manager;
    }

    public String getTeamID() {
        return teamID;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public Date getCreatedAtTimeStamp() {
        return createdAtTimeStamp;
    }

    public Integer getLastUpdatedBy() {
        return lastUpdatedBy;
    }

    public Integer getCreatedBy() {
        return createdBy;
    }

    public Date getLastUpdatedTimeStamp() {
        return lastUpdatedTimeStamp;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setUsername(String username) {
        this.userName = username;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setManager(Integer manager) {
        this.manager = manager;
    }

    public void setTeamID(String teamID) {
        this.teamID = teamID;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
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
