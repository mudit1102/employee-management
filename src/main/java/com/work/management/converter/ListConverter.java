package com.work.management.converter;

import com.google.common.collect.ImmutableList;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;
import java.util.List;

@Converter
public final class ListConverter implements AttributeConverter<ImmutableList<String>, String> {

    private static final String DELIMITER = ";";

    @Override
    public String convertToDatabaseColumn(ImmutableList<String> attribute) {
        return String.join(DELIMITER, attribute);
    }

    @Override
    public ImmutableList<String> convertToEntityAttribute(String string) {
        return ImmutableList.copyOf(string.split(DELIMITER));
    }
}
