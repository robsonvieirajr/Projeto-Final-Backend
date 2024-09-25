package com.gov.aesa.model.dtos;

import java.util.List;
import lombok.Data;

@Data
public class ChuvaDTO {
	private String municipio;  // Nome do município
	private String estacao;  // Nome da estação (posto)
	private int idMunicipio;  // ID do município
	private List<AnoMensal> anosMensais;  // Lista de anos com dados mensais de chuva

	// Classe estática para representar os dados anuais e mensais de chuva
	@Data
	public static class AnoMensal {
		private String ano;  // Ano de registro
		private List<DadoMensal> dadosMensais;  // Lista de dados mensais

		@Data
		public static class DadoMensal {
			private String mes;  // Mês do registro (ex: "Jan", "Fev")
			private double valor;  // Valor da precipitação em mm para o mês
		}
	}
}