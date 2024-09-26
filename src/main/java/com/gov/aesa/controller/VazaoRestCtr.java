package com.gov.aesa.controller;

import com.gov.aesa.model.VazaoVO;
import com.gov.aesa.model.dtos.PrecipitacaoDTO;
import com.gov.aesa.model.dtos.VazaoDTO;
import com.gov.aesa.retorno.RetornoAesa;
import com.gov.aesa.seguranca.JwtUtil;
import com.gov.aesa.service.VazaoRN;
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

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(VazaoRestCtr.BASE_URL)
@Tag(name = VazaoRestCtr.VAZAO_SERVIDOR, description = VazaoRestCtr.VAZAO_SERVIDOR_DESCRICAO)
public class VazaoRestCtr extends AesaBaseCtr {
    private static final Logger logger = LoggerFactory.getLogger(VazaoRestCtr.class);

    public static final String BASE_URL = "/api/v1/vazao";
    static final String VAZAO_SERVIDOR = "VAZAO";
    public static final String VAZAO_SERVIDOR_DESCRICAO = "Servidor - Vazao Servidor";
    public static final String VAZAO_DESCRICAO = "Esse endpoint gerencia os dados das Vazões Afluentes";
    public static final String VAZAO_URL = URL_BASE_DOCUMENTACAO_REST + "/vazao.MD";
    public static final String VAZAO_RESUMO = "Esse endpoint gerencia os dados das Cota x Área x Volume";


    @Autowired
    private VazaoRN vazaoRN;

    @Autowired
    private JwtUtil jwtUtil;

    @PostMapping("/criarVazao")
    @Operation(summary = VAZAO_RESUMO, description = VAZAO_DESCRICAO, externalDocs = @ExternalDocumentation(description = MENSAGEM_PADRAO_PRELINK, url = VAZAO_URL))
    public ResponseEntity<RetornoAesa> criarVazao(@RequestBody VazaoDTO vazaoDTO) {
        try {
            VazaoVO vazaoVO = new VazaoVO();
            vazaoVO.setId_acude(vazaoDTO.getId_acude() != null ? vazaoDTO.getId_acude() : null);
            vazaoVO.setMesVazao(vazaoDTO.getMesVazao() != null ? vazaoDTO.getMesVazao() : null);
            vazaoVO.setAnoVazao(vazaoDTO.getAnoVazao() != null ? vazaoDTO.getAnoVazao() : null);
            vazaoVO.setValorVazao(vazaoDTO.getValorVazao() != null ? vazaoDTO.getValorVazao() : null);


            RetornoAesa retornoAesa = vazaoRN.save(vazaoVO);
            if (retornoAesa != null && retornoAesa.getObjeto() instanceof VazaoVO) {
                VazaoVO savedVazao = (VazaoVO) retornoAesa.getObjeto();
                VazaoDTO savedVazaoDTO = new VazaoDTO();
                savedVazaoDTO.setId(savedVazao.getId() != null ? savedVazao.getId() : 0);
                savedVazaoDTO.setId_acude(savedVazao.getId_acude() != null ? savedVazao.getId_acude() : 0);
                savedVazaoDTO.setMesVazao(savedVazao.getMesVazao() != null ? savedVazao.getMesVazao() : null);
                savedVazaoDTO.setAnoVazao(savedVazao.getAnoVazao() != null ? savedVazao.getAnoVazao() : null);
                savedVazaoDTO.setValorVazao(savedVazao.getValorVazao()!= null ? savedVazao.getValorVazao() : null);


                return ResponseEntity.status(HttpStatus.CREATED).body(gerarRetornoDeSucesso(savedVazaoDTO));
            } else {
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(retornoAesa);
            }
        } catch (Exception e) {
            logger.error("Erro ao criar Vazão", e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(gerarRetornoErro(e.getMessage()));
        }
    }

    @GetMapping("/listarVazoes")
    @Operation(summary = VAZAO_RESUMO, description = VAZAO_DESCRICAO, externalDocs = @ExternalDocumentation(description = MENSAGEM_PADRAO_PRELINK, url = VAZAO_URL))
    public ResponseEntity<RetornoAesa> getAllVazoes() {
        try {
            RetornoAesa vazoesRetorno = vazaoRN.findAll();

            if (vazoesRetorno != null) {
                List<VazaoVO> vazoes = (List<VazaoVO>) vazoesRetorno.getObjeto();
                List<VazaoDTO> vazaoDTOs = vazoes.stream().map(vazaoVO -> {
                    VazaoDTO dto = new VazaoDTO();
                    dto.setId(Long.valueOf(vazaoVO.getId() != null ? vazaoVO.getId() : 0));
                    dto.setId_acude(Long.valueOf(vazaoVO.getId_acude() != null ? vazaoVO.getId_acude() : 0));
                    dto.setMesVazao(vazaoVO.getMesVazao() != null ? vazaoVO.getMesVazao() : null);
                    dto.setAnoVazao(vazaoVO.getAnoVazao() != null ? vazaoVO.getAnoVazao() : null);
                    dto.setValorVazao(vazaoVO.getValorVazao() != null ? vazaoVO.getValorVazao() : null);
                    return dto;
                }).collect(Collectors.toList());
                return ResponseEntity.ok(gerarRetornoDeSucesso(vazaoDTOs));
            } else {
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(vazoesRetorno);
            }
        } catch (Exception e) {
            logger.error("Erro ao listar CAV's", e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(gerarRetornoErro(e.getMessage()));
        }
    }

    @GetMapping("/listarVazoesPorIdAcude")
    @Operation(summary = VAZAO_RESUMO, description = VAZAO_DESCRICAO, externalDocs = @ExternalDocumentation(description = MENSAGEM_PADRAO_PRELINK, url = VAZAO_URL))
    public ResponseEntity<RetornoAesa> buscaVazoesPorIdAcude(@RequestParam(name = "id_acude", required = true) Long id_acude) {
        try {
            RetornoAesa vazoesRetorno = vazaoRN.buscarVazoesPorIdAcude(id_acude);
            if (vazoesRetorno != null) {
                return ResponseEntity.ok(vazoesRetorno);
            }else {
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(vazoesRetorno);
            }
        } catch (Exception e) {
            logger.error("Erro ao buscar Vazões por Id do Açude", e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(gerarRetornoErro(e.getMessage()));
        }
    }

    @PutMapping("/editarVazoes")
    @Operation(summary = VAZAO_RESUMO, description = VAZAO_DESCRICAO, externalDocs = @ExternalDocumentation(description = MENSAGEM_PADRAO_PRELINK, url = VAZAO_URL))
    public ResponseEntity<RetornoAesa> editarVazoes(@RequestBody List<VazaoDTO> vazoesDTO) {
        try {
            vazaoRN.editarVazoes(vazoesDTO);
            return ResponseEntity.ok(gerarRetornoDeSucesso("Dados de Vazões salvos com sucesso."));
        } catch (Exception e) {
            logger.error("Erro ao editar dados de Vazões", e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(gerarRetornoErro(e.getMessage()));
        }
    }

}
