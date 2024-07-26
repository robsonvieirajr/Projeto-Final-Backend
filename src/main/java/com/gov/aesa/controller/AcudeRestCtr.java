package com.gov.aesa.controller;

import java.util.List;
import java.util.stream.Collectors;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
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
		try {
			AcudeVO acudeVO = new AcudeVO();
			acudeVO.setNome(acudeDTO.getNome());
			RetornoAesa savedAcudeRetorno = acudeRN.save(acudeVO);

			if (savedAcudeRetorno != null)  {
				AcudeVO savedAcude = (AcudeVO) savedAcudeRetorno.getObjeto();
				AcudeDTO savedAcudeDTO = new AcudeDTO();
				savedAcudeDTO.setId(Long.valueOf(savedAcude.getId()));
				savedAcudeDTO.setNome(savedAcude.getNome());
				return ResponseEntity.status(HttpStatus.CREATED).body(gerarRetornoDeSucesso(savedAcudeDTO));
			} else {
				return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(savedAcudeRetorno);
			}
		} catch (Exception e) {
			logger.error("Erro ao criar açude", e);
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(gerarRetornoErro(e.getMessage()));
		}
	}

	@GetMapping("/listarAcudes")
	@Operation(summary = ACUDE_RESUMO, description = ACUDE_DESCRICAO, externalDocs = @ExternalDocumentation(description = MENSAGEM_PADRAO_PRELINK, url = ACUDE_URL))
	public ResponseEntity<RetornoAesa> getAllAcudes() {
		try {
			RetornoAesa acudesRetorno = acudeRN.findAll();

			if (acudesRetorno != null) {
				List<AcudeVO> acudes = (List<AcudeVO>) acudesRetorno.getObjeto();
				List<AcudeDTO> acudeDTOs = acudes.stream().map(acudeVO -> {
					AcudeDTO dto = new AcudeDTO();
					dto.setId(Long.valueOf(acudeVO.getId() != null ? acudeVO.getId() : 0));
					dto.setNome(acudeVO.getNome() != null ? acudeVO.getNome() : "");
					dto.setDataPedido(acudeVO.getDataPedido() != null ? acudeVO.getDataPedido() : null);
					dto.setVolumeMorto(acudeVO.getVolumeMorto() != null ? acudeVO.getVolumeMorto() : null);
					dto.setVolumeAcumulado(acudeVO.getVolumeAcumulado() != null ? acudeVO.getVolumeAcumulado() : null);
					dto.setAreaDrenagem(acudeVO.getAreaDrenagem() != null ? acudeVO.getAreaDrenagem() : null);
					dto.setCoeficienteTanque(acudeVO.getCoeficienteTanque() != null ? acudeVO.getCoeficienteTanque() : null);
					return dto;
				}).collect(Collectors.toList());
				return ResponseEntity.ok(gerarRetornoDeSucesso(acudeDTOs));
			} else {
				return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(acudesRetorno);
			}
		} catch (Exception e) {
			logger.error("Erro ao listar açudes", e);
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(gerarRetornoErro(e.getMessage()));
		}
	}

	@GetMapping("/buscarAcudePorNome")
	@Operation(summary = ACUDE_RESUMO, description = ACUDE_DESCRICAO, externalDocs = @ExternalDocumentation(description = MENSAGEM_PADRAO_PRELINK, url = ACUDE_URL))
	public ResponseEntity<RetornoAesa> buscaAcudePorNome(@RequestParam(name = "nome", required = true) String nome) {
		try {
			RetornoAesa acudeRetorno = acudeRN.buscarAcudePorNome(nome);
			return ResponseEntity.ok(acudeRetorno);
		} catch (Exception e) {
			logger.error("Erro ao buscar açude por nome", e);
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(gerarRetornoErro(e.getMessage()));
		}
	}
}
