package com.work.management.dto;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;
import java.util.Date;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public final class EmployeeDto {

    private String firstName;
    private String lastName;
    private String userName;
    private Integer id;
    private Integer manager;
    private String teamId;
    private String phoneNumber;
    private Integer lastUpdatedBy;
    private Integer createdBy;
}
