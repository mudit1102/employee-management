package com.work.management.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;

import java.util.Date;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public final class TeamDto {

    private String name;
    private Integer manager;
    private Date createdAtTimeStamp;
    private Date lastUpdatedTimeStamp;
    private Integer lastUpdatedBy;
    private Integer createdBy;
    private List<String> employeeIds;

}
