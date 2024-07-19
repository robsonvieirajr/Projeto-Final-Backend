package com.gov.aesa.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.gov.aesa.exceptions.ViolacaoDeRegraEx;
import com.gov.aesa.model.UsuarioVO;
import com.gov.aesa.model.dtos.NovaSenhaDTO;
import com.gov.aesa.model.dtos.UsuarioDTO;
import com.gov.aesa.retorno.RetornaDadosUsuarioServidorSchema;
import com.gov.aesa.retorno.RetornoAesa;
import com.gov.aesa.seguranca.JwtUtil;
import com.gov.aesa.service.UsuarioRN;
import com.gov.aesa.util.AesaBaseCtr;
import io.swagger.v3.oas.annotations.ExternalDocumentation;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping(UsuarioRestCtr.BASE_URL)
@Tag(name = UsuarioRestCtr.USUARIO_SERVIDOR, description = UsuarioRestCtr.USUARIO_SERVIDOR_DESCRICAO)
public class UsuarioRestCtr extends AesaBaseCtr {

	private static final Logger logger = LoggerFactory.getLogger(UsuarioRestCtr.class);

	// Constantes gerais
	public static final String BASE_URL = "/api/v1/usuario";
	static final String USUARIO_SERVIDOR = "Usuario";
	public static final String USUARIO_SERVIDOR_DESCRICAO = "Servidor - Usuário Servidor";
	public static final String USUARIO_DESCRICAO = "Esse endpoint grava os dados do usuário do servidor";
	public static final String USUARIO_URL = URL_BASE_DOCUMENTACAO_REST + "/usuario.MD";
	public static final String USUARIO_RESUMO = "Esse endpoint grava os dados do usuário do servidor utilizando o cpf";

	// Constantes para a operação de redefinição de senha
	public static final String RESET_SENHA_SUMARIO = "Solicitar redefinição de senha";
	public static final String RESET_SENHA_DESCRICAO = "Esse endpoint permite que o usuário solicite a redefinição de senha utilizando o cpf";
	public static final String RESET_SENHA_URL = URL_BASE_DOCUMENTACAO_REST + "/reset-senha.MD";
	public static final String RESET_SENHA_RESUMO = "Solicitação de redefinição de senha utilizando o cpf";

	@Autowired
	private UsuarioRN usuarioRN;

	@Autowired
	private JwtUtil jwtUtil;

	@PostMapping("/login")
	@Operation(summary = USUARIO_RESUMO, description = USUARIO_DESCRICAO, externalDocs = @ExternalDocumentation(description = MENSAGEM_PADRAO_PRELINK, url = USUARIO_URL))
	public ResponseEntity<RetornaDadosUsuarioServidorSchema> login(@RequestBody UsuarioDTO usuarioDTO) {
		RetornaDadosUsuarioServidorSchema ret = new RetornaDadosUsuarioServidorSchema();

		try {
			UsuarioVO usuarioVO = usuarioRN.autenticar(usuarioDTO.getCpf(), usuarioDTO.getSenha());
			if (usuarioVO != null) {
				String token = jwtUtil.gerarToken(usuarioDTO.getCpf());
				usuarioVO.setToken(token);

				UsuarioDTO usuarioDTOResponse = new UsuarioDTO();
				usuarioDTOResponse.setCpf(usuarioVO.getCpf());
				ret.setObjeto(usuarioDTOResponse);
				ret.setToken(token);
				ret.setMensagemExibicao("Usuário autenticado com sucesso");
				return ResponseEntity.ok(ret);
			} else {
				ret.setMensagemExibicao("Credenciais inválidas");
				return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(ret);
			}
		} catch (Exception e) {
			logger.error("Erro ao autenticar usuário", e);
			ret.setMensagemExibicao("Erro interno no servidor");
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(ret);
		}
	}


	@PostMapping("/redefinir-senha")
	@Operation(summary = RESET_SENHA_SUMARIO, description = RESET_SENHA_DESCRICAO, externalDocs = @ExternalDocumentation(description = MENSAGEM_PADRAO_PRELINK, url = RESET_SENHA_URL))
	public ResponseEntity<RetornoAesa> redefinirSenha(@RequestBody NovaSenhaDTO novaSenhaDTO) {
		RetornoAesa ret = new RetornoAesa();
		try {
			usuarioRN.redefinirSenha(novaSenhaDTO);
			ret.setMensagem("Senha redefinida com sucesso.");
			return ResponseEntity.ok(ret);
		} catch (ViolacaoDeRegraEx e) {
			ret.setMensagem(e.getMessage());
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ret);
		} catch (Exception e) {
			logger.error("Erro ao redefinir senha", e);
			ret.setMensagem("Erro ao redefinir senha.");
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(ret);
		}
	}
}
