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
@Table(name = "ACUDE", schema = "AESA", catalog = "recursos_hidricos")
public class AcudeVO {
	private Long id;
	private String nome;
	private String localizacao;

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
	@Column(name = "nome", nullable = false, length = 100)
	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	@Basic
	@Column(name = "localizacao", nullable = false, length = 100)
	public String getLocalizacao() {
		return localizacao;
	}

	public void setLocalizacao(String localizacao) {
		this.localizacao = localizacao;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (o == null || getClass() != o.getClass())
			return false;
		AcudeVO acudeVO = (AcudeVO) o;
		return id == acudeVO.id && Objects.equals(nome, acudeVO.nome) && Objects.equals(localizacao, acudeVO.localizacao);
	}

	@Override
	public int hashCode() {
		return Objects.hash(id, nome, localizacao);
	}
}
