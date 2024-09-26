package com.gov.aesa.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.gov.aesa.model.AcudeVO;
import com.gov.aesa.model.dtos.AcudeDTO;
import com.gov.aesa.repository.AcudeRepository;
import com.gov.aesa.repository.ChuvaRepository;
import com.gov.aesa.repository.DadosChuva;
import com.gov.aesa.retorno.RetornoAesa;
import com.gov.aesa.util.AesaBaseCtr;

@Service
public class AcudeRN extends AesaBaseCtr {

	@Autowired
	private AcudeRepository acudeRepository;

	@Autowired
	private DadosChuva dadosChuva;

	@Autowired
	private ChuvaRepository chuvaRepository;

	public RetornoAesa save(AcudeVO acude) {
		try {
			AcudeVO savedAcude = acudeRepository.save(acude);
			return gerarRetornoDeSucesso(savedAcude);
		} catch (Exception e) {
			return gerarRetornoErro(e.getMessage());
		}
	}

	public RetornoAesa findAll() {
		try {
			List<AcudeVO> acudes = acudeRepository.findAll();
			return gerarRetornoDeSucesso(acudes);
		} catch (Exception e) {
			return gerarRetornoErro(e.getMessage());
		}
	}

	public RetornoAesa buscarAcudePorNome(String nome) {
		try {
			AcudeVO acude = acudeRepository.findByNome(nome);
			if (acude != null && acude.getNome().equalsIgnoreCase(nome)) {
				AcudeDTO acudeDTO = new AcudeDTO();
				acudeDTO.setId(Long.valueOf(acude.getId()));
				acudeDTO.setNome(acude.getNome());
				return gerarRetornoDeSucesso(acudeDTO);
			} else {
				return gerarRetornoNaoEncontrado("Açude não encontrado.");
			}
		} catch (Exception e) {
			return gerarRetornoErro(e.getMessage());
		}
	}
}