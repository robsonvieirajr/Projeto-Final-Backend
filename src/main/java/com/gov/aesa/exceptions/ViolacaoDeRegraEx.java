package com.gov.aesa.exceptions;

import java.util.HashMap;
import java.util.Map;

public class ViolacaoDeRegraEx extends ProBaseException {
	private static final long serialVersionUID = -681184136758849271L;
	private String mensagem = "";
	private Map<String, String> campos = new HashMap();

	public ViolacaoDeRegraEx() {
	}

	public ViolacaoDeRegraEx(String msg) {
		super(msg);
		this.mensagem = msg;
	}

	public ViolacaoDeRegraEx(Map<String, String> campos) {
		super("Erros!");
		this.campos = campos;
	}

	public ViolacaoDeRegraEx(String msg, Map<String, String> campos) {
		super(msg);
		this.mensagem = msg;
		this.campos = campos;
	}

	public String getMessage() {
		return this.mensagem;
	}

	public Map<String, String> getMensagensParaCamposEspecificos() {
		return this.campos;
	}
}
