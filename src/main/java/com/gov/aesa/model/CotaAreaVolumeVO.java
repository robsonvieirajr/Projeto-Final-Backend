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
@Table(name = "cota_area_volume", schema = "aesa")
public class CotaAreaVolumeVO {
	@Id
	@ColumnDefault("nextval('seq_id_cota_area_volume'::regclass)")
	@Column(name = "id_cota_area_volume", nullable = false)
	private Long id;

	@Column(name = "cota")
	private BigDecimal cota;

	@Column(name = "area")
	private BigDecimal area;

	@Column(name = "volume")
	private BigDecimal volume;

	@Column(name = "datacav")
	private LocalDate datacav;

	@OneToMany(mappedBy = "idCotaAreaVolume")
	private Set<AcudeVO> acudes = new LinkedHashSet<>();

}