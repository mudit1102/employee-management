package com.work.management.dto;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import java.util.Date;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

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
  private Date createdAtTimeStamp;
  private Date lastUpdatedTimeStamp;
  private Integer lastUpdatedBy;
  private Integer createdBy;
}
