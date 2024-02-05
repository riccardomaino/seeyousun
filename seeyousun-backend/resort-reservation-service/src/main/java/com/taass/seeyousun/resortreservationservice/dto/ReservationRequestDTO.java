package com.taass.seeyousun.resortreservationservice.dto;
/*questa classe viene inviata dal front end in post per registrare una richiesta di prenotazione*/

import com.taass.seeyousun.resortreservationservice.model.PersistenceType;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ReservationRequestDTO {
    private long resort;
    private int reservedUmbrellaLine;
    private int reservedUmbrellaColumn;
    private PersistenceType persistenceType;
    private int numberOfSunbeds;
    @Temporal(TemporalType.DATE)
    private LocalDate initialDate;
    @Temporal(TemporalType.DATE)
    private LocalDate finalDate;
}

