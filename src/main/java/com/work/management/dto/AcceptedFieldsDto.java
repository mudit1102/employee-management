package com.work.management.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public enum AcceptedFieldsDto {
  MANAGER_ID("Integer"),
  TEAM_ID("String");

  private final String dataType;

  AcceptedFieldsDto(String dataType) {
    this.dataType = dataType;
  }

  public String getDataType() {
    return dataType;
  }
}
