package com.work.management.web.rest.resource;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public enum AcceptedFields {
  MANAGER_ID("Integer"),
  TEAM_ID("String");

  private final String dataType;

  AcceptedFields(String dataType) {
    this.dataType = dataType;
  }

  public String getDataType() {
    return dataType;
  }
}
