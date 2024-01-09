package com.taass.seeyousun.resortreservationservice.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.taass.seeyousun.resortreservationservice.DTO.ReservationDTO;
import com.taass.seeyousun.resortreservationservice.DTO.UmbrellaDTO;
import com.taass.seeyousun.resortreservationservice.exception.PriceNotSettedException;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;
import java.util.List;

/*
* Contiene tutte le prenotazioni di un dato giorno in un dato resort, e anche la poosizione rispetto alla mappa
* */

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "resort_reservations")
public class ResortReservation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private int totalUmbrellaLine;

    private int totalUmbrellaColumn;

    @Temporal(TemporalType.DATE)
    private Date date;

    private long resort;

    @OneToMany(mappedBy = "resortReservation")
    private List<PricePeriod> pricePeriodList;

    @OneToMany(mappedBy = "resortReservation")
    @JsonManagedReference
    private List<Reservation> reservation;


    public ReservationDTO getReservationInformation() throws PriceNotSettedException {
        ReservationDTO reservationDTO = new ReservationDTO(totalUmbrellaLine, totalUmbrellaColumn);

        //setta la lista degli ombrelloni occupati(linea,colonna)
        reservationDTO.setReservedUmbrella(
                reservation.stream()
                        .map(r -> new UmbrellaDTO(r.getReservedUmbrellaLine(), getTotalUmbrellaColumn()))
                        .toList());


        for(PricePeriod p : pricePeriodList){
            if(p.isInPeriod(date)){
                reservationDTO.setUmbrellaPrice(p.getUmbrellaPrice());
                reservationDTO.setSunbedPrice(p.getSunbedPrice());
                return reservationDTO;
            }
        }

        throw new PriceNotSettedException();
    }
}
