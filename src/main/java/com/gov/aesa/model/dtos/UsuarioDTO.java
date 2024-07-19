package com.gov.aesa.model.dtos;

import lombok.Data;

@Data
public class UsuarioDTO {
	private String cpf;
	private String senha;

	public UsuarioDTO() {
		super();
	}
}
