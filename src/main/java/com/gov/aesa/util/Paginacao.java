package com.gov.aesa.util;

public class Paginacao {

	private long totalRegistros;
	private int pagina;
	private int tamanhoPagina;
	private int totalPaginas;

	public long getTotalRegistros() {
		return totalRegistros;
	}

	public void setTotalRegistros(long totalRegistros) {
		this.totalRegistros = totalRegistros;
	}

	public int getPagina() {
		return pagina;
	}

	public void setPagina(int pagina) {
		this.pagina = pagina;
	}

	public int getTamanhoPagina() {
		return tamanhoPagina;
	}

	public void setTamanhoPagina(int tamanhoPagina) {
		this.tamanhoPagina = tamanhoPagina;
	}

	public int getTotalPaginas() {
		return totalPaginas;
	}

	public void setTotalPaginas(int totalPaginas) {
		this.totalPaginas = totalPaginas;
	}

	public Paginacao(long totalRegistros, int pagina, int tamanhoPagina, int totalPaginas) {
		this.totalRegistros = totalRegistros;
		this.pagina = pagina;
		this.tamanhoPagina = tamanhoPagina;
		this.totalPaginas = totalPaginas;
	}

	public Paginacao() {
	}
}