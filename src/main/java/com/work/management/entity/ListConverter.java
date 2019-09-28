package com.work.management.entity;

import com.google.common.collect.ImmutableList;
import java.util.List;
import java.util.Objects;
import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

@Converter
public final class ListConverter implements AttributeConverter<List<String>, String> {

  private static final String DELIMITER = ";";

  @Override
  public String convertToDatabaseColumn(List<String> items) {
    if (Objects.isNull(items) || items.isEmpty()) {
      return null;
    }

    return String.join(DELIMITER, items);
  }

  @Override
  public List<String> convertToEntityAttribute(String itemsString) {
    if (Objects.isNull(itemsString) || itemsString.isEmpty()) {
      return null;
    }
    return ImmutableList.copyOf(itemsString.split(DELIMITER));
  }
}