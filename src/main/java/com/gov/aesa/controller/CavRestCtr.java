package com.gov.aesa.controller;


import com.gov.aesa.model.CavVO;
import com.gov.aesa.model.dtos.CavDTO;
import com.gov.aesa.retorno.RetornoAesa;
import com.gov.aesa.seguranca.JwtUtil;
import com.gov.aesa.service.CavRN;
import com.gov.aesa.util.AesaBaseCtr;
import io.swagger.v3.oas.annotations.ExternalDocumentation;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;


@RestController
@RequestMapping(CavRestCtr.BASE_URL)
@Tag(name = CavRestCtr.CAV_SERVIDOR, description = CavRestCtr.CAV_SERVIDOR_DESCRICAO)
public class CavRestCtr extends AesaBaseCtr {
    private static final Logger logger = LoggerFactory.getLogger(CavRestCtr.class);

    public static final String BASE_URL = "/api/v1/cav";
    static final String CAV_SERVIDOR = "CAV";
    public static final String CAV_SERVIDOR_DESCRICAO = "Servidor - Cav Servidor";
    public static final String CAV_DESCRICAO = "Esse endpoint gerencia os dados das CAV's Cota x Área x Volume";
    public static final String CAV_URL = URL_BASE_DOCUMENTACAO_REST + "/cav.MD";
    public static final String CAV_RESUMO = "Esse endpoint gerencia os dados das Cota x Área x Volume";


    @Autowired
    private CavRN cavRN;

    @Autowired
    private JwtUtil jwtUtil;

    @PostMapping("/criarCav")
    @Operation(summary = CAV_RESUMO, description = CAV_DESCRICAO, externalDocs = @ExternalDocumentation(description = MENSAGEM_PADRAO_PRELINK, url = CAV_URL))
    public ResponseEntity<RetornoAesa> criarcAV(@RequestBody CavDTO cavDTO) {
        try {
            CavVO cavVO = new CavVO();
            cavVO.setId_acude(cavDTO.getId_acude() != null ? cavDTO.getId_acude() : null);
            cavVO.setCota(cavDTO.getCota() != null ? cavDTO.getCota() : null);
            cavVO.setArea(cavDTO.getArea() != null ? cavDTO.getArea() : null);
            cavVO.setVolume(cavDTO.getVolume() != null ? cavDTO.getVolume() : null);


            RetornoAesa retornoAesa = cavRN.save(cavVO);
            if (retornoAesa != null && retornoAesa.getObjeto() instanceof CavVO) {
                CavVO savedCav = (CavVO) retornoAesa.getObjeto();
                CavDTO savedCavDTO = new CavDTO();
                savedCavDTO.setId(savedCav.getId() != null ? savedCav.getId() : 0);
                savedCavDTO.setId_acude(savedCav.getId_acude() != null ? savedCav.getId_acude() : 0);
                savedCavDTO.setCota(savedCav.getCota() != null ? savedCav.getCota() : null);
                savedCavDTO.setArea(savedCav.getArea() != null ? savedCav.getArea() : null);
                savedCavDTO.setVolume(savedCav.getVolume() != null ? savedCav.getVolume() : null);

                return ResponseEntity.status(HttpStatus.CREATED).body(gerarRetornoDeSucesso(savedCavDTO));
            } else {
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(retornoAesa);
            }
        } catch (Exception e) {
            logger.error("Erro ao criar CAV", e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(gerarRetornoErro(e.getMessage()));
        }
    }

    @GetMapping("/listarCavs")
    @Operation(summary = CAV_RESUMO, description = CAV_DESCRICAO, externalDocs = @ExternalDocumentation(description = MENSAGEM_PADRAO_PRELINK, url = CAV_URL))
    public ResponseEntity<RetornoAesa> getAllCavs() {
        try {
            RetornoAesa cavsRetorno = cavRN.findAll();

            if (cavsRetorno != null) {
                List<CavVO> cavs = (List<CavVO>) cavsRetorno.getObjeto();
                List<CavDTO> cavDTOs = cavs.stream().map(cavVO -> {
                    CavDTO dto = new CavDTO();
                    dto.setId(Long.valueOf(cavVO.getId() != null ? cavVO.getId() : 0));
                    dto.setId_acude(Long.valueOf(cavVO.getId_acude() != null ? cavVO.getId_acude() : 0));
                    dto.setCota(cavVO.getCota() != null ? cavVO.getCota() : null);
                    dto.setArea(cavVO.getArea() != null ? cavVO.getArea() : null);
                    dto.setVolume(cavVO.getVolume() != null ? cavVO.getVolume() : null);
                    return dto;
                }).collect(Collectors.toList());
                return ResponseEntity.ok(gerarRetornoDeSucesso(cavDTOs));
            } else {
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(cavsRetorno);
            }
        } catch (Exception e) {
            logger.error("Erro ao listar CAV's", e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(gerarRetornoErro(e.getMessage()));
        }
    }

    @GetMapping("/listarCavsPorIdAcude")
    @Operation(summary = CAV_RESUMO, description = CAV_DESCRICAO, externalDocs = @ExternalDocumentation(description = MENSAGEM_PADRAO_PRELINK, url = CAV_URL))
    public ResponseEntity<RetornoAesa> buscarCavsPorIdAcude(@RequestParam(name = "id_acude", required = true) Long id_acude) {
        try {
            RetornoAesa cavsRetorno = cavRN.buscarCavsPorIdAcude(id_acude);
            if (cavsRetorno != null) {
                List<CavVO> cavs = (List<CavVO>) cavsRetorno.getObjeto();
                List<CavDTO> cavDTOs = cavs.stream().map(cavVO -> {
                    CavDTO dto = new CavDTO();
                    dto.setId(Long.valueOf(cavVO.getId() != null ? cavVO.getId() : 0));
                    dto.setId_acude(Long.valueOf(cavVO.getId_acude() != null ? cavVO.getId_acude() : 0));
                    dto.setCota(cavVO.getCota() != null ? cavVO.getCota() : null);
                    dto.setArea(cavVO.getArea() != null ? cavVO.getArea() : null);
                    dto.setVolume(cavVO.getVolume() != null ? cavVO.getVolume() : null);
                    return dto;
                }).collect(Collectors.toList());
                return ResponseEntity.ok(gerarRetornoDeSucesso(cavDTOs));
            }else {
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(cavsRetorno);
            }
        } catch (Exception e) {
            logger.error("Erro ao buscar CAV por Id do Açude", e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(gerarRetornoErro(e.getMessage()));
        }
    }
}
