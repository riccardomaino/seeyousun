package com.tass.seeyousun.resortservice.dto;

import com.tass.seeyousun.resortservice.enums.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ServiceDTO {
    private String name;

    private String category;

    public ServiceInterface getService(){
        return switch (category) {
            case "Benessere" -> Benessere.valueOf(name);
            case "Accessibilità" -> Accessibilita.valueOf(name);
            case "Ristorazione" -> Ristorazione.valueOf(name);
            case "Servizio" -> Servizio.valueOf(name);
            case "ServizioPerFamiglie" -> ServiziPerFamiglie.valueOf(name);
            case "SportEAttivita" -> SportEAttivita.valueOf(name);
            case null, default -> throw new RuntimeException();
        };
    }
}
