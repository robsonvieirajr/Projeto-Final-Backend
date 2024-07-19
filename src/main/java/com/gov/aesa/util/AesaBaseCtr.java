package com.gov.aesa.util;

import java.util.Date;
import java.util.TimeZone;
import com.gov.aesa.retorno.RetornoAesa;

public class AesaBaseCtr {

	protected static final String URL_BASE_DOCUMENTACAO = "https://github.com/robsonvieirajr";
	protected static final String URL_BASE_DOCUMENTACAO_REST = URL_BASE_DOCUMENTACAO + "endpoints";
	protected static final String MENSAGEM_PADRAO_PRELINK = "A Documentação da regra pode ser encontrada em:";
	protected static final TimeZone TIMEZONE = TimeZone.getTimeZone(ConstantesAESA.TIMEZONE_DEFAULT);



	protected RetornoAesa gerarRetornoDeSucesso(Object objeto) {
		RetornoAesa retorno = new RetornoAesa();
		retorno.setCodigoMensagem(ConstantesUteis.SUCESSO_CODIGO);
		retorno.setMensagem(ConstantesUteis.SUCESSO_MENSAGEM);
		retorno.setDataServidor(new Date());
		retorno.setObjeto(objeto);
		return retorno;
	}

	protected RetornoAesa gerarRetornoNaoEncontrado(Object objeto) {
		RetornoAesa retorno = new RetornoAesa();
		retorno.setCodigoMensagem(ConstantesUteis.CRITICA_DADOS_NAO_ENCONTRADOS);
		retorno.setMensagem(ConstantesUteis.CRITICA_DADOS_NAO_ENCONTRADOS_MENSAGEM);
		retorno.setDataServidor(new Date());
		retorno.setObjeto(objeto);
		return retorno;
	}

	protected RetornoAesa gerarRetornoErro(Object objeto) {
		RetornoAesa retorno = new RetornoAesa();
		retorno.setCodigoMensagem(ConstantesUteis.CRITICA_CODIGO);
		retorno.setMensagem(ConstantesUteis.CRITICA_MENSAGEM);
		retorno.setDataServidor(new Date());
		retorno.setObjeto(objeto);
		return retorno;
	}

	protected RetornoAesa gerarRetornoNaoEncontradoCache(Object objeto) {
		RetornoAesa retorno = new RetornoAesa();
		retorno.setCodigoMensagem(ConstantesUteis.CRITICA_DADOS_NAO_ENCONTRADO_CHACHE);
		retorno.setMensagem(ConstantesUteis.CRITICA_DADOS_NAO_ENCONTRADO_CACHE_MENSAGEM);
		retorno.setDataServidor(new Date());
		retorno.setObjeto(objeto);
		return retorno;
	}

}
