package com.taass.seeyousun.resortreservationservice.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.taass.seeyousun.resortreservationservice.DTO.ReservationDTO;
import com.taass.seeyousun.resortreservationservice.DTO.UmbrellaDTO;
import com.taass.seeyousun.resortreservationservice.exception.PriceNotSettedException;
import com.taass.seeyousun.resortreservationservice.exception.UmbrellaAlreadyReservedException;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDate;
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


    @Temporal(TemporalType.DATE)
    private LocalDate date;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "resort_id")
    @JsonBackReference
    private Resort resort;

    @OneToMany(mappedBy = "resortReservation", cascade = CascadeType.ALL)
    @JsonManagedReference
    private List<Reservation> reservation;



    public ReservationDTO getReservationInformation() throws PriceNotSettedException {
        ReservationDTO reservationDTO = new ReservationDTO(resort.getTotalUmbrellaLine(), resort.getTotalUmbrellaColumn());

        //setta la lista degli ombrelloni occupati(linea,colonna)
        reservationDTO.setReservedUmbrella(
                reservation.stream()
                        .map(r -> new UmbrellaDTO(r.getReservedUmbrellaLine(), r.getReservedUmbrellaColumn()))
                        .toList());


        for(PricePeriod p : resort.getPricePeriodList()){
            if(p.isInPeriod(date)){
                reservationDTO.setUmbrellaPrice(p.getUmbrellaPrice());
                reservationDTO.setSunbedPrice(p.getSunbedPrice());
                return reservationDTO;
            }
        }

        throw new PriceNotSettedException();
    }
    
    public boolean isInPeriod(LocalDate initialDate, LocalDate finalDate){
        return initialDate.isBefore(this.date) && finalDate.isAfter(this.date);
    }

    //TODO: aggiungere controllo sul posto: fare in modo che non ecceda i limiti della mappa
    public void addReservation(Reservation newReservation) throws UmbrellaAlreadyReservedException {
        boolean isAlreadyReserved = reservation.stream()
                .anyMatch(r -> r.isSamePlaceAndSameDayTime(newReservation));
        if(isAlreadyReserved) throw new UmbrellaAlreadyReservedException("Umbrella (" +
                newReservation.getReservedUmbrellaLine() + ", " + newReservation.getReservedUmbrellaColumn()+
                ") are already reserved");
        newReservation.setResortReservation(this);
        reservation.add(newReservation);
        System.out.println(reservation.size());
    }
}
