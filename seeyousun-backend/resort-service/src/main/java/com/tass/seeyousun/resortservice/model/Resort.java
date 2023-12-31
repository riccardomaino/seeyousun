package com.tass.seeyousun.resortservice.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.tass.seeyousun.resortservice.dto.EventDTO;
import com.tass.seeyousun.resortservice.dto.ReviewDTO;
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
    private Integer rating;

    @ElementCollection
    @CollectionTable(name = "resort_services", joinColumns = @JoinColumn(name = "resort_id"))
    private List<String> services;

    @ElementCollection
    @CollectionTable(name = "resort_photos", joinColumns = @JoinColumn(name = "resort_id"))
    private List<String> photos;

    @OneToMany(mappedBy = "resort")
    private List<Review> reviews;

    @OneToMany(mappedBy = "resort")
    private List<Event> events;

}
