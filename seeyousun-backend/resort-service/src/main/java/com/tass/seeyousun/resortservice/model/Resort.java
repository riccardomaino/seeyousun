package com.tass.seeyousun.resortservice.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.tass.seeyousun.resortservice.enums.ServiceInterface;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Set;

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


    /*informazioi quali numero di telefono, orario ecc */
    @ElementCollection
    @CollectionTable(name = "resort_information", joinColumns = @JoinColumn(name = "resort_id"))
    private List<String> information;

    //TODO: mettere enum
    @ManyToMany
    @JoinTable(
            name = "resorts_services",
            joinColumns = @JoinColumn(name = "service_id"),
            inverseJoinColumns = @JoinColumn(name = "resort_id")
    )
    private Set<Service> services;

    //https://storage.googleapis.com/pod_public/1300/122734.jpg
    @Column(name = "photo_cover")
    private String photoCover;

    @ElementCollection
    @CollectionTable(name = "resort_photos", joinColumns = @JoinColumn(name = "resort_id"))
    private List<String> photos;

    @OneToMany(mappedBy = "resort")
    @JsonManagedReference
    private List<Review> reviews;

    @OneToMany(mappedBy = "resort")
    @JsonManagedReference
    private List<Event> events;
}
