package com.gov.aesa.model;

import java.sql.Date;
import java.util.Objects;
import jakarta.persistence.Basic;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "MANEJO", schema = "AESA", catalog = "recursos_hidricos")
public class ManejoVO {
	private Long id;
	private String descricao;
	private Date data;

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
	@Column(name = "descricao", nullable = true, length = -1)
	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	@Basic
	@Column(name = "data", nullable = true)
	public Date getData() {
		return data;
	}

	public void setData(Date data) {
		this.data = data;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (o == null || getClass() != o.getClass())
			return false;
		ManejoVO manejoVO = (ManejoVO) o;
		return id == manejoVO.id && Objects.equals(descricao, manejoVO.descricao) && Objects.equals(data, manejoVO.data);
	}

	@Override
	public int hashCode() {
		return Objects.hash(id, descricao, data);
	}
}
