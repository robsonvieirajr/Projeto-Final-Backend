package com.gov.aesa.util;

import java.time.ZoneId;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class ConstantesAESA {

	public static final String RELATORIO_SOLICITACAO_SISAPMG = "relatorioSisapMG";
	private static final String AMERICA_BAHIA = "America/Bahia";
	// constantes para TAG SWAGGER para manter nomes de tags padronizados e não atrapalhar agrupamento
	public static final String AUXILIARES_DESCRICAO = "Auxiliares para tela";
	public static final String AUXILIARES_NOME = "Auxiliares";

	public static final String CACHE_TOKENWSO2_NOME_PRINCIPAL = "tokenWso2";
	public static final String CACHE_TOKENWSO2_CHAVE_DENTRO_CACHE_PRINCIPAL = "tokenValido";

	public static final ZoneId zonaIdDefault = ZoneId.of(AMERICA_BAHIA);

	public static final String FAVORITOS_DESCRICAO = "Gerenciamento de favoritos do usuário";
	public static final String FAVORITOS_NOME = "Favoritos";
	public static final String TIMEZONE_DEFAULT = AMERICA_BAHIA;
	public static final String ISO_DATETIME = "yyyy-MM-dd'T'HH:mm:ss.SSSXXX";
	public static final String ISO_TIME = "HH:mm:ss.SSSXXX";
	public static final String ISO_DATE = "yyyy-MM-dd";


	// mensagens para exibicao
	public static final String DADO_NAO_ENCONTRADO = "-";
	public static final String DADOS_NAO_ENCONTRADOS_MENSAGEM = " Não foram encontrados dados para exibição!";

	// Retornos HTTP
	public static final int HTTP_OK = 200;
	public static final int HTTP_SERVER_ERROR = 500;

	public static final String TOKEN_INVALIDO = "Token SSC não e valido, ou expirado!";

	public static final Integer SUCESSO_CODIGO = 00;
	public static final Integer CRITICA_CODIGO = 01;
	public static final Integer CRITICA_DADOS_NAO_ENCONTRADOS = 02;
	public static final Integer CRITICA_CODIGO_REGRA_DE_NEGOCIO = 04;
	public static final String SUCESSO_MENSAGEM = "Operação realizada com sucesso!";
	public static final String CRITICA_MENSAGEM = "Erro na requisição!";
	public static final String CRITICA_DADOS_NAO_ENCONTRADOS_MENSAGEM = "Não foram encontrados resultados para o valor pesquisado!";

	public static final Integer CRITICA_01_CODIGO = 01;
	public static final String CRITICA_01_MENSAGEM = "Campo inválido: ";
}
