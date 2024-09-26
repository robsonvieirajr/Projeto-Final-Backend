package com.gov.aesa.service;

import com.gov.aesa.model.ChuvaVO;
import com.gov.aesa.model.EvaporacaoVO;
import com.gov.aesa.model.VazaoVO;
import com.gov.aesa.model.dtos.AnoMensalDTO;
import com.gov.aesa.model.dtos.PrecipitacaoDTO;
import com.gov.aesa.model.dtos.VazaoDTO;
import com.gov.aesa.repository.VazaoRepository;
import com.gov.aesa.retorno.RetornoAesa;
import com.gov.aesa.util.AesaBaseCtr;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class VazaoRN extends AesaBaseCtr {

    @Autowired
    private VazaoRepository vazaoRepository;

    public RetornoAesa save(VazaoVO vazao) {
        try {
            VazaoVO savedVazao = vazaoRepository.save(vazao);
            return gerarRetornoDeSucesso(vazao);
        } catch (Exception e) {
            return gerarRetornoErro(e.getMessage());
        }
    }

    public RetornoAesa findAll() {
        try {
            List<VazaoVO> vazoes = vazaoRepository.findAll();
            return gerarRetornoDeSucesso(vazoes);
        } catch (Exception e) {
            return gerarRetornoErro(e.getMessage());
        }
    }

    public RetornoAesa findById(Long id) {
        try {
            Optional<VazaoVO> vazaoVO = vazaoRepository.findById(id);
            return gerarRetornoDeSucesso(vazaoVO);
        } catch (Exception e) {
            return gerarRetornoErro(e.getMessage());
        }
    }

    public RetornoAesa buscarVazoesPorIdAcude(Long id_acude) {
        try {
            List<VazaoVO> vazoes = vazaoRepository.findAll();
            List<VazaoVO> vazoesRetorno = new ArrayList<VazaoVO>();
            for(VazaoVO vazao : vazoes){
                if(vazao != null && vazao.getId_acude() == id_acude){
                    vazoesRetorno.add(vazao);
                }
            }
            return gerarRetornoDeSucesso(vazoesRetorno);
        } catch (Exception e) {
            return gerarRetornoErro(e.getMessage());
        }
    }

    public void editarVazoes(List<VazaoDTO> vazoesDTOList) throws Exception{
        for (VazaoDTO vazaoDTO : vazoesDTOList) {
            // Iterar pelas vazoesDTO
            //busca no banco as vazões com o mesmo id da vazaoDTO
            Optional<VazaoVO> optionalVazaoVO = vazaoRepository.findById(vazaoDTO.getId());
            VazaoVO vazaoVO = optionalVazaoVO.orElse(null);

            //atualiza os dados do DTO
            vazaoVO.setId_acude(vazaoDTO.getId_acude());
            vazaoVO.setValorVazao(vazaoDTO.getValorVazao());
            vazaoVO.setAnoVazao(vazaoDTO.getAnoVazao());
            vazaoVO.setMesVazao(vazaoDTO.getMesVazao());


            // Salva o objeto no repositório
            vazaoRepository.save(vazaoVO);
        }
    }

}
