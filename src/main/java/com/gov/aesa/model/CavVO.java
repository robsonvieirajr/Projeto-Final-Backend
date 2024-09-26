package com.gov.aesa.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@Entity
@Table(name = "cav", schema = "aesa")
public class CavVO {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "vac_seq_gen")
    @SequenceGenerator(name = "cav_seq_gen", sequenceName = "seq_id_cav", allocationSize = 1)
    @Column(name = "id_cav", nullable = false)
    private Long id;

    @Column(name = "id_acude")
    private Long id_acude;

    @Column(name = "cota")
    private BigDecimal cota;

    @Column(name = "area")
    private BigDecimal area;

    @Column(name = "volume")
    private BigDecimal volume;
}
