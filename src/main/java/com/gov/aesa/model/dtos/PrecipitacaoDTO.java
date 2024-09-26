package com.gov.aesa.model.dtos;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

public class PrecipitacaoDTO {

	private Long idAcude;
	private String municipio;
	private String estacao;
	private List<AnoMensalDTO> anosMensais;  // Agora será uma lista de objetos que contém o ano e os dados de chuva

	// Getters e setters
	public Long getIdAcude() {
		return idAcude;
	}

	public void setIdAcude(Long idAcude) {
		this.idAcude = idAcude;
	}

	public String getMunicipio() {
		return municipio;
	}

	public void setMunicipio(String municipio) {
		this.municipio = municipio;
	}

	public String getEstacao() {
		return estacao;
	}

	public void setEstacao(String estacao) {
		this.estacao = estacao;
	}

	public List<AnoMensalDTO> getAnosMensais() {
		return anosMensais;
	}

	public void setAnosMensais(List<AnoMensalDTO> anosMensais) {
		this.anosMensais = anosMensais;
	}
}
