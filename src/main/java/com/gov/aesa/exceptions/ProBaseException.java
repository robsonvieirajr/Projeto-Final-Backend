package com.gov.aesa.exceptions;

public class ProBaseException extends RuntimeException {
	private static final long serialVersionUID = -3359804902060675886L;
	private String mensagem = "";

	public ProBaseException() {
	}

	public ProBaseException(String msg) {
		super(msg);
		this.mensagem = msg;
	}

	public String getMessage() {
		return this.mensagem;
	}
}
