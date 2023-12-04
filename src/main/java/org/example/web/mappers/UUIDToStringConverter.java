package org.example.web.mappers;

import org.modelmapper.Converter;
import org.modelmapper.spi.MappingContext;
import java.util.UUID;

public class UUIDToStringConverter implements Converter<UUID, String> {
    @Override
    public String convert(MappingContext<UUID, String> context) {
        if (context.getSource() == null) {
            return null;
        }
        return context.getSource().toString();
    }
}