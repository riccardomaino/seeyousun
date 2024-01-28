package com.taass.seeyousun.resortservice.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.taass.seeyousun.resortservice.converter.ServiceConverter;
import com.taass.seeyousun.resortservice.enums.ServiceInterface;
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
@Table(name = "services")
public class Service {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "category")
    private String category;

    @ManyToMany(mappedBy = "services")
    @JsonBackReference
    private List<Resort> resorts;
}

