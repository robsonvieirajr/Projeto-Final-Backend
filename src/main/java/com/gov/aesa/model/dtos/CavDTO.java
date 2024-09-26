package com.gov.aesa.model.dtos;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class CavDTO {

    private Long id;
    private Long id_acude;
    private BigDecimal cota;
    private BigDecimal area;
    private BigDecimal volume;

}
