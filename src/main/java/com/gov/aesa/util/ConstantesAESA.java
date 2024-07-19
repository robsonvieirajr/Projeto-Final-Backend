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

	public static final String MENSAGEM_TENTE_NOVAMENTE_TRIBUNUS = "Ocorreu um erro na busca dos parametros informados, por favor tente novamente mais tarde";

	public static final String MENSAGEM_TENTE_NOVAMENTE_TRIBUNUS_CPF_CARGA_DIARIA = "Não foi encontrado nenhum CPF de servidor na carga diaria.";

	public static final String MENSAGEM_TENTE_NOVAMENTE_TRIBUNUS_ERRO_PARAMETRO_DATA_COMPLETA = "Ocorreu um erro, a data informada não e uma data valida, informe uma data DD/MM/YYYY";

	public static final String MENSAGEM_TENTE_NOVAMENTE_TRIBUNUS_ERRO_PARAMETRO_DATA_ANO_MES = "Ocorreu um erro, a data informada não e uma data valida,data inical maior que a final ";

	public static final String MESAGEM_PARAMETROS_ENCONTRADOS_COM_SUCESSO = "Paramentros encontrados com sucesso.";

	public static final String MENSAGEM_TENTE_NOVAMENTE_TRIBUNUS_ERRO_PARAMETRO_CPF = "Ocorreu um erro, o cpf informado não é um cpf valido, informe um cpf valido!";

	// mensagens para exibicao
	public static final String DADO_NAO_ENCONTRADO = "-";
	public static final String DADOS_NAO_ENCONTRADOS_MENSAGEM = " Não foram encontrados dados para exibição!";
	public static final String OBSERVACOES_RESIDE_NO_EXTERIOR_DETALHES = "Para mais detalhes acesse o box 'Observações Básicas' logo abaixo.";

	// Retornos HTTP
	public static final int HTTP_OK = 200;
	public static final int HTTP_SERVER_ERROR = 500;

	public static final String TOKEN_INVALIDO = "Token SSC não e valido, ou expirado!";

	// endereços tipos
	public static final Integer TIPO_ACERTO = 61;
	public static final String TIPO_ENDERECO = "R";

	// tipos contato
	public static final String TIPO_CONTATO_CELULAR = "CE";
	public static final String TIPO_CONTATO_EMAIL_INSTITUICIONAL = "EI";
	public static final String TIPO_CONTATO_EMAIL_PARTICULAR = "EP";
	public static final String TIPO_CONTATO_TELEFONE_PESSOAL = "TE";

	public static final Integer[] situacoesFuncionaisInativas = { 2, 3, 4, 6, 7, 11, 15, 16, 18, 19 };

	public static final Integer[] tipoVinculacaoDependenteRH = { 9, 10 };

	public static final Integer[] codigoGrupoNatureza = { 5, 6, 7, 14, 15, 20, 22, 23, 44, 35 };

	public static final Integer[] TIPOS_CODIGO_VERBA_FERIAS_PREMIOS = { 160, 1160, 2160, 3160, 8160, 1161, 3161 };

	public interface ParametroVO {
		public static final String ENDERECO_BROKER = "BROKER-ENDERECO";
		public static final String PORTA_BROKER = "BROKER-PORTNUMBER";
		public static final String CLASSE_SERVICO_BROKER = "BROKER-SERVERCLASS";
		public static final String NOME_SERVICO_BROKER = "BROKER-SERVERNAME";
		public static final String NOME_SERVIDOR_BROKER = "BROKER-SERVICENAME";
		public static final String TIME_OUT_BROKER = "BROKER-TIMEOUTBROKER";
	}

	public interface ComprovanteVoOrderBy {
		public static final String nomeServidor = "pessoaVO.nomePessoa";
		public static final String cpf = "cpfVO.numeroDocumento";
		public static final String nome = "nome";
		public static final String formato = "formato";
		public static final String data = "tsMovimentacao";
		public static final String id = "id";

	}

	// classes db2
	public static final String nome_vo_db2_complementoPessoaFisica = "br.gov.prodemge.seplag.sisap.entidades.sisap.pessoa.fisica.ComplementoPessoaFisicaVO";
	public static final String nome_vo_db2_contato = "br.gov.prodemge.seplag.sisap.entidades.sisap.pessoa.ContatoPessoaFisicaJuridicaVO";
	public static final String nome_vo_db2_endereco_pessoa_fis_juridica = "br.gov.prodemge.seplag.sisap.entidades.sisap.pessoa.EnderecoPessoaFisicaJuridicaVO";
	public static final String nome_vo_db2_endereco_exterior = "br.gov.prodemge.seplag.sisap.entidades.sisap.pessoa.EnderecoExteriorVO";
	public static final String nome_vo_db2_localidade_incluida = "br.gov.prodemge.seplag.sisap.entidades.sisap.pessoa.LocalidadeIncluidaVO";
	public static final String nome_vo_db2_pessoaFisica = "br.gov.prodemge.seplag.sisap.entidades.sisap.pessoa.fisica.PessoaFisicaVO";
	public static final String nome_vo_db2_pessoa = "br.gov.prodemge.seplag.sisap.entidades.sisap.pessoa.PessoaVO";
	public static final String nome_vo_db2_rotina = "br.gov.prodemge.seplag.sisap.entidades.sisap.cadastro.RotinaVO";
	public static final String nome_vo_db2_documento = "br.gov.prodemge.seplag.sisap.entidades.sisap.pessoa.DocumentoVO";
	public static final String nome_vo_db2_dependente = "br.gov.prodemge.seplag.sisap.entidades.sisap.pessoa.fisica.DependenteVO";
	public static final String nome_vo_db2_dependenteRH = "br.gov.prodemge.seplag.sisap.entidades.sisap.pessoa.fisica.DependenteRhVO";
	public static final String nome_vo_db2_maisSeq1 = "br.gov.prodemge.seplag.sisap.entidades.sisap.MaiorSeq1VO";

	// Situacoes Funcionais
	public static final Integer SIT_FUNC_05_PENSIONISTA = 5;
	public static final Integer SIT_FUNC_06_PENSAO_ALIMENTO = 6;
	public static final Integer SIT_FUNC_07_MEMBRO_ACADEPOL_ORG_DEL_COLET = 7;
	public static final Integer SIT_FUNC_13_DETERM_JUDICIAL_INDENIZACAO = 13;
	public static final Integer SIT_FUNC_15_GRATIFICADOS = 15;
	public static final Integer SIT_FUNC_19_BOLSISTAS = 19;
	public static final Integer SIT_FUNC_23_GRATIF_ENCARGO_CURSO_CONCURSO = 23;
	public static final Integer SIT_FUNC_01_ATIVO = 1;
	public static final Integer SIT_FUNC_04_APOSENTADO = 4;
	public static final Integer SIT_FUNC_03_RECRUTAMENTO_AMPLO = 3;
	public static final Integer SIT_FUNC_09_ESTABILIZADO = 9;
	public static final Integer SIT_FUNC_11_FUNCAO_PUBLICA = 11;
	public static final Integer SIT_FUNC_16_EFETIVO_APOS_64_2002 = 16;
	public static final Integer SIT_FUNC_17_APOSENTADO_POR_MEDIA = 17;

	// pessoa fisica
	public static final Integer TIPO_VINCULACAO_CURADOR_9 = 9;
	public static final Integer TIPO_VINCULACAO_CURADOR_10 = 10;

	// complemento pessoa fisica
	public static final int TIPO_RECADASTRAMENTO = 5;

	// Situacoes Servidor
	public static final Integer SIT_SERV_07_APOSENTADO = 7;
	public static final Integer SIT_SERV_02_DESLIGADO = 2;
	public static final Integer SIT_SERV_04_FALECIDO = 4;
	public static final Integer SIT_SERV_11_EM_PROCESSO_APOSENTADORIA = 11;

	// Tipo de Cargo Funcao
	public static final Integer TIP_CARGO_FUNCAO_01_EFETIVO_FUNCAO_PUBLICA = 1;
	public static final Integer TIP_CARGO_FUNCAO_02_COMISSAO = 2;
	public static final Integer TIP_CARGO_FUNCAO_04_DESIGNADO = 4;
	public static final Integer TIP_CARGO_FUNCAO_06_RECRUTAMENTO_AMPLO = 6;

	// Sequencial Natureza
	public static final Integer SEQ_NAT_01_FERIAS_PREMIO_CONCESSAO = 1;
	public static final Integer SEQ_NAT_02_EFETIVACAO_LEI10254 = 2;
	public static final Integer SEQ_NAT_03_EFETIVACAO_POR_CLASSIFICACAO = 3;
	public static final Integer SEQ_NAT_04_EFETIVACAO_TERMOS_ART105_INC_I = 4;
	public static final Integer SEQ_NAT_05_EFETIVACAO_TERMOS_ART105_INC_II = 5;
	public static final Integer SEQ_NAT_06_EFETIVACAO_TERMOS_ART107_ADCT = 6;
	public static final Integer SEQ_NAT_07_EFETIVACAO_TERMOS_ART16_ART19 = 7;
	public static final Integer SEQ_NAT_09_EFETIVACAO_TERMOS_ART6_LEI10254 = 9;
	public static final Integer SEQ_NAT_10_EFETIVACAO_TERMOS_ART14_LEI6277 = 10;
	public static final Integer SEQ_NAT_11_EFETIVACAO_POR_MEIO_DELIBERACAO_28_1986 = 11;
	public static final Integer SEQ_NAT_13_FERIAS_PREMIO_CONVERSAO_ESPECIE_APOSENTADORIA = 13;
	public static final Integer SEQ_NAT_17_DESLIGADO_EXONERACAO_DEMISSAO_CONVERSAO_EM_ESPECIE = 17;
	public static final Integer SEQ_NAT_18_FALECIMENTO_CONVERSAO_EM_ESPECIE = 18;
	public static final Integer SEQ_NAT_19_DESLIGADO_CONVERSAO_EM_ESPECIE = 19;
	public static final Integer SEQ_NAT_31_FERIAS_PREMIO_ZONA_RURAL = 31;
	public static final Integer SEQ_NAT_98_EFETIVACAO_POR_DETERMINACAO_JUDICIAL = 98;
	public static final Integer SEQ_NAT_99_MIGRADO = 99;
	public static final Integer SEQ_NAT_230_PAGAMENTO_SUSPENSO_RECEBENDO_FUNPEMG = 230;

	// Grupo Natureza
	public static final Integer COD_GRUPO_NAT_01 = 1;
	public static final Integer COD_GRUPO_NAT_12 = 12;
	public static final Integer COD_GRUPO_NAT_24_BENEFICIOS_GRATIFICACOES = 24;
	public static final Integer COD_GRUPO_NAT_27_USUFRUTO_FERIAS_PREMIO = 27;
	public static final Integer COD_GRUPO_NAT_41_ESTABILIDADE_EFETIVACAO = 41;

	// Situacoes Funcionais permitida recadastramento
	public static final String SIT_FUNC_PERMITIDAS_RECADASTRAMENTO = "01,02,04,05,09,10,11,12,14,16,17,19,20";
	public static final String SIT_FUNCIONARIO_DESLIGADOS_TB_ADMISSAO = "02,03,04,06,07,11,15,16,18,19";

	// Situacoes servidor permitida recadastramento
	public static final String SIT_SERV_PERMITIDAS_RECADASTRAMENTO = "05,06,07,11,16";

	// TIPOS DE PAPEIS
	public static final String PAPEL_CODIGO_ANALISTA_INDICADORES = "USR_IND_ADMIN";
	public static final String PAPEL_CODIGO_VISUALIZADOR_RELATORIO_MASTER = "VIS_REL_MASTER";
	public static final String PAPEL_CODIGO_SERVIDOR_GERAL = "SERV_GERAL";
	public static final String PAPEL_CODIGO_MASTER_SCAP = "SCAP_SISAP";
	public static final String PAPEL_EMISSOR_LAUDA_MASTER = "EMISS_LAUDA_MASTER";
	public static final String PAPEL_CODIGO_MASTER_RH = "MASTER_RH_SISAP";
	public static final String PAPEL_CODIGO_RH_ORGAO_SISAP = "RH_ORGAO_SISAP";
	public static final String PAPEL_CODIGO_RH_ORGAO_FIN_SISAP = "RH_ORGAO_FIN_SISAP";
	public static final String PAPEL_CODIGO_MASTER_RH_FIN_ORGAO = "MASTER_RH_FIN_ORGAO";
	public static final String PAPEL_CODIGO_MASTER_RH_FIN_ORG_ATUALIZA = "MASTER_RH_FIN_ORG_ATUALIZA";
	public static final String PAPEL_CODIGO_RH_ORGAO_FIN_SISAP_ATTUALIZA = "RH_ORGAO_FIN_SISAP_ATTUALIZA";
	public static final String PAPEL_CODIGO_SCAP_SISAP_ATUALIZA = "SCAP_SISAP_ATUALIZA";
	public static final String PAPEL_CODIGO_PERICIA_MEDICA_DIGITACAO = "PERICIA_MEDICA_DIGITACAO";
	public static final String NAO_POSSUI_PERMISSAO = "O usuario não possui permissões para realizar a operação solicitada!";

	public static final String[] permissaoPapeisDadosServidor = { "SCAP_SISAP", "MASTER_RH_SISAP", "MASTER_RH_FIN_ORGAO", "RH_ORGAO_SISAP", "RH_ORGAO_FIN_SISAP" };

	public static final String[] permissaoPapeisDadosBeneficioServidor = { "SCAP_SISAP", "RH_ORGAO_SISAP", "RH_ORGAO_FIN_SISAP" };
	public static final String[] permissaoPapeisDadoFinanceiroServidor = { "SCAP_SISAP", "MASTER_RH_FIN_ORGAO", "RH_ORGAO_FIN_SISAP" };
	public static final String[] permissaoPapeisPericiaMedicaServidor = { "PERICIA_MEDICA_GERAL" };
	public static final String[] permissaoPapeisAusenciasServidor = { "SCAP_SISAP", "MASTER_RH_SISAP", "MASTER_RH_FIN_ORGAO", "RH_ORGAO_SISAP", "RH_ORGAO_FIN_SISAP" };
	public static final String[] permissaoPapeisBeneficioServidor = { "SCAP_SISAP", "RH_ORGAO_SISAP", "RH_ORGAO_FIN_SISAP" };
	public static final String[] permissaoPapeisRelatorioBeneficiarioServidor = { "RECAD_MASTER" };
	public static final String[] permissaoPapeisRelatorioGerenciaisServidor = { "GESTOR_IND_MASTER" };

	public static final String USUARIO_NAO_POSSUI_PERMISSAO_PARA_ACESSAR_ESSA_FUNCIONALIDADE = "Usuario não possui permissão para acessar essa funcionalidade!";
	public static final String USUARIO_NAO_POSSUI_PERMISSAO_PARA_VISUALIZAR_DADOS_PERICIA_MEDICA = "Informação disponivel somente para Gestores de Pericia Medica *";

	public static final Integer[] LISTA_SEQUENCIAL_NATUREZA_SALDOS_DIREITOS = { 1, 322, 324, 323, 31 };
	public static final String[] LISTA_BENEFICIARIOS_PAGAMENTO_BLOQUEADO = { "S", "B" };
	public static final String[] LISTA_BENEFICIARIOS_PAGAMENTO_LIBERADO = { "L" };

	public static final String BENEFICIARIOS_PAGAMENTO_LIBERADO = "L";
	public static final String BENEFICIARIOS_PAGAMENTO_AUTOMATICO_NAO = "N";

	public interface FeriasPremio {
		public static final Integer[] codigosVerbasFeriasPremio = { 160, 1016, 1160, 1161, 2154, 2160, 3160, 3161, 7160, 8160 };
		public static final String statusLiberacaoProcessado = "P";
		public static final Integer codigoVerbaDesconto = 8160;

	}

	public interface PapeisSSCConsultaTodos {
		public static final String RECAD_MASTER = "RECAD_MASTER";
		public static final String RECAD_UAI_RH = "RECAD_UAI_RH";
		public static final String RECAD_RH_RESP = "RECAD_RH-RESP";
	}

	public interface PapeisSSCPossuiPermissaoEdicaoTodosPapeis {
		public static final String SCAP_SISAP = "SCAP_SISAP";
	}

	public interface PapeisSSCConsultaLimitada {
		public static final String RECAD_ESCOLA_UNIDADES = "RECAD_ESCOLA_UNIDADES";
		public static final String RECAD_DRH_SRE = "RECAD_DRH_SRE";
	}

	public interface AdmissaoVoPesquisaServidorOrderBY {
		public static final String nome = "pessoaIdentificacao.pessoaRecuperada.nomePessoa";
		public static final String cpf = "pessoaIdentificacao.cpfPessoa";
		public static final String masp = "pessoaIdentificacao.complementoPessoaFisicaVO.numeroMaspFuncionario";
		public static final String sequencialAdmissao = "sequencialAdmissao";
		public static final String situacaoFuncional = "codigoSituacaoFuncional";
		public static final String situacaoServidor = "codigoSituacaoServidor";
		public static final String orgaoExercicio = "admissaoLotacaoExercicioVO.orgaoExercicio.instituicaoVO.siglaInstituicao";

	}

	public interface BeneficioVoPesquisaServidorOrderBY {
		public static final String nome = "admissaoVO.pessoaIdentificacao.pessoaRecuperada.nomePessoa";
		public static final String masp = "admissaoVO.pessoaIdentificacao.complementoPessoaFisicaVO.numeroMaspFuncionario";
		public static final String situacaoServidor = "admissaoVO.situacaoServidorVO.codigoSituacaoServidor";
		public static final String orgaoLotacao = "admissaoVO.admissaoLotacaoExercicioVO.orgaoLotacao.codigoUnidadeSsc";

	}

	public interface AdmissaoBeneficioVoPesquisaServidorOrderBY {
		public static final String nome = "pessoaIdentificacao.pessoaRecuperada.nomePessoa";
		public static final String masp = "pessoaIdentificacao.complementoPessoaFisicaVO.numeroMaspFuncionario";
		public static final String situacaoServidor = "situacaoServidorVO.codigoSituacaoServidor";
		public static final String orgaoLotacao = "admissaoLotacaoExercicioVO.orgaoLotacao.codigoUnidadeSsc";

	}

	public interface AusenciasServidorVoPesquisaServidorOrderBY {
		public static final String tipoAusencia = "tipoAusencia";
		public static final String natureza = "naturezaAusencia";
		public static final String dataInicio = "dataInicio";
		public static final String dataFim = "dataFim";
		public static final String dataRetornoPrevisto = "dataRetorno";
		public static final String quantidadeDiasAusentes = "quantidadeDias";

	}

	public interface ExercioioCargoViewVoServidorVoPesquisaServidorOrderBY {

		public static final String numeroFuncionario = "numeroFuncionario";
		public static final String dataInicio = "dataInicio";
		public static final String dataFim = "dataFim";

	}

	public interface PensaoAlimentosListaViewVoServidorVoPesquisaServidorOrderBY {

		public static final String numeroFuncionario = "numeroFuncionario";
		public static final String pensaoDataInicio = "pensaoDataInicio";
		public static final String pensaoDataFim = "pensaoDataFim";

	}

	public interface FormaDeCalculoListaViewVoServidorVoPesquisaServidorOrderBY {

		public static final String baseCalculo = "codigoBaseCalculo";
		public static final String descricaoBaseCalculo = "descricaoBaseCalculo";
		public static final String numeroPensao = "numeroPensao";
		public static final String dataInicio = "dataInicio";

	}

	public interface VerbaBaseCalculoListaViewVoServidorVoPesquisaServidorOrderBY {

		public static final String verba = "codigoVerba";
	}

	public interface CurriculoVoPesquisaFormacaoEscolarServidorOrderBY {
		public static final String sequencialCurriculum = "sequencialCurriculum";

	}

	public interface DependenteVoOrderBY {
		public static final String dataInicio = "dataInicio";
		public static final String nome = "pessoaVO.nomePessoa";
		public static final String cpf = "cpfVO.numeroDocumento";

	}

	public interface TribunusCargoVoOrderBY {
		public static final String cargoFuncaoDataInicio = "cargoFuncaoDataInicio";
	}

	public interface EvolucaoServidorVoOrderBY {
		public static final String dataInicio = "dataInicio";
	}

	public interface ProcessosJudiciaisVoOrderBY {
		public static final String dataAtualizacao = "dataAtualizacao";
		public static final String dataAjuizamento = "dataAjuizamento";
		public static final String nome = "pes.nomePessoa";
		public static final String masp = "parteProcessos.servidorVO.numeroMaspFuncionario";
		public static final String cpf = "serv.cpf";
		public static final String situacaoAge = "parteProcessos.situacaoProcesso.descricao";
		public static final String numeroProcesso = "numeroProcesso";
		public static final String numeroRecurso = "numeroRecurso";
		public static final String assunto = "processo.assuntoProcesso";
		public static final String liminar = "liminarTutelaPje";

	}

	public interface ProcessosJudiciaisVinculadosVoOrderBY {
		public static final String numeroProcesso = "numeroProcesso";
		public static final String numeroRecurso = "numeroRecurso";
		public static final String idProcesso = "id";
		public static final String dataRegistro = "dataAjuizamento";
		public static final String assuntoProcesso = "assuntoProcesso";
		public static final String liminar = "liminarTutelaPje";

	}

	public interface ParteProcessosJudiciaisVoOrderBY {
		public static final String dataAtualizacao = "processo.dataAtualizacao";
		public static final String dataAjuizamento = "processo.dataAjuizamento";
		public static final String nome = "pessoaIdentificacaoVO.pessoaRecuperada.nomePessoa";
		public static final String masp = "pessoaIdentificacaoVO.complementoPessoaFisicaVO.numeroMaspFuncionario";
		public static final String cpf = "pessoaIdentificacaoVO.cpfPessoa";
		public static final String situacaoAge = "processo.situacaoProcesso";
		public static final String numeroProcesso = "processo.numeroProcesso";
		public static final String numeroRecurso = "processo.numeroRecurso";
		public static final String assunto = "processo.assuntoProcesso";
		public static final String liminar = "processo.liminarTutelaPje";

	}

	public interface VinculoProcessoVOOrderBY {
		public static final String numeroProcessoVinculado = "numeroProcessoVinculado";
	}

	public interface AdmissaoVoTutoriaOrderBY {
		public static final String nome = "pessoaFisicaVO.pessoaVO.nomePessoa";
		public static final String cpf = "pessoaFisicaVO.cpfVO.numeroDocumento";
		public static final String masp = "pessoaFisicaVO.complementoPessoaFisicaVO.numeroMaspFuncionario";
		public static final String admissao = "sequencialAdmissao";
		public static final String curadorTutor = "pessoaFisicaVO.curadorVO.pessoaVO.nomePessoa";

	}

	public interface ComprovantePagamentoDetalheVoOrderBy {
		public static final String numeroPessoaFisjur = "numeroPessoaFisjur";
		public static final String numeroFuncionario = "numeroFuncionario";
		public static final String sequencialAdmissao = "sequencialAdmissao";
		public static final String AnoMesFolha = "AnoMesFolha";
		public static final String numeroDadoFinanceiro = "numeroDadoFinanceiro";
		public static final String numeroFolhaSuplementar = "numeroFolhaSuplementar";

	}

	public interface DocumentoEnviadoVoOrderBy {
		public static final String nomeServidor = "pessoaVO.nomePessoa";
		public static final String cpf = "cpfVO.numeroDocumento";
		public static final String nome = "nome";
		public static final String formato = "formato";
		public static final String data = "tsMovimentacao";
		public static final String id = "id";

	}

	public interface UsuarioPapelVoOrderBy {
		public static final String nome = "usuarioSolicitacao.usuarioDadosVO.nome";
		public static final String cpf = "cpf";
		public static final String data = "dataAnalise";
		public static final String situacao = "situacaoSSC";
		public static final String tipo = "tipoSolicitacao";
		public static final String papel = "papel";

	}

	public interface CargoAdmissaoVoPesquisaCargosCarreirasServidorOrderBY {

		public static final String orgaoEntidade = "instituicaoVO.siglaInstituicao";
		public static final String cargoCarreira = "tipoCargoFuncao";
		public static final String tipoCargo = "tipoCargoFuncaoVO.descricaoTipoCargoFuncao";

		public static final String dataInicio = "dataInicio";
		public static final String dataFim = "dataFim";
		public static final String dataFimEfetiva = "dataFimEfetiva";
		// situacao e definida no codigo atraves da datafimefetiva
		public static final String situacao = "dataFimEfetiva";

	}

	public interface ConPagamentoIndiceVOPesquisaServidorOrderBY {

		public static final String pagamentoAnoMesFolha = "pagamentoAnoMesFolha";
		public static final String pagamentoDescricaoTipoFolha = "pagamentoDescricaoTipoFolha";

	}

	public interface CargoHorariaVoPesquisaCargaHorariaProfessorOrderBY {

		public static final String natureza = "";

		public static final String dataInicio = "dataInicio";

		public static final String dataFim = "dataFim";
		public static final String dataFimEfetiva = "dataFimEfetiva";
		public static final String tipo = "tipoCargaHorariaVO.descricaoTipoCargaHoraria";
		public static final String modalidadeDeEnsino = "modalidadeEnsinoVO.descricaoModalidade";
		public static final String quantidadeHorasSemanais = "quantidadeHorasAula";

	}

	public interface EvolucaoCargoVoDetalhaEvolucaoCargosCarreirasOrderBY {

		public static final String cargo = "";
		public static final String nivelGrau = "codigoGrauCargo";
		public static final String simbolo = "codigoSimboloVencimento";

		public static final String dataInicio = "dataInicio";
		public static final String dataFim = "dataFim";
		public static final String categoriaProfissional = "";
		public static final String natureza = "";

	}

	public interface CargoHorariaVoPesquisaSubstitutosServidorOrderBY {

		public static final String nome = "pessoaIdentificacao.pessoaRecuperada.nomePessoa";

		public static final String cpf = "pessoaIdentificacao.cpfPessoa";
		public static final String masp = "pessoaIdentificacao.complementoPessoaFisicaVO.numeroMaspFuncionario";

		public static final String admissao = "";

		public static final String natureza = "naturezaVO";

		public static final String dataInicio = "dataInicio";

		public static final String dataFim = "dataFim";

		public static final String dataFimEfetiva = "dataFimEfetiva";

	}

	public interface FuncionalProgramaticaAdmissaoVoPesquisaFuncionaisProgramaticasOrderBY {

		public static final String orgaoEntidade = "";

		public static final String funcao = "";
		public static final String subFuncao = "";

		public static final String programa = "";

		public static final String projetoAtividade = "";
		public static final String subProjeto = "";

		public static final String dataInicio = "dataInicio";

		public static final String dataFim = "dataFim";

	}

	public interface RelatoriosServidorOrderBY {
		public static final String id = "id";
		public static final String nomeRelatorio = "nomeRelatorio";
		public static final String orgaoMomentoAgendamento = "orgaoLogado";
		public static final String statusGeracao = "status";
		public static final String dataPeriodo = "dataPedido";
		public static final String formato = "tipoRelatorio";

	}

	public interface RelatoriosBeneficiariosRecadastramentoServidorOrderBY {

		public static final String nomeServidor = "beneficioNome";
		public static final String cpf = "beneficioCpf";
		public static final String masp = "beneficioNumeroMasp";
		public static final String dataNascimento = "beneficioDataNascimento";
		public static final String dataUltimoRecadastramento = "beneficioDataUltimoRecadastramento";
		public static final String situacaoFuncional = "beneficioCodigoSituacaoFuncional";
		public static final String situacaoServidor = "beneficioCodigoSituacaoServidor";
		public static final String orgaoEntidade = "beneficioSiglaOrgao";

	}

	public interface RelatoriosBeneficiarioPagamentoBloqueadoOrderBY {
		public static final String nomeServidor = "beneficioNome";
		public static final String cpf = "beneficioCpf";
		public static final String masp = "beneficioNumeroMasp";
		public static final String dataNascimento = "beneficioDataNascimento";
		public static final String dataUltimoRecadastramento = "beneficioDataUltimoRecadastramento";
		public static final String situacaoFuncional = "beneficioCodigoSituacaoFuncional";
		public static final String orgaoEntidade = "beneficioSiglaOrgao";
	}

	public interface RelatoriosBeneficiarioPagamentoLiberadoOrderBY {
		public static final String nome = "beneficioNome";
		public static final String cpf = "beneficioCpf";
		public static final String masp = "beneficioNumeroMasp";
		public static final String dataNascimento = "beneficioDataNascimento";
		public static final String dataUltimoRecadastramento = "beneficioDataUltimoRecadastramento";
		public static final String situacaoFuncional = "beneficioCodigoSituacaoFuncional";
		public static final String orgaoEntidade = "beneficioSiglaOrgao";
	}

	public interface RelatoriosRecadastramentoAniversarioOrderBY {
		public static final String nomeServidor = "aniversarioNome";
		public static final String cpf = "aniversarioCpf";
		public static final String masp = "aniversarioNumeroMasp";
		public static final String dataNascimento = "aniversarioDataNascimento";
		public static final String dataUltimoRecadastramento = "aniversarioUltimoRecadastramento";
		public static final String tipoRecadastramento = "aniversarioDescricaoRecastramento";
		public static final String municipio = "aniversarioMunicipio";
	}

	public interface RecadastramentosPorOrgaoPerfilOrderBY {
		public static final String orgaoEntidade = "siglaOrgao";
		public static final String codigoPapel = "codigoPapel";
		public static final String papel = "descricaoPapel";
		public static final String quantidade = "quantidade";
	}

	public interface AssuntoProcessoPorQuantidadeOrderBy {
		public static final String quantidade = "quantidade";
	}

	public interface RecadastramentosPorOperadorOrderBY {
		public static final String nomeOperador = "nomePessoa";
		public static final String nomePapel = "descricaoPapel";
		public static final String orgaoEntidade = "siglaOrgao";
		public static final String quantidade = "quantidade";
	}

	public interface SolicitacoesAcessoOrderBY {
		public static final String situacao = "statusAprovacao";
		public static final String papel = "papelSSC.codigoPapelSsc";
		public static final String orgaoEntidade = "orgaoSsc.instituicaoViewVO.siglaInstituicao";
		public static final String quantidade = "quantidade";
	}

	public static final Integer SUCESSO_CODIGO = 00;
	public static final Integer CRITICA_CODIGO = 01;
	public static final Integer CRITICA_DADOS_NAO_ENCONTRADOS = 02;
	public static final Integer CRITICA_CODIGO_REGRA_DE_NEGOCIO = 04;
	public static final String SUCESSO_MENSAGEM = "Operação realizada com sucesso!";
	public static final String CRITICA_MENSAGEM = "Erro na requisição!";
	public static final String CRITICA_DADOS_NAO_ENCONTRADOS_MENSAGEM = "Não foram encontrados resultados para o valor pesquisado!";

	public static final String NOME_PAPEL_GESTOR_SEGURANCA = "GESTOR DE SEGURANCA";
	public static final String NOME_PAPEL_ADMINISTRADOR_UNIDADE = "ADMINISTRADOR";

	public static final int TIPO_VINCULO_ESTAGIARIO = 2;
	public static final int TIPO_VINCULO_TERCERIZADO = 3;

	// tipos Documento
	public static final Integer TIPO_DOC_IDENTIDADE = 1;
	public static final Integer TIPO_DOC_CARTEIRA_PROFISSIONAL = 2;
	public static final Integer TIPO_DOC_TITULO_ELEITOR = 6;
	public static final Integer TIPO_DOC_HABILITACAO = 7;
	public static final Integer TIPO_DOC_CPF = 8;
	public static final Integer TIPO_DOC_PIS_PASEP = 9;
	public static final Integer TIPO_DOC_CARTEIRA_TRABALHO = 11;
	public static final Integer TIPO_DOC_CERTIFICADO_RESERVISTA = 14;

	// TIPO TECNOLOGIA CONTATO
	public interface TecnologiaContato {
		public static final String TELEFONE_RESIDENCIAL = "TE";
		public static final String TELEFONE_CELULAR = "CE";
		public static final String TELEFONE_EMERGENCIA = "EM";
		public static final String EMAIL_PESSOAL = "EP";
	}

	public static final String NOME_SERVICO_DADOS_FUNCIONAIS_ATUAIS = "consulta-de-dados-funcionais-atuais";
	public static final String NOME_SERVICO_CONTRA_CHEQUE = "consulta-de-dados-contra-cheque";
	public static final String NOME_SERVICO_EVOLUCAO_CARREIRA = "consulta-de-evolucao-carreira";
	public static final String NOME_SERVICO_DADOS_PESSOAIS = "consulta-dados-pessoais";

	public static final String NOME_SERVICO_VENCIMENTOS_CARGOS = "consulta-vencimentos-cargos";
	public static final String NOME_SERVICO_VENCIMENTOS_CARREIRAS = "consulta-vencimentos-carreiras";

	public static final String NOME_SERVICO_LISTA_COMPLEMENTACAO = "Consulta Lista de Complementacao de Assuntos do Processo";

	public static final String TIPO_PESSOA_FISICA = "F";
	public static final Integer SEQUENCIAL_INICIAL = 1;
	public static final Integer COD_DEPENDENTE_CURADOR = 8;
	public static final String CODIGO_USUARIO = "SISAPMG";
	public static final String TIMEZONE_DEFAULT = AMERICA_BAHIA;
	public static final String ISO_DATETIME = "yyyy-MM-dd'T'HH:mm:ss.SSSXXX";
	public static final String ISO_TIME = "HH:mm:ss.SSSXXX";
	public static final String ISO_DATE = "yyyy-MM-dd";

	public static final String[] TIPOS_AUSENCIAS_SERVIDOR = { "AFASTAMENTO", "FALTAS CONSOLIDADAS", "FERIAS PREMIO", "FERIAS REGULAMENTARES", "PERICIA MEDICA" };

	public static final List<Integer> TIPOS_DOCUMENTOS_IDENTIFICADORES = Arrays.asList(1, 4, 5, 7, 8, 12);
	public static final List<Integer> TIPOS_DOCUMENTOS_REQUEREM_UF = Arrays.asList(1, 2, 6, 7, 11, 14);
	public static final List<Integer> TIPOS_DOCUMENTOS_ELEITORAIS = Collections.singletonList(6);
	public static final List<Integer> TIPOS_DOCUMENTOS_TRABALHISTAS = Arrays.asList(2, 9, 11);
	public static final List<Integer> TIPOS_DOCUMENTOS_DIVERSOS = Collections.singletonList(14);

	public static final String DIREITOS_PRIMEIRO_PERIODO_SERVIDOR = "Gozo|Espécie momento da aposentadoria|Espécie momento da exoneração/dispensa|Dobro para aposentadoria|Dobro para adicionais momento da aposentadoria";
	public static final String BASE_LEGAL_PRIMEIRO_PERIODO_SERVIDOR = "Art. 31, II da CE/89 (redação original)|Art. 31, II da CE/89 (redação EC 13/94)";

	public static final String DIREITOS_SEGUNDO_PERIODO_SERVIDOR = "Gozo|Espécie momento da aposentadoria|Dobro para aposentadoria|Dobro para adicionais momento da aposentadoria ";
	public static final String BASE_LEGAL_SEGUNDO_PERIODO_SERVIDOR = "Art. 31, II da CE/89 (redação EC 18/95)";

	public static final String DIREITOS_TERCEIRO_PERIODO_SERVIDOR = "Gozo|Espécie momento da aposentadoria|Dobro para adicionais momento da aposentadoria";
	public static final String BASE_LEGAL_TERCEIRO_PERIODO_SERVIDOR = "Art. 31, II da CE/89 (redação EC 48/00, 114 e 117 do ADCT)";

	public static final String DIREITOS_QUARTO_PERIODO_SERVIDOR = "Gozo|Dobro para adicionais momento da aposentadoria";
	public static final String BASE_LEGAL_QUARTO_PERIODO_SERVIDOR = "Art. 31, 4º da CE/89 (redação EC 57/03)";

	public static final Integer CRITICA_01_CODIGO = 01;
	public static final String CRITICA_01_MENSAGEM = "Campo inválido: ";
}
