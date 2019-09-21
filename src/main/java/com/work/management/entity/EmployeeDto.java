package com.work.management.entity;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;

import java.util.Date;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_EMPTY)
@EqualsAndHashCode
public final class EmployeeDto {

    private String firstName;
    private String lastName;
    private String userName;
    private Integer manager;
    private String teamId;
    private String phoneNumber;
    private Date createdAtTimeStamp;
    private Date lastUpdatedTimeStamp;
    private Integer lastUpdatedBy;
    private Integer createdBy;
}
