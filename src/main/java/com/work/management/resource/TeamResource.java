package com.work.management.resource;

import javax.validation.constraints.NotBlank;
import java.util.List;

public class TeamResource {

    @NotBlank
    private String name;
    @NotBlank
    private List<Integer> employeesId;
    @NotBlank
    private Integer manager;

    public String getName() {
        return name;
    }

    public List<Integer> getEmployeesId() {
        return employeesId;
    }

    public Integer getManager() {
        return manager;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setEmployeesId(List<Integer> employeesId) {
        this.employeesId = employeesId;
    }

    public void setManager(Integer manager) {
        this.manager = manager;
    }
}
