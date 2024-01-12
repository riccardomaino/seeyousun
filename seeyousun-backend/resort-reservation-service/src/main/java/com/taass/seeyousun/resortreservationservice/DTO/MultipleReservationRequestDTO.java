package com.taass.seeyousun.resortreservationservice.DTO;
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
public class MultipleReservationRequestDTO {
    private long resort;
    private int umbrellaLine;
    private int umbrellaColumn;

    @Temporal(TemporalType.DATE)
    private LocalDate intialDate;
    @Temporal(TemporalType.DATE)
    private LocalDate finalDate;

    private PersistenceType persistenceType;

    private int numerbOfSunbeds;
}

