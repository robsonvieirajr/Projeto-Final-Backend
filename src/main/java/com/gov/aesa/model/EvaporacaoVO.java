package com.gov.aesa.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@Entity
@Table(name = "evaporacao", schema = "aesa")
public class EvaporacaoVO {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "evaporacao_seq_gen")
    @SequenceGenerator(name = "evaporacao_seq_gen", sequenceName = "seq_id_evaporacao", allocationSize = 1)
    @Column(name = "id_evaporacao", nullable = false)
    private Long id;

    @Column(name = "id_acude")
    private Long id_acude;

    @Column(name = "valor_media_mes_evaporacao")
    private BigDecimal valorEvaporacao;

    @Column(name = "mes_evaporacao")
    private String mesEvaporacao;

    @Column(name = "ano_evaporacao")
    private Integer anoEvaporacao;
}
