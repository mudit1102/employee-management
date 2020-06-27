package com.work.management.web.rest.resource;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public enum FieldInfo {

  FIRST_NAME(DataType.STRING, "firstName"),
  AGE(DataType.INTEGER, "age"),
  MANAGER_ID(DataType.STRING, "manager"),
  SALARY(DataType.INTEGER, "salary"),;

  private final DataType dataType;
  private final String fieldName;
  private static final Map<DataType, Function<List<String>, ?>> mapOfDataTypeConversion;

  static {
    mapOfDataTypeConversion = new HashMap<>();
    mapOfDataTypeConversion.put(DataType.INTEGER, value -> {
      List<Integer> list = new ArrayList<>();
      value.forEach(item -> list.add(Integer.parseInt(item)));
      return list;
    });
    mapOfDataTypeConversion.put(DataType.BOOLEAN, value -> {
      List<Boolean> list = new ArrayList<>();
      value.forEach(item -> list.add(Boolean.parseBoolean(item)));
      return list;
    });
    mapOfDataTypeConversion.put(DataType.FLOAT, value -> {
      List<Float> list = new ArrayList<>();
      value.forEach(item -> list.add(Float.parseFloat(item)));
      return list;
    });
    mapOfDataTypeConversion.put(DataType.STRING, Function.identity());
  }

  FieldInfo(DataType dataType, String fieldName) {
    this.dataType = dataType;
    this.fieldName = fieldName;
  }


  public DataType getDataType() {
    return this.dataType;
  }

  public String getFieldName() {
    return fieldName;
  }


  public <T> List<T> convert(List<String> value) {
    Function<List<String>, ?> function = mapOfDataTypeConversion.get(this.getDataType());
    return (List<T>) function.apply(value);
  }
}
