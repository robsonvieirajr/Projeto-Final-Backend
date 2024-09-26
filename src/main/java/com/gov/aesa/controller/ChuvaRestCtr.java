package com.gov.aesa.controller;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.gov.aesa.model.dtos.ChuvaDTO;
import com.gov.aesa.model.dtos.PrecipitacaoDTO;
import com.gov.aesa.retorno.RetornoAesa;
import com.gov.aesa.seguranca.JwtUtil;
import com.gov.aesa.service.ChuvaRN;
import com.gov.aesa.util.AesaBaseCtr;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping(ChuvaRestCtr.BASE_URL)
@Tag(name = ChuvaRestCtr.DADOS_CHUVA, description = ChuvaRestCtr.CHUVA_DESCRICAO)
public class ChuvaRestCtr extends AesaBaseCtr {

	private static final Logger logger = LoggerFactory.getLogger(ChuvaRestCtr.class);

	public static final String BASE_URL = "/api/v1/chuva";
	static final String DADOS_CHUVA = "Dados de Chuva";
	public static final String CHUVA_DESCRICAO = "Chuva";

	@Autowired
	private ChuvaRN chuvaRN;

	@Autowired
	private JwtUtil jwtUtil;

	@GetMapping("/importarChuvas")
	@Operation(summary = "Importar Dados de Chuvas", description = "Este endpoint importa dados de chuvas por município, posto e período selecionado.")
	public ResponseEntity<RetornoAesa> importarDadosDeChuvas(@RequestParam String municipio, @RequestParam String posto, @RequestParam String anoInicial, @RequestParam String anoFinal) {

		try {
			List<ChuvaDTO> dadosChuva = new ArrayList<>();
			for (int ano = Integer.parseInt(anoInicial); ano <= Integer.parseInt(anoFinal); ano++) {
				List<ChuvaDTO> chuvasDoAno = chuvaRN.obterDadosDeChuvaPorMunicipioEPeriodo(municipio, posto, String.valueOf(ano));
				dadosChuva.addAll(chuvasDoAno);
			}
			return ResponseEntity.ok(gerarRetornoDeSucesso(dadosChuva));
		} catch (Exception e) {
			logger.error("Erro ao importar dados de chuvas", e);
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(gerarRetornoErro(e.getMessage()));
		}
	}

	@CrossOrigin(origins = "http://localhost:3000", allowCredentials = "true")
	@PostMapping("/salvarChuvas")
	@Operation(summary = "Salvar Dados de Chuvas", description = "Este endpoint salva os dados de chuvas no banco de dados.")
	public ResponseEntity<RetornoAesa> salvarDadosDeChuvas(@RequestBody List<PrecipitacaoDTO> precipitacaoDTOS) {
		try {
			for (PrecipitacaoDTO precipitacaoDTO : precipitacaoDTOS) {
				chuvaRN.salvarChuva(Collections.singletonList(precipitacaoDTO));
			}
			return ResponseEntity.ok(gerarRetornoDeSucesso("Dados de chuva salvos com sucesso."));
		} catch (Exception e) {
			logger.error("Erro ao salvar dados de chuvas", e);
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(gerarRetornoErro(e.getMessage()));
		}
	}
}