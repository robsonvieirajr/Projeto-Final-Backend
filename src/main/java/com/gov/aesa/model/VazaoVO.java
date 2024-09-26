package com.gov.aesa.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@Entity
@Table(name = "vazao", schema = "aesa")
public class VazaoVO {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "vazao_seq_gen")
    @SequenceGenerator(name = "vazao_seq_gen", sequenceName = "seq_id_vazao", allocationSize = 1)
    @Column(name = "id_vazao", nullable = false)
    private Long id;

    @Column(name = "id_acude")
    private Long id_acude;

    @Column(name = "valor_vazao")
    private BigDecimal valorVazao;

    @Column(name = "mes_vazao")
    private String mesVazao;

    @Column(name = "ano_vazao")
    private Integer anoVazao;
}
