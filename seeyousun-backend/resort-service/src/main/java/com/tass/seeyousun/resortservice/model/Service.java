package com.tass.seeyousun.resortservice.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.tass.seeyousun.resortservice.converter.ServiceConverter;
import com.tass.seeyousun.resortservice.enums.ServiceInterface;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "service")
public class Service {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JsonBackReference
    private Resort resorts;


    @Convert(converter = ServiceConverter.class)
    private ServiceInterface serviceInterface;

}

