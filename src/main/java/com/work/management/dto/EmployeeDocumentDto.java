package com.work.management.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class EmployeeDocumentDto {

  private String firstName;
  private String lastName;
  private String userName;
  private Integer id;
  private Integer manager;
  private String teamId;
  private String phoneNumber;
  private Integer age;

}
