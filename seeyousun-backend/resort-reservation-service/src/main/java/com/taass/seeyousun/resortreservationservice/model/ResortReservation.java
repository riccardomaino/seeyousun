package com.taass.seeyousun.resortreservationservice.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.taass.seeyousun.resortreservationservice.DTO.PriceDTO;
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

    private int umbrellaLine;

    private int umbrellaColumn;

    @Temporal(TemporalType.DATE)
    private Date date;

    private long resort;

    @OneToMany(mappedBy = "resortReservation")
    private List<PricePeriod> pricePeriodList;

    @OneToMany(mappedBy = "resortReservation")
    @JsonManagedReference
    private List<Reservation> reservation;


    public PriceDTO getPrice () throws PriceNotSettedException {
        PriceDTO priceDTO = new PriceDTO(umbrellaLine,umbrellaColumn);

        for(PricePeriod p : pricePeriodList){
            if(p.isInPeriod(date)){
                priceDTO.setUmbrellaPrice(p.getUmbrellaPrice());
                priceDTO.setSunbedPrice(p.getSunbedPrice());
                return priceDTO;
            }
        }
        throw new PriceNotSettedException();
    }
}
