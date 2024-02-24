package com.taass.seeyousun.resortservice.dto;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class DimensionDTO {
    private Integer totalUmbrellaLine;
    private Integer totalUmbrellaColumn;
}
