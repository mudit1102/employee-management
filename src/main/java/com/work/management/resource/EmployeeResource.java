package com.work.management.resource;

import javax.validation.constraints.NotBlank;

public class EmployeeResource {
    @NotBlank
    private String firstName;
    @NotBlank
    private String lastName;
    @NotBlank
    private Integer manager;
    @NotBlank
    private String teamId;
    @NotBlank
    private String phoneNumber;

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public Integer getManager() {
        return manager;
    }

    public String getTeamId() {
        return teamId;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setManager(Integer manager) {
        this.manager = manager;
    }

    public void setTeamId(String teamId) {
        this.teamId = teamId;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
}
