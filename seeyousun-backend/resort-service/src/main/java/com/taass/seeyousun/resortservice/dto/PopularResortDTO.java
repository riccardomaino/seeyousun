package com.taass.seeyousun.resortservice.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PopularResortDTO {
    private List<ResortDTO> popularResorts;
    private int pageNr;
    private int pageSize;
    private long totalElements;
    private int totalPages;
    private boolean last;
}
