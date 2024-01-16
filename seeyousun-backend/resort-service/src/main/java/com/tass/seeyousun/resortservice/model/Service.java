package com.tass.seeyousun.resortservice.model;

import com.tass.seeyousun.resortservice.enums.Accessibilita;
import com.tass.seeyousun.resortservice.enums.ServiceInterface;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import java.util.List;
import java.util.Set;

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

    @ManyToMany(mappedBy = "services")
    private Set<Resort> resorts;

    private ServiceInterface serviceInterface;
}
