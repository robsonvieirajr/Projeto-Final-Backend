package com.gov.aesa.retorno;

import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.gov.aesa.util.ConstantesAESA;
import com.gov.aesa.util.Paginacao;

public class RetornoAesa {

	private Integer codigoMensagem;
	private String token;
	private String mensagem;
	private String mensagemExibicao;
	@JsonFormat(pattern = ConstantesAESA.ISO_DATETIME, shape = JsonFormat.Shape.STRING, timezone = ConstantesAESA.TIMEZONE_DEFAULT)
	private Date dataServidor = null;
	private Object objeto;
	private String hashErro;

	Paginacao paginacao;
	@JsonIgnore
	private String nomeCache = "cacheRetornoKey";

	public String getNomeCache() {
		return nomeCache;
	}

	public void setNomeCache(String nomeCache) {
		this.nomeCache = nomeCache;
	}

	public Integer getCodigoMensagem() {
		return codigoMensagem;
	}

	public void setCodigoMensagem(Integer codigoMensagem) {
		this.codigoMensagem = codigoMensagem;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public String getMensagem() {
		return mensagem;
	}

	public void setMensagem(String mensagem) {
		this.mensagem = mensagem;
	}

	public Date getDataServidor() {
		return dataServidor;
	}

	public void setDataServidor(Date dataServidor) {
		this.dataServidor = dataServidor;
	}

	public Object getObjeto() {
		return objeto;
	}

	public void setObjeto(Object objeto) {
		this.objeto = objeto;
	}

	public Paginacao getPaginacao() {
		return paginacao;
	}

	public void setPaginacao(Paginacao paginacao) {
		this.paginacao = paginacao;
	}

	public String getHashErro() {
		return hashErro;
	}

	public void setHashErro(String hashErro) {
		this.hashErro = hashErro;
	}

	public String getMensagemExibicao() {
		return mensagemExibicao;
	}

	public void setMensagemExibicao(String mensagemExibicao) {
		this.mensagemExibicao = mensagemExibicao;
	}
}
