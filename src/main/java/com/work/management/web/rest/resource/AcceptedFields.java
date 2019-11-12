package com.work.management.web.rest.resource;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.work.management.entity.Employee;
import java.util.HashMap;
import java.util.Map;
import java.util.function.BiConsumer;
import java.util.function.Function;

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public enum AcceptedFields {
  MANAGER_ID("Integer", (employee, managerId) -> employee.setManager((Integer) managerId)),
  TEAM_ID("String", ((employee, teamId) -> employee.setTeamId((String) teamId)));

  private final String dataType;
  private final BiConsumer<Employee, ?> setterFunction;

  private static final Map<String, Function<String, ?>> mapOfDataTypeConversion;

  static {
    mapOfDataTypeConversion = new HashMap<>();
    mapOfDataTypeConversion.put("Integer", value -> Integer.parseInt(value));
    mapOfDataTypeConversion.put("String", Function.identity());
  }

  AcceptedFields(String dataType, BiConsumer<Employee, ?> setterFunction) {
    this.dataType = dataType;
    this.setterFunction = setterFunction;
  }

  public String getDataType() {
    return this.dataType;
  }

  private <T> T convert(String value) {
    Function<String, ?> function = mapOfDataTypeConversion.get(this.getDataType());
    return (T) function.apply(value);
  }

  public Employee processValue(Employee employee, String value) {
    this.setterFunction.accept(employee, this.convert(value));
    return employee;
  }

}
