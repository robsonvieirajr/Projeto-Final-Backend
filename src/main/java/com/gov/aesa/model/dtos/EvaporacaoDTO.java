package com.gov.aesa.model.dtos;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class EvaporacaoDTO {
    private Long id;
    private Long id_acude;
    private BigDecimal valorEvaporacao;
    private String mesEvaporacao;
    private Integer anoEvaporacao;
}
