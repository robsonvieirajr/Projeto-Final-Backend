package com.gov.aesa.service;

import com.gov.aesa.model.EvaporacaoVO;
import com.gov.aesa.model.VazaoVO;
import com.gov.aesa.model.dtos.EvaporacaoDTO;
import com.gov.aesa.model.dtos.VazaoDTO;
import com.gov.aesa.repository.EvaporacaoRepository;
import com.gov.aesa.retorno.RetornoAesa;
import com.gov.aesa.util.AesaBaseCtr;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class EvaporacaoRN extends AesaBaseCtr {

    @Autowired
    private EvaporacaoRepository evaporacaoRepository;

    public RetornoAesa save(EvaporacaoVO evaporacao) {
        try {
            EvaporacaoVO savedEvaporacao = evaporacaoRepository.save(evaporacao);
            return gerarRetornoDeSucesso(evaporacao);
        } catch (Exception e) {
            return gerarRetornoErro(e.getMessage());
        }
    }

    public RetornoAesa findAll() {
        try {
            List<EvaporacaoVO> evaporacao = evaporacaoRepository.findAll();
            return gerarRetornoDeSucesso(evaporacao);
        } catch (Exception e) {
            return gerarRetornoErro(e.getMessage());
        }
    }

    public RetornoAesa buscarEvaporacaoPorIdAcude(Long id_acude) {
        try {
            List<EvaporacaoVO> evaporacoes = evaporacaoRepository.findAll();
            List<EvaporacaoVO> evaporacoesRetorno = new ArrayList<EvaporacaoVO>();
            for(EvaporacaoVO evaporacao : evaporacoes){
                if(evaporacao != null && evaporacao.getId_acude() == id_acude){
                    evaporacoesRetorno.add(evaporacao);
                }
            }
            return gerarRetornoDeSucesso(evaporacoesRetorno);
        } catch (Exception e) {
            return gerarRetornoErro(e.getMessage());
        }
    }

    public void editarEvaporacoes(List<EvaporacaoDTO> evaporacaoDTOList) throws Exception{
        for (EvaporacaoDTO evaporacaoDTO : evaporacaoDTOList) {
            // Iterar pelas evaporacaoDTOList
            //busca no banco as evaporações com o mesmo id da evaporacaoDTO
            Optional<EvaporacaoVO> optionalEvaporacaoVO = evaporacaoRepository.findById(evaporacaoDTO.getId());
            EvaporacaoVO evaporacaoVO = optionalEvaporacaoVO.orElse(null);

            //atualiza os dados do DTO
            evaporacaoVO.setId_acude(evaporacaoDTO.getId_acude());
            evaporacaoVO.setValorEvaporacao(evaporacaoDTO.getValorEvaporacao());
            evaporacaoVO.setAnoEvaporacao(evaporacaoDTO.getAnoEvaporacao());
            evaporacaoVO.setMesEvaporacao(evaporacaoDTO.getMesEvaporacao());


            // Salva o objeto no repositório
            evaporacaoRepository.save(evaporacaoVO);
        }
    }
}
