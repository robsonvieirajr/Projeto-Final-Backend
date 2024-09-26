package com.gov.aesa.service;


import com.gov.aesa.model.CavVO;
import com.gov.aesa.repository.CavRepository;
import com.gov.aesa.retorno.RetornoAesa;
import com.gov.aesa.util.AesaBaseCtr;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CavRN extends AesaBaseCtr {

    @Autowired
    private CavRepository cavRepository;

    public RetornoAesa save(CavVO cav) {
        try {
            CavVO savedCav = cavRepository.save(cav);
            return gerarRetornoDeSucesso(cav);
        } catch (Exception e) {
            return gerarRetornoErro(e.getMessage());
        }
    }

    public RetornoAesa findAll() {
        try {
            List<CavVO> cavs = cavRepository.findAll();
            return gerarRetornoDeSucesso(cavs);
        } catch (Exception e) {
            return gerarRetornoErro(e.getMessage());
        }
    }

    public RetornoAesa buscarCavsPorIdAcude(Long id_acude) {
        try {
            List<CavVO> cavs = cavRepository.findAll();
            List<CavVO> cavsRetorno = new ArrayList<CavVO>();

            for(CavVO cav : cavs){
                if(cav != null && cav.getId_acude() == id_acude){
                    cavsRetorno.add(cav);
                }
            }
            return gerarRetornoDeSucesso(cavsRetorno);
        } catch (Exception e) {
            return gerarRetornoErro(e.getMessage());
        }
    }
}
