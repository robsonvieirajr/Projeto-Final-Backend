package com.gov.aesa.model;

import java.util.Objects;
import jakarta.persistence.Basic;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "SENSOR", schema = "AESA", catalog = "recursos_hidricos")
public class SensorVO {
	private Long id;
	private String tipo;
	private Integer acudeId;

	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Id
	@Column(name = "id", nullable = false)
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	@Basic
	@Column(name = "tipo", nullable = false, length = 50)
	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	@Basic
	@Column(name = "acude_id", nullable = true)
	public Integer getAcudeId() {
		return acudeId;
	}

	public void setAcudeId(Integer acudeId) {
		this.acudeId = acudeId;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (o == null || getClass() != o.getClass())
			return false;
		SensorVO sensorVO = (SensorVO) o;
		return id == sensorVO.id && Objects.equals(tipo, sensorVO.tipo) && Objects.equals(acudeId, sensorVO.acudeId);
	}

	@Override
	public int hashCode() {
		return Objects.hash(id, tipo, acudeId);
	}
}
