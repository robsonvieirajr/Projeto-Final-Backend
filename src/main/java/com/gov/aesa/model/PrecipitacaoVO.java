package com.gov.aesa.model;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.LinkedHashSet;
import java.util.Set;
import org.hibernate.annotations.ColumnDefault;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "precipitacao", schema = "aesa")
public class PrecipitacaoVO {
	@Id
	@ColumnDefault("nextval('seq_id_precipitacao'::regclass)")
	@Column(name = "id_precipitacao", nullable = false)
	private Long id;

	@Column(name = "valorprecipitacao")
	private BigDecimal valorprecipitacao;

	@Column(name = "dataprecipitacao")
	private LocalDate dataprecipitacao;

	@OneToMany(mappedBy = "idPrecipitacao")
	private Set<AcudeVO> acudes = new LinkedHashSet<>();

}