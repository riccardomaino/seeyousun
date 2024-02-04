package com.taass.seeyousun.resortservice.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.List;

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

    @Column(name = "name")
    private String name;

    @Column(name = "location")
    private String location;

    @Column(name = "description")
    private String description;

    @Column(name = "rating")
    private Double rating;

    // Informazioni quali: numero di telefono, giorni di apertura e orario
    @ElementCollection
    @CollectionTable(
            name = "resorts_information",
            joinColumns = @JoinColumn(name = "resort_id")
    )
    private List<String> information;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "resorts_services",
            joinColumns = @JoinColumn(name = "resort_id"),
            inverseJoinColumns = @JoinColumn(name = "service_id")
    )
    @JsonManagedReference
    private List<Service> services;

    //https://storage.googleapis.com/pod_public/1300/122734.jpg
    @Column(name = "photo_cover")
    private String photoCover;

    @ElementCollection
    @CollectionTable(
            name = "resorts_photos",
            joinColumns = @JoinColumn(name = "resort_id")
    )
    private List<String> photos;

    @OneToMany(mappedBy = "resort")
    @JsonManagedReference
    private List<Event> events;



    private int totalUmbrellaLine;

    private int totalUmbrellaColumn;

    @OneToMany(mappedBy = "resort")
    @JsonManagedReference
    private List<PricePeriod> pricePeriodList;
}
