package com.gov.aesa.model;

import java.math.BigDecimal;
import java.time.LocalDate;
import org.hibernate.annotations.ColumnDefault;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "acude", schema = "aesa")
public class AcudeVO {
	@Id
	@ColumnDefault("nextval('seq_id_acude'::regclass)")
	@Column(name = "id_acude", nullable = false)
	private Integer id;

	@NotNull
	@ManyToOne(fetch = FetchType.LAZY, optional = false)
	@JoinColumn(name = "id_precipitacao", nullable = false)
	private PrecipitacaoVO idPrecipitacao;

	@NotNull
	@ManyToOne(fetch = FetchType.LAZY, optional = false)
	@JoinColumn(name = "id_vazao", nullable = false)
	private VazaoVO idVazao;

	@NotNull
	@ManyToOne(fetch = FetchType.LAZY, optional = false)
	@JoinColumn(name = "id_evaporacao", nullable = false)
	private EvaporacaoVO idEvaporacao;

	@NotNull
	@ManyToOne(fetch = FetchType.LAZY, optional = false)
	@JoinColumn(name = "id_cota_area_volume", nullable = false)
	private CotaAreaVolumeVO idCotaAreaVolume;

	@Size(max = 255)
	@Column(name = "nome")
	private String nome;

	@Column(name = "data_pedido")
	private LocalDate dataPedido;

	@Column(name = "vol_morto")
	private BigDecimal volumeMorto;

	@Column(name = "vol_acumulado")
	private BigDecimal volumeAcumulado;

	@Column(name = "area_drenagem")
	private BigDecimal areaDrenagem;

	@Column(name = "coeficiente_tanque")
	private BigDecimal coeficienteTanque;
}