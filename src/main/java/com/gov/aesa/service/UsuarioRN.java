package com.gov.aesa.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.gov.aesa.exceptions.ViolacaoDeRegraEx;
import com.gov.aesa.model.UsuarioVO;
import com.gov.aesa.model.dtos.NovaSenhaDTO;
import com.gov.aesa.repository.UsuarioRepository;
import com.gov.aesa.util.AesaBaseCtr;

@Service
public class UsuarioRN extends AesaBaseCtr {

	@Autowired
	private UsuarioRepository usuarioRepository;

	public UsuarioVO autenticar(String cpf, String senha) {
		String cpfSemMascara = cpf.replaceAll("[^0-9]", "");
		UsuarioVO usuario = usuarioRepository.findByCpf(cpfSemMascara);
		if (usuario != null && usuario.getSenha().equals(senha)) {
			return usuario;
		}
		return null;
	}

	public void redefinirSenha(NovaSenhaDTO novaSenhaDTO) {
		String cpf = novaSenhaDTO.getCpf();
		String novaSenha = novaSenhaDTO.getNovaSenha();

		if (cpf == null || cpf.isEmpty()) {
			throw new ViolacaoDeRegraEx("O CPF não pode ser nulo ou vazio");
		}
		if (novaSenha == null || novaSenha.isEmpty()) {
			throw new ViolacaoDeRegraEx("A nova senha não pode ser nula ou vazia");
		}

		UsuarioVO usuario = usuarioRepository.findByCpf(cpf);
		if (usuario == null) {
			throw new ViolacaoDeRegraEx("Usuário não encontrado");
		}
		if (usuario.getSenha().equalsIgnoreCase(novaSenha)) {
			throw new ViolacaoDeRegraEx("A nova senha não pode ser a mesma que a senha atual");
		}
		usuario.setToken(null);
		usuario.setSenha(novaSenha);
		usuarioRepository.save(usuario);
	}
}
