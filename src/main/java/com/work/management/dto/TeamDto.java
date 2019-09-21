package com.work.management.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.google.common.collect.ImmutableList;
import lombok.*;

import java.util.Date;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public final class TeamDto {

    private String name;
    private String id;
    private Integer manager;
    private Date createdAtTimeStamp;
    private Date lastUpdatedTimeStamp;
    private Integer lastUpdatedBy;
    private Integer createdBy;
    private ImmutableList<String> employeeIds;

}
