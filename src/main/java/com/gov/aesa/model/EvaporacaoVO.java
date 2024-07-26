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
@Table(name = "evaporacao", schema = "aesa")
public class EvaporacaoVO {
	@Id
	@ColumnDefault("nextval('seq_id_evaporacao'::regclass)")
	@Column(name = "id_evaporacao", nullable = false)
	private Long id;

	@Column(name = "valorevaporacao")
	private BigDecimal valorevaporacao;

	@Column(name = "dataevaporacao")
	private LocalDate dataevaporacao;

	@OneToMany(mappedBy = "idEvaporacao")
	private Set<AcudeVO> acudes = new LinkedHashSet<>();

}