package com.gov.aesa.model.dtos;

import java.math.BigDecimal;
import java.time.LocalDate;
import lombok.Data;

@Data
public class AcudeDTO {

	private Long id;
	private String nome;
	private BigDecimal volumeMorto;
	private BigDecimal volumeAcumulado;
	private BigDecimal volumeMaximo;
	private BigDecimal drenagem;
	private BigDecimal coeficienteTanque;
}