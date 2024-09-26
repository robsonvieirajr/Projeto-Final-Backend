package com.gov.aesa.controller;

import com.gov.aesa.model.EvaporacaoVO;
import com.gov.aesa.model.dtos.EvaporacaoDTO;
import com.gov.aesa.model.dtos.VazaoDTO;
import com.gov.aesa.retorno.RetornoAesa;
import com.gov.aesa.seguranca.JwtUtil;
import com.gov.aesa.service.EvaporacaoRN;
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
@RequestMapping(EvaporacaoRestCtr.BASE_URL)
@Tag(name = EvaporacaoRestCtr.EVAPORACAO_SERVIDOR, description = EvaporacaoRestCtr.EVAPORACAO_SERVIDOR_DESCRICAO)
public class EvaporacaoRestCtr extends AesaBaseCtr {
    private static final Logger logger = LoggerFactory.getLogger(EvaporacaoRestCtr.class);

    public static final String BASE_URL = "/api/v1/evaporacao";
    static final String EVAPORACAO_SERVIDOR = "EVAPORAÇÃO";
    public static final String EVAPORACAO_SERVIDOR_DESCRICAO = "Servidor - Evaporação Servidor";
    public static final String EVAPORACAO_DESCRICAO = "Esse endpoint gerencia os dados de Evaporação";
    public static final String EVAPORACAO_URL = URL_BASE_DOCUMENTACAO_REST + "/evaporacao.MD";
    public static final String EVAPORACAO_RESUMO = "Esse endpoint gerencia os dados de Evaporação";


    @Autowired
    private EvaporacaoRN evaporacaoRN;

    @Autowired
    private JwtUtil jwtUtil;

    @PostMapping("/criarEvaporacao")
    @Operation(summary = EVAPORACAO_RESUMO, description = EVAPORACAO_DESCRICAO, externalDocs = @ExternalDocumentation(description = MENSAGEM_PADRAO_PRELINK, url = EVAPORACAO_URL))
    public ResponseEntity<RetornoAesa> criarEvaporacao(@RequestBody EvaporacaoDTO evaporacaoDTO) {
        try {
            EvaporacaoVO evaporacaoVO = new EvaporacaoVO();
            evaporacaoVO.setId_acude(evaporacaoDTO.getId_acude() != null ? evaporacaoDTO.getId_acude() : null);
            evaporacaoVO.setMesEvaporacao(evaporacaoDTO.getMesEvaporacao() != null ? evaporacaoDTO.getMesEvaporacao() : null);
            evaporacaoVO.setAnoEvaporacao(evaporacaoDTO.getAnoEvaporacao() != null ? evaporacaoDTO.getAnoEvaporacao() : null);
            evaporacaoVO.setValorEvaporacao(evaporacaoDTO.getValorEvaporacao() != null ? evaporacaoDTO.getValorEvaporacao() : null);


            RetornoAesa retornoAesa = evaporacaoRN.save(evaporacaoVO);
            if (retornoAesa != null && retornoAesa.getObjeto() instanceof EvaporacaoVO) {
                EvaporacaoVO savedEvaporacao = (EvaporacaoVO) retornoAesa.getObjeto();
                EvaporacaoDTO savedEvaporacaoDTO= new EvaporacaoDTO();
                savedEvaporacaoDTO.setId(savedEvaporacao.getId() != null ? savedEvaporacao.getId() : 0);
                savedEvaporacaoDTO.setId_acude(savedEvaporacao.getId_acude() != null ? savedEvaporacao.getId_acude() : 0);
                savedEvaporacaoDTO.setMesEvaporacao(savedEvaporacao.getMesEvaporacao() != null ? savedEvaporacao.getMesEvaporacao() : null);
                savedEvaporacaoDTO.setAnoEvaporacao(savedEvaporacao.getAnoEvaporacao() != null ? savedEvaporacao.getAnoEvaporacao() : null);
                savedEvaporacaoDTO.setValorEvaporacao(savedEvaporacao.getValorEvaporacao()!= null ? savedEvaporacao.getValorEvaporacao() : null);


                return ResponseEntity.status(HttpStatus.CREATED).body(gerarRetornoDeSucesso(savedEvaporacaoDTO));
            } else {
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(retornoAesa);
            }
        } catch (Exception e) {
            logger.error("Erro ao criar Evaporação", e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(gerarRetornoErro(e.getMessage()));
        }
    }

    @GetMapping("/listarEvaporacoes")
    @Operation(summary = EVAPORACAO_RESUMO, description = EVAPORACAO_DESCRICAO, externalDocs = @ExternalDocumentation(description = MENSAGEM_PADRAO_PRELINK, url = EVAPORACAO_URL))
    public ResponseEntity<RetornoAesa> getAllEvaporacoes() {
        try {
            RetornoAesa evaporacoesRetorno = evaporacaoRN.findAll();

            if (evaporacoesRetorno != null) {
                List<EvaporacaoVO> evaporacoes = (List<EvaporacaoVO>) evaporacoesRetorno.getObjeto();
                List<EvaporacaoDTO> evaporacaoDTOS = evaporacoes.stream().map(evaporacaoVO -> {
                    EvaporacaoDTO dto = new EvaporacaoDTO();
                    dto.setId(Long.valueOf(evaporacaoVO.getId() != null ? evaporacaoVO.getId() : 0));
                    dto.setId_acude(Long.valueOf(evaporacaoVO.getId_acude() != null ? evaporacaoVO.getId_acude() : 0));
                    dto.setMesEvaporacao(evaporacaoVO.getMesEvaporacao() != null ? evaporacaoVO.getMesEvaporacao() : null);
                    dto.setAnoEvaporacao(evaporacaoVO.getAnoEvaporacao() != null ? evaporacaoVO.getAnoEvaporacao() : null);
                    dto.setValorEvaporacao(evaporacaoVO.getValorEvaporacao() != null ? evaporacaoVO.getValorEvaporacao() : null);
                    return dto;
                }).collect(Collectors.toList());
                return ResponseEntity.ok(gerarRetornoDeSucesso(evaporacaoDTOS));
            } else {
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(evaporacoesRetorno);
            }
        } catch (Exception e) {
            logger.error("Erro ao listar as Evaporações", e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(gerarRetornoErro(e.getMessage()));
        }
    }

    @GetMapping("/listarEvaporacoesPorIdAcude")
    @Operation(summary = EVAPORACAO_RESUMO, description = EVAPORACAO_DESCRICAO, externalDocs = @ExternalDocumentation(description = MENSAGEM_PADRAO_PRELINK, url = EVAPORACAO_URL))
    public ResponseEntity<RetornoAesa> buscarVazoesPorIdAcudePorIdAcude(@RequestParam(name = "id_acude", required = true) Long id_acude) {
        try {
            RetornoAesa evaporacoesRetorno = evaporacaoRN.buscarEvaporacaoPorIdAcude(id_acude);
            if (evaporacoesRetorno != null) {
                return ResponseEntity.ok(evaporacoesRetorno);
            }else {
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(evaporacoesRetorno);
            }
        } catch (Exception e) {
            logger.error("Erro ao buscar Evaporações por Id do Açude", e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(gerarRetornoErro(e.getMessage()));
        }
    }

    @PutMapping("/editarEvaporacoes")
    @Operation(summary = EVAPORACAO_RESUMO, description = EVAPORACAO_DESCRICAO, externalDocs = @ExternalDocumentation(description = MENSAGEM_PADRAO_PRELINK, url = EVAPORACAO_URL))
    public ResponseEntity<RetornoAesa> editarEvaporacoes(@RequestBody List<EvaporacaoDTO> evaporacoesDTO) {
        try {
            evaporacaoRN.editarEvaporacoes(evaporacoesDTO);
            return ResponseEntity.ok(gerarRetornoDeSucesso("Dados de Evaporações salvos com sucesso."));
        } catch (Exception e) {
            logger.error("Erro ao editar dados de Evaporações", e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(gerarRetornoErro(e.getMessage()));
        }
    }
}
