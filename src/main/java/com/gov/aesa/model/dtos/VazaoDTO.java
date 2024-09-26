package com.gov.aesa.model.dtos;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class VazaoDTO {
    private Long id;
    private Long id_acude;
    private BigDecimal valorVazao;
    private String mesVazao;
    private Integer anoVazao;
}
