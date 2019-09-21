package com.work.management.converter;

import com.google.common.collect.ImmutableList;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;
import java.util.List;

@Converter
public final class ListConverter implements AttributeConverter<List<String>, String> {

    private static final String delimiter = ";";

    @Override
    public String convertToDatabaseColumn(List<String> list) {

        return String.join(delimiter, list);
    }

    @Override
    public ImmutableList<String> convertToEntityAttribute(String string) {

        return ImmutableList.copyOf(string.split(delimiter));
    }
}
