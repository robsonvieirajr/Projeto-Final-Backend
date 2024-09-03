package com.gov.aesa.model;

import java.math.BigDecimal;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "acude", schema = "aesa")
public class AcudeVO {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "acude_seq_gen")
	@SequenceGenerator(name = "acude_seq_gen", sequenceName = "seq_id_acude", allocationSize = 1)
	@Column(name = "id_acude", nullable = false)
	private Long id;

	@Size(max = 255)
	@Column(name = "nome")
	private String nome;

	@Column(name = "vol_morto")
	private BigDecimal volumeMorto;

	@Column(name = "vol_acumulado")
	private BigDecimal volumeAcumulado;

	@Column(name = "area_drenagem")
	private BigDecimal areaDrenagem;

	@Column(name = "vol_maximo")
	private BigDecimal volumeMaximo;

	@Column(name = "coeficiente_tanque")
	private BigDecimal coeficienteTanque;
}
