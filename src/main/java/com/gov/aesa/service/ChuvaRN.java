package com.gov.aesa.service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import com.gov.aesa.model.EvaporacaoVO;
import com.gov.aesa.model.VazaoVO;
import com.gov.aesa.repository.EvaporacaoRepository;
import com.gov.aesa.repository.VazaoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.gov.aesa.model.ChuvaVO;
import com.gov.aesa.model.dtos.AnoMensalDTO;
import com.gov.aesa.model.dtos.ChuvaDTO;
import com.gov.aesa.model.dtos.PrecipitacaoDTO;
import com.gov.aesa.repository.ChuvaRepository;
import com.gov.aesa.repository.DadosChuva;
import com.gov.aesa.util.AesaBaseCtr;

@Service
public class ChuvaRN extends AesaBaseCtr {

	@Autowired
	private DadosChuva dadosChuva;

	@Autowired
	private ChuvaRepository chuvaRepository;
	@Autowired
	private VazaoRepository vazaoRepository;
	@Autowired
	private EvaporacaoRepository evaporacaoRepository;

	public List<ChuvaDTO> obterDadosDeChuvaPorMunicipioEPeriodo(String municipio, String posto, String ano) {
		try {
			List<ChuvaDTO> listaChuvaDTO = new ArrayList<>();
			Map<String, Double> acumuladoPorMes = inicializarMapaDeMeses();
			Map<String, Double> entradasValidasPorMes = inicializarMapaDeMeses();
			int codigo = 0;

			// Iterar pelos meses e processar os dados de chuva
			for (int mes = 1; mes <= 12; mes++) {
				String mesFormatado = formatarMes(mes);
				String jsonResponse = dadosChuva.getDadosDeChuva(ano, mesFormatado);
				JsonNode rootNode = parseJsonResponse(jsonResponse);

				// Processa os dados mensais e atualiza o código
				codigo = processarDadosMensais(rootNode, municipio, posto, acumuladoPorMes, entradasValidasPorMes, codigo);
			}

			// Criar o objeto AnoMensal com as médias calculadas
			ChuvaDTO.AnoMensal anoMensal = calcularDadosMensais(acumuladoPorMes, entradasValidasPorMes, ano);

			// Preencher o ChuvaDTO final com os dados processados
			ChuvaDTO chuvaDTO = preencherChuvaDTO(municipio, posto, codigo, anoMensal);
			listaChuvaDTO.add(chuvaDTO);

			return listaChuvaDTO;

		} catch (Exception e) {
			return Collections.emptyList();
		}
	}

	private Map<String, Double> inicializarMapaDeMeses() {
		Map<String, Double> mapa = new LinkedHashMap<>();
		for (int mes = 1; mes <= 12; mes++) {
			String mesFormatado = String.format("%02d", mes);
			mapa.put(mesFormatado, 0.0); // Inicializa cada mês com valor 0.0
		}
		return mapa;
	}

	private String formatarMes(int mes) {
		return String.format("%02d", mes); // Formatar o mês com dois dígitos
	}

	private JsonNode parseJsonResponse(String jsonResponse) throws Exception {
		ObjectMapper mapper = new ObjectMapper();
		return mapper.readTree(jsonResponse); // Converte o JSON em uma árvore de nós
	}

	private int processarDadosMensais(JsonNode rootNode, String municipio, String posto, Map<String, Double> acumuladoPorMes, Map<String, Double> entradasValidasPorMes, int codigo) {
		if (rootNode.isArray()) {
			for (JsonNode dado : rootNode) {
				String municipioJson = dado.get("municipio").asText();
				String postoJson = dado.get("posto").asText();

				// Verifica se o município e o posto coincidem
				if (municipio.equalsIgnoreCase(municipioJson) && posto.equalsIgnoreCase(postoJson)) {
					codigo = dado.get("codigo").asInt(); // Obtém o código
					JsonNode dadosAnoArray = dado.get("data");
					if (dadosAnoArray != null && dadosAnoArray.isObject()) {
						processarChuvaMeses(dadosAnoArray, acumuladoPorMes, entradasValidasPorMes);
					}
				}
			}
		}
		return codigo;
	}

	private void processarChuvaMeses(JsonNode dadosAnoArray, Map<String, Double> acumuladoPorMes, Map<String, Double> entradasValidasPorMes) {
		for (Iterator<String> it = dadosAnoArray.fieldNames(); it.hasNext(); ) {
			String dataKey = it.next();
			double valor = dadosAnoArray.get(dataKey).asDouble();

			if (valor > 0.0) { // Ignorar valores 0.0
				String mesRegistro = dataKey.split("-")[1]; // Extrair o mês do formato "YYYY-MM"
				acumuladoPorMes.put(mesRegistro, acumuladoPorMes.getOrDefault(mesRegistro, 0.0) + valor);
				entradasValidasPorMes.put(mesRegistro, entradasValidasPorMes.getOrDefault(mesRegistro, 0.0) + 1);
			}
		}
	}

	private ChuvaDTO.AnoMensal calcularDadosMensais(Map<String, Double> acumuladoPorMes, Map<String, Double> entradasValidasPorMes, String ano) {
		ChuvaDTO.AnoMensal anoMensal = new ChuvaDTO.AnoMensal();
		anoMensal.setAno(ano);
		anoMensal.setDadosMensais(new ArrayList<>());

		// Calcular a média ou definir como 0.0 se não houver entradas
		for (int mes = 1; mes <= 12; mes++) {
			String mesFormatado = formatarMes(mes);
			ChuvaDTO.AnoMensal.DadoMensal dadoMensal = new ChuvaDTO.AnoMensal.DadoMensal();

			String mesAbreviado = getMesAbreviado(mesFormatado);
			dadoMensal.setMes(mesAbreviado);

			double acumulado = acumuladoPorMes.get(mesFormatado);
			Double entradas = entradasValidasPorMes.get(mesFormatado);
			double media = (entradas > 0) ? acumulado / entradas : 0.0;

			// Arredondar para duas casas decimais
			BigDecimal valorFormatado = BigDecimal.valueOf(media).setScale(2, RoundingMode.HALF_UP);
			dadoMensal.setValor(valorFormatado.doubleValue());

			anoMensal.getDadosMensais().add(dadoMensal);
		}
		return anoMensal;
	}

	private ChuvaDTO preencherChuvaDTO(String municipio, String posto, int codigo, ChuvaDTO.AnoMensal anoMensal) {
		ChuvaDTO chuvaDTO = new ChuvaDTO();
		chuvaDTO.setCodigo(codigo);
		chuvaDTO.setMunicipio(municipio);
		chuvaDTO.setEstacao(posto);
		chuvaDTO.setAnosMensais(List.of(anoMensal));
		return chuvaDTO;
	}

	private String getMesAbreviado(String mes) {
		switch (mes) {
		case "01":
			return "Jan";
		case "02":
			return "Fev";
		case "03":
			return "Mar";
		case "04":
			return "Abr";
		case "05":
			return "Mai";
		case "06":
			return "Jun";
		case "07":
			return "Jul";
		case "08":
			return "Ago";
		case "09":
			return "Set";
		case "10":
			return "Out";
		case "11":
			return "Nov";
		case "12":
			return "Dez";
		default:
			return mes;
		}
	}

	public void salvarChuva(List<PrecipitacaoDTO> precipitacaoDTOList) throws Exception {
		for (PrecipitacaoDTO dto : precipitacaoDTOList) {
			// Iterar pelos anosMensais
			for (AnoMensalDTO anoMensal : dto.getAnosMensais()) {
				// Iterar pelos meses e valores de chuva
				for (Map.Entry<String, BigDecimal> entry : anoMensal.getChuvaPorMes().entrySet()) {
					ChuvaVO chuvaVO = new ChuvaVO();
					EvaporacaoVO evaporacaoVO = new EvaporacaoVO();
					VazaoVO vazaoVO = new VazaoVO();

					chuvaVO.setIdAcude(dto.getIdAcude());
					chuvaVO.setMunicipio(dto.getMunicipio());
					chuvaVO.setEstacao(dto.getEstacao());
					chuvaVO.setAno(anoMensal.getAno());
					chuvaVO.setMes(entry.getKey());
					chuvaVO.setValorChuva(entry.getValue());

					//Insere os dados de Ano e Mês para Evaporação
					evaporacaoVO.setAnoEvaporacao(anoMensal.getAno());
					evaporacaoVO.setMesEvaporacao(entry.getKey());
					evaporacaoVO.setId_acude(dto.getIdAcude());

					//Insere os dados de Ano e Mês para Vazão
					vazaoVO.setAnoVazao(anoMensal.getAno());
					vazaoVO.setMesVazao(entry.getKey());
					vazaoVO.setId_acude(dto.getIdAcude());

					// Salva o objeto no repositório
					chuvaRepository.save(chuvaVO);

					// Salva o objeto no repositório
					evaporacaoRepository.save(evaporacaoVO);

					// Salva o objeto no repositório
					vazaoRepository.save(vazaoVO);

				}
			}
		}
	}

}