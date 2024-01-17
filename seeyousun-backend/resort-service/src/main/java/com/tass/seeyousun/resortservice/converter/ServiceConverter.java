package com.tass.seeyousun.resortservice.converter;

import com.fasterxml.jackson.databind.util.ClassUtil;
import com.tass.seeyousun.resortservice.enums.*;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Convert;
import jakarta.persistence.Converter;
import org.apache.catalina.core.ApplicationContext;
import org.springframework.util.ClassUtils;

import java.lang.reflect.InvocationTargetException;

@Converter(autoApply = true)
public class ServiceConverter implements AttributeConverter<ServiceInterface,String> {
    @Override
    public String convertToDatabaseColumn(ServiceInterface serviceInterface) {
        return serviceInterface.toString()+":"+serviceInterface.getClass().getSimpleName();
    }

    @Override
    public ServiceInterface convertToEntityAttribute(String s) {
        String value = s.split(":")[0];
        String category = s.split(":")[1];
        return switch (category) {
            case "Benessere" -> Benessere.valueOf(value);
            case "Accessibilità" -> Accessibilita.valueOf(value);
            case "Ristorazione" -> Ristorazione.valueOf(value);
            case "Servizio" -> Servizio.valueOf(value);
            case "ServizioPerFamiglie" -> ServiziPerFamiglie.valueOf(value);
            case "SportEAttivita" -> SportEAttivita.valueOf(value);
            case null, default -> throw new RuntimeException();
        };
    }
}
