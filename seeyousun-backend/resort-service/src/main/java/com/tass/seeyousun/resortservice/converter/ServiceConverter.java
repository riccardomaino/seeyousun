package com.tass.seeyousun.resortservice.converter;

import com.tass.seeyousun.resortservice.enums.Accessibilita;
import com.tass.seeyousun.resortservice.enums.ServiceInterface;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Convert;
import jakarta.persistence.Converter;
import org.apache.catalina.core.ApplicationContext;

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
        ServiceInterface ss;
        try {
            ss = (ServiceInterface) Class.forName(category).getMethod("valueOf", String.class).invoke(null,value);
        } catch (IllegalAccessException | InvocationTargetException | NoSuchMethodException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        return ss;
    }
}
