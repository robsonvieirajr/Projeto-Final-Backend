package com.gov.aesa.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.gov.aesa.model.AcudeVO;
import com.gov.aesa.model.dtos.AcudeDTO;
import com.gov.aesa.retorno.RetornoAesa;
import com.gov.aesa.seguranca.JwtUtil;
import com.gov.aesa.service.AcudeRN;
import com.gov.aesa.util.AesaBaseCtr;
import io.swagger.v3.oas.annotations.ExternalDocumentation;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(AcudeRestCtr.BASE_URL)
@Tag(name = AcudeRestCtr.ACUDE_SERVIDOR, description = AcudeRestCtr.ACUDE_SERVIDOR_DESCRICAO)
public class AcudeRestCtr extends AesaBaseCtr {

	private static final Logger logger = LoggerFactory.getLogger(AcudeRestCtr.class);

	public static final String BASE_URL = "/api/v1/acude";
	static final String ACUDE_SERVIDOR = "Acude";
	public static final String ACUDE_SERVIDOR_DESCRICAO = "Servidor - Acude Servidor";
	public static final String ACUDE_DESCRICAO = "Esse endpoint gerencia os dados dos açudes";
	public static final String ACUDE_URL = URL_BASE_DOCUMENTACAO_REST + "/acude.MD";
	public static final String ACUDE_RESUMO = "Esse endpoint gerencia os dados dos açudes";

	@Autowired
	private AcudeRN acudeRN;

	@Autowired
	private JwtUtil jwtUtil;

	@PostMapping("/criarAcudes")
	@Operation(summary = ACUDE_RESUMO, description = ACUDE_DESCRICAO, externalDocs = @ExternalDocumentation(description = MENSAGEM_PADRAO_PRELINK, url = ACUDE_URL))
	public ResponseEntity<RetornoAesa> criarAcudes(@RequestBody AcudeDTO acudeDTO) {
		RetornoAesa retorno = new RetornoAesa();
		try {
			AcudeVO acudeVO = new AcudeVO();
			acudeVO.setNome(acudeDTO.getNome());
			acudeVO.setLocalizacao(acudeDTO.getLocalizacao());
			AcudeVO savedAcude = acudeRN.save(acudeVO);
			AcudeDTO savedAcudeDTO = new AcudeDTO();
			savedAcudeDTO.setId(savedAcude.getId());
			savedAcudeDTO.setNome(savedAcude.getNome());
			savedAcudeDTO.setLocalizacao(savedAcude.getLocalizacao());

			retorno.setObjeto(savedAcudeDTO);
			retorno.setMensagem("Açude criado com sucesso");
			return ResponseEntity.status(HttpStatus.CREATED).body(retorno);
		} catch (Exception e) {
			logger.error("Erro ao criar açude", e);
			retorno.setMensagem("Erro interno no servidor");
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(retorno);
		}
	}

	@GetMapping("/listarAcudes")
	@Operation(summary = ACUDE_RESUMO, description = ACUDE_DESCRICAO, externalDocs = @ExternalDocumentation(description = MENSAGEM_PADRAO_PRELINK, url = ACUDE_URL))
	public ResponseEntity<RetornoAesa> getAllAcudes() {
		RetornoAesa retorno = new RetornoAesa();
		try {
			List<AcudeVO> acudes = acudeRN.findAll();
			List<AcudeDTO> acudeDTOs = acudes.stream().map(acudeVO -> {
				AcudeDTO dto = new AcudeDTO();
				dto.setId(acudeVO.getId());
				dto.setNome(acudeVO.getNome());
				dto.setLocalizacao(acudeVO.getLocalizacao());
				return dto;
			}).collect(Collectors.toList());

			retorno.setObjeto(acudeDTOs);
			retorno.setMensagem("Lista de açudes obtida com sucesso");
			return ResponseEntity.ok(retorno);
		} catch (Exception e) {
			logger.error("Erro ao listar açudes", e);
			retorno.setMensagem("Erro interno no servidor");
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(retorno);
		}
	}
}
