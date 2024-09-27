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
import java.util.HashMap;
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
            List<VazaoVO> vazoesRetorno = new ArrayList<>();

            Map<String, Integer> mesesMap = new HashMap<>();
            mesesMap.put("Jan", 1);
            mesesMap.put("Fev", 2);
            mesesMap.put("Mar", 3);
            mesesMap.put("Abr", 4);
            mesesMap.put("Mai", 5);
            mesesMap.put("Jun", 6);
            mesesMap.put("Jul", 7);
            mesesMap.put("Ago", 8);
            mesesMap.put("Set", 9);
            mesesMap.put("Out", 10);
            mesesMap.put("Nov", 11);
            mesesMap.put("Dez", 12);

            for (VazaoVO vazao : vazoes) {
                if (vazao != null && vazao.getId_acude().equals(id_acude)) {
                    vazoesRetorno.add(vazao);
                }
            }

            vazoesRetorno.sort((v1, v2) -> {
                String mes1 = v1.getMesVazao().substring(0, 1).toUpperCase() + v1.getMesVazao().substring(1).toLowerCase();
                String mes2 = v2.getMesVazao().substring(0, 1).toUpperCase() + v2.getMesVazao().substring(1).toLowerCase();

                Integer ordemMes1 = mesesMap.get(mes1);
                Integer ordemMes2 = mesesMap.get(mes2);

                if (ordemMes1 == null || ordemMes2 == null) {
                    return (ordemMes1 == null) ? 1 : -1;
                }

                return ordemMes1 - ordemMes2;
            });

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
