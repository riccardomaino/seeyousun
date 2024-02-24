package com.taass.seeyousun.resortservice.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.math.BigDecimal;
import java.util.List;

/**
 * Questa classe rappresenta un entity "Resort". Un Resort contiene tutte le informazioni relative del Resort.
 * I servizi offerti dal Resort sono salvati in table separata, dove per ogni Resort (con riferimento al suo id) si prende
 * nota dei servizi offerti (con riferimento all'id del servizio, in quanto vi sarà una table Services che conterrà tutti
 * i tipi di servizi esistenti che possono essere offerti dai vari resorts)
 * Osserviamo che sotto l'attributo "information" salviamo diversi tipi di informazioni in formato stringa, questo
 * si traduce in una table nel Database con associa a un Resort delle stringhe che rappresentano ad esempio: numero di
 * telefono, giorni di apertura e orario.
 */
@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "resorts")
public class Resort {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String location;

    private String description;

    @Column(columnDefinition = "NUMERIC(2,1) DEFAULT 0.0", nullable = false)
    private BigDecimal rating;

    @ElementCollection
    @CollectionTable(
            name = "phone_numbers",
            joinColumns = @JoinColumn(name = "resort_id")
    )
    private List<String> phoneNumbers;

    @ElementCollection
    @CollectionTable(
            name = "time_tables",
            joinColumns = @JoinColumn(name = "resort_id")
    )
    private List<String> timeTables;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "resorts_services",
            joinColumns = @JoinColumn(name = "resort_id"),
            inverseJoinColumns = @JoinColumn(name = "service_id")
    )
    @JsonManagedReference
    private List<Service> services;

    private String photoCover;

    @ElementCollection
    @CollectionTable(
            name = "resorts_photos",
            joinColumns = @JoinColumn(name = "resort_id")
    )
    private List<String> photos;

    private Integer totalUmbrellaLine;

    private Integer totalUmbrellaColumn;

    @OneToMany(mappedBy = "resort")
    @JsonManagedReference
    private List<PricePeriod> pricePeriodList;
}
