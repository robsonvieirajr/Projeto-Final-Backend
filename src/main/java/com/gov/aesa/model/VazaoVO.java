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
@Table(name = "vazao", schema = "aesa")
public class VazaoVO {
	@Id
	@ColumnDefault("nextval('seq_id_vazao'::regclass)")
	@Column(name = "id_vazao", nullable = false)
	private Long id;

	@Column(name = "valorvazao")
	private BigDecimal valorvazao;

	@Column(name = "datavazao")
	private LocalDate datavazao;

	@OneToMany(mappedBy = "idVazao")
	private Set<AcudeVO> acudes = new LinkedHashSet<>();

}