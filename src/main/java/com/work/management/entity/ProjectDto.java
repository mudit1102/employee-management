package com.work.management.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;

import java.util.Date;

@EqualsAndHashCode
@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public final class ProjectDto {

    String name;
    Integer owner;
    Date startDate;
    Date endDate;
    Date createdAtTimeStamp;
    Date lastUpdatedTimeStamp;
    Integer lastUpdatedBy;
    Integer createdBy;
}
