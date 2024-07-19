package com.gov.aesa.model;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Objects;
import jakarta.persistence.Basic;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "MEDICAO", schema = "AESA", catalog = "recursos_hidricos")
public class MedicaoVO {
	private Long id;
	private BigDecimal valor;
	private Timestamp data;
	private Integer sensorId;

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
	@Column(name = "valor", nullable = false, precision = 2)
	public BigDecimal getValor() {
		return valor;
	}

	public void setValor(BigDecimal valor) {
		this.valor = valor;
	}

	@Basic
	@Column(name = "data", nullable = true)
	public Timestamp getData() {
		return data;
	}

	public void setData(Timestamp data) {
		this.data = data;
	}

	@Basic
	@Column(name = "sensor_id", nullable = true)
	public Integer getSensorId() {
		return sensorId;
	}

	public void setSensorId(Integer sensorId) {
		this.sensorId = sensorId;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (o == null || getClass() != o.getClass())
			return false;
		MedicaoVO medicaoVO = (MedicaoVO) o;
		return id == medicaoVO.id && Objects.equals(valor, medicaoVO.valor) && Objects.equals(data, medicaoVO.data) && Objects.equals(sensorId, medicaoVO.sensorId);
	}

	@Override
	public int hashCode() {
		return Objects.hash(id, valor, data, sensorId);
	}
}
