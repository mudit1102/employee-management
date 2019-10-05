package com.work.management.web.rest.resource;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class EmployeeResponse {

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
