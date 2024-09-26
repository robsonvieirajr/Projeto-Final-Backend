package com.gov.aesa.model.dtos;

import java.math.BigDecimal;
import java.util.Map;

public class AnoMensalDTO {
	private int ano;  // O ano da precipitação
	private Map<String, BigDecimal> chuvaPorMes;  // Mapa dos meses e seus respectivos valores de chuva

	public int getAno() {
		return ano;
	}

	public void setAno(int ano) {
		this.ano = ano;
	}

	public Map<String, BigDecimal> getChuvaPorMes() {
		return chuvaPorMes;
	}

	public void setChuvaPorMes(Map<String, BigDecimal> chuvaPorMes) {
		this.chuvaPorMes = chuvaPorMes;
	}
}
