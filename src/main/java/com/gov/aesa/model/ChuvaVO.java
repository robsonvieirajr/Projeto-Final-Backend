package com.gov.aesa.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import lombok.Data;

import java.math.BigDecimal;

@Data
@Entity
@Table(name = "chuva")
public class ChuvaVO {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "chuva_seq")
	@SequenceGenerator(name = "chuva_seq", sequenceName = "aesa.seq_id_chuva", allocationSize = 1)
	@Column(name = "id_chuva")
	private Long idChuva;

	@Column(name = "id_acude")
	private Long idAcude;

	@Column(name = "municipio", length = 255)
	private String municipio;

	@Column(name = "estacao", length = 255)
	private String estacao;

	@Column(name = "ano")
	private Integer ano;

	@Column(name = "mes", length = 255)
	private String mes;

	@Column(name = "valor_chuva")
	private BigDecimal valorChuva;
}