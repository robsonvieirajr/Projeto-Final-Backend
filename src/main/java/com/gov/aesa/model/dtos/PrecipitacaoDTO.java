package com.gov.aesa.model.dtos;

import lombok.Data;
import java.math.BigDecimal;
import java.util.Map;

@Data
public class PrecipitacaoDTO {
	private Long idAcude;
	private String municipio;
	private String estacao;
	private Integer ano;
	// Map que associa cada mês com o valor da chuva
	private Map<String, BigDecimal> chuvaPorMes;
}
