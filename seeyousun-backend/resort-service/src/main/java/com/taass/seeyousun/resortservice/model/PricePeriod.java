package com.taass.seeyousun.resortservice.model;


import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;


/**
 * Questa classe rappresenta un entity "PricePeriod". Un PricePeriod contiene tutte le informazioni relative ai prezzi,
 * in un certo periodo, di un resort. I prezzi mantenuti sono: prezzo per linea e colonna dell'ombrellone, e prezzo del
 * lettino.
 * I servizi offerti dal Resort sono salvati in table separata, dove per ogni Resort (con riferimento al suo id) si prende
 * nota dei servizi offerti (con riferimento all'id del servizio, in quanto vi sarà una table Services che conterrà tutti
 * i tipi di servizi esistenti che possono essere offerti dai vari resorts)
 * Osserviamo che sotto l'attributo "information" salviamo diversi tipi di informazioni in formato stringa, questo
 * si traduce in una table nel Database con associa a un Resort delle stringhe che rappresentano ad esempio: numero di
 * telefono, giorni di apertura e orario.
 */
/*Un array di prezzi crescenti in base alla fila, valido per il periodo specificato tra le due date*/
@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "price_period")
public class PricePeriod {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(name = "umbrella_line_price", joinColumns = @JoinColumn(name = "price_period_id"))
    @OrderColumn
    private List<Integer> umbrellaPrice;

    private Integer sunbedPrice;

    @Temporal(TemporalType.DATE)
    private LocalDate periodInitialDate;

    @Temporal(TemporalType.DATE)
    private LocalDate periodFinalDate;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "resort_id")
    @JsonBackReference
    private Resort resort;

    public boolean isInPeriod(LocalDate date) {
        return periodInitialDate.isBefore(date) && periodFinalDate.isAfter(date);
    }
}
