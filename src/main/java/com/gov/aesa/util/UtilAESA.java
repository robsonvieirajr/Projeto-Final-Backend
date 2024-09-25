package com.gov.aesa.util;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.lang.reflect.Field;
import java.math.BigDecimal;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.text.Normalizer;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.time.temporal.ChronoUnit;
import java.util.AbstractList;
import java.util.ArrayList;
import java.util.Base64;
import java.util.Date;
import java.util.HashMap;
import java.util.InputMismatchException;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Objects;
import java.util.UUID;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;
import java.util.zip.ZipOutputStream;
import javax.swing.text.MaskFormatter;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import com.gov.aesa.exceptions.ViolacaoDeRegraEx;
import com.gov.aesa.retorno.RetornoAesa;
import jakarta.persistence.Transient;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.xml.bind.DatatypeConverter;

public class UtilAESA {
	private static final long serialVersionUID = 1L;

	public static Object copyAttributesFromTo(Object origem, Object destino) {

		if (origem == null)
			return destino;

		Field[] fieldsFromOrigemClass = origem.getClass().getDeclaredFields();
		Field[] fieldsFromDestinoClass = destino.getClass().getDeclaredFields();
		if (origem.getClass().getSuperclass().getSimpleName().contains("ConPagamentoDetalheBase")) {
			fieldsFromOrigemClass = origem.getClass().getSuperclass().getDeclaredFields();
		}
		if (objetoOrigemEPaiDoObjetoDestino(origem, destino)) {
			fieldsFromOrigemClass = origem.getClass().getDeclaredFields();
			fieldsFromDestinoClass = origem.getClass().getDeclaredFields();
		}
		if (objetoOrigemEFilhoDoObjetoDestino(origem, destino)) {
			fieldsFromOrigemClass = destino.getClass().getDeclaredFields();
			fieldsFromDestinoClass = destino.getClass().getDeclaredFields();
		}

		try {
			copyUsingMap(destino, origem, fieldsFromDestinoClass, fieldsFromOrigemClass);
		} catch (Exception e) {
			throw new ViolacaoDeRegraEx(String.format("Erro ao copiar Objetos%s", e.getMessage()));
		}
		return destino;
	}

	private static boolean objetoOrigemEFilhoDoObjetoDestino(Object origem, Object destino) {
		return origem.getClass().getSuperclass().getSimpleName().equalsIgnoreCase(destino.getClass().getSimpleName());
	}

	private static boolean objetoOrigemEPaiDoObjetoDestino(Object origem, Object destino) {
		return destino.getClass().getSuperclass().getSimpleName().equalsIgnoreCase(origem.getClass().getSimpleName());
	}

	private static void copyUsingMap(Object destino, Object origem, Field[] fieldsFromDestinoClass, Field[] fieldsFromOrigemClass) {
		Map<String, Field> fieldsFromDestinoClassMap = new HashMap<String, Field>();
		Map<String, Field> fieldsFromOrigemClassMap = new HashMap<String, Field>();

		for (Field field : fieldsFromDestinoClass) {
			fieldsFromDestinoClassMap.put(field.getName(), field);
		}

		for (Field field : fieldsFromOrigemClass) {
			fieldsFromOrigemClassMap.put(field.getName(), field);
		}

		Iterator<Map.Entry<String, Field>> it = fieldsFromDestinoClassMap.entrySet().iterator();
		while (it.hasNext()) {
			Map.Entry<String, Field> objectAtual = it.next();
			if (validatesFieldsForCopyingUsingMap(fieldsFromDestinoClassMap, fieldsFromOrigemClassMap, objectAtual)) {
				Field currentFieldFromTheDestinoClass = fieldsFromDestinoClassMap.get(objectAtual.getKey());
				Field currentFieldFromTheOrigemClass = fieldsFromOrigemClassMap.get(objectAtual.getKey());
				currentFieldFromTheDestinoClass.setAccessible(true);
				currentFieldFromTheOrigemClass.setAccessible(true);

				try {
					currentFieldFromTheDestinoClass.set(destino, currentFieldFromTheOrigemClass.get(origem));
				} catch (Exception e) {
					throw new ViolacaoDeRegraEx(String.format("Erro copyUsingMap - %s", e.getMessage()));
				}
			}
		}
	}

	private static boolean validatesFieldsForCopyingUsingMap(Map<String, Field> fieldsFromFirstClassMap, Map<String, Field> fieldsFromSecondClassMap, Map.Entry<String, Field> objectAtual) {
		return fieldsFromFirstClassMap.containsKey(objectAtual.getKey()) && fieldsFromSecondClassMap.containsKey(objectAtual.getKey()) && notAFieldProtected(objectAtual.getKey());
	}

	private static boolean notAFieldProtected(String nameOfTheFirstField) {
		return !nameOfTheFirstField.contains("serialVersionUID");
	}

	public static String maskValues(int numDigitos, String mask, String valor) {
		String toReturn = StringUtils.EMPTY;
		toReturn = valor.trim();
		toReturn = StringUtils.leftPad(toReturn, numDigitos, '0');
		MaskFormatter mf;
		try {
			mf = new MaskFormatter(mask);
			mf.setValueContainsLiteralCharacters(false);
			toReturn = mf.valueToString(toReturn);
		} catch (ParseException e) {
			throw new ViolacaoDeRegraEx("Erro ao adicionar zeros a esquerda ");
		}
		return toReturn;
	}


	public static boolean isCPF(String CPF) {
		// considera-se erro CPF's formados por uma sequencia de numeros iguais
		if (CPF.equals("00000000000") || CPF.equals("11111111111") || CPF.equals("22222222222") || CPF.equals("33333333333") || CPF.equals("44444444444") || CPF.equals("55555555555") || CPF.equals("66666666666") || CPF.equals("77777777777")
				|| CPF.equals("88888888888") || CPF.equals("99999999999") || (CPF.length() != 11))
			return (false);

		char dig10, dig11;
		int sm, i, r, num, peso;

		// "try" - protege o codigo para eventuais erros de conversao de tipo (int)
		try {
			// Calculo do 1o. Digito Verificador
			sm = 0;
			peso = 10;
			for (i = 0; i < 9; i++) {
				// converte o i-esimo caractere do CPF em um numero:
				// por exemplo, transforma o caractere '0' no inteiro 0
				// (48 eh a posicao de '0' na tabela ASCII)
				num = CPF.charAt(i) - 48;
				sm = sm + (num * peso);
				peso = peso - 1;
			}

			r = 11 - (sm % 11);
			if ((r == 10) || (r == 11))
				dig10 = '0';
			else
				dig10 = (char) (r + 48); // converte no respectivo caractere numerico

			// Calculo do 2o. Digito Verificador
			sm = 0;
			peso = 11;
			for (i = 0; i < 10; i++) {
				num = CPF.charAt(i) - 48;
				sm = sm + (num * peso);
				peso = peso - 1;
			}

			r = 11 - (sm % 11);
			if ((r == 10) || (r == 11))
				dig11 = '0';
			else
				dig11 = (char) (r + 48);

			// Verifica se os digitos calculados conferem com os digitos informados.
			if ((dig10 == CPF.charAt(9)) && (dig11 == CPF.charAt(10)))
				return (true);
			else
				return (false);
		} catch (InputMismatchException erro) {
			return (false);
		}
	}

	public static byte[] ziparArquivo(String filename, byte[] input) throws IOException {

		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		ZipOutputStream zos = new ZipOutputStream(baos);
		ZipEntry entry = new ZipEntry(filename);
		entry.setSize(input.length);
		zos.putNextEntry(entry);
		zos.write(input);
		zos.closeEntry();
		zos.close();
		return baos.toByteArray();
	}

	public static byte[] encodeBase64(byte[] file) {
		return Base64.getEncoder().encode(file);
	}

	public static byte[] extrairArquivo(byte[] input) throws IOException {
		ZipInputStream zis = new ZipInputStream(new ByteArrayInputStream(input));
		ZipEntry zipEntry = zis.getNextEntry();
		byte[] buffer = new byte[input.length * 10];
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		while (zipEntry != null) {
			zipEntry.getName();
			int len;
			while ((len = zis.read(buffer)) > 0) {
				baos.write(buffer, 0, len);
			}
			baos.close();
			zipEntry = zis.getNextEntry();
		}
		zis.closeEntry();
		zis.close();
		return baos.toByteArray();
	}

	public static String removeCaracteresEspeciaisString(String str) {
		String nfdNormalizedString = Normalizer.normalize(str, Normalizer.Form.NFD);
		Pattern pattern = Pattern.compile("\\p{InCombiningDiacriticalMarks}+");
		return pattern.matcher(nfdNormalizedString).replaceAll("");
	}

	public static String formataDataDDMMYYYY(Date dataAFormatar) {
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		String dataRetorno = ConstantesAESA.DADO_NAO_ENCONTRADO;
		if (dataAFormatar != null) {
			dataRetorno = sdf.format(dataAFormatar);
		}

		return dataRetorno;
	}

	public static LocalDate obterIntervaloFimAno(Integer anoInicio) {
		LocalDate fimAno = LocalDate.of(anoInicio, 12, 31);
		return fimAno;
	}

	public static LocalDate obterIntervaloInicioAno(Integer anoInicio) {
		LocalDate inicioAno = LocalDate.of(anoInicio, 1, 1);
		return inicioAno;
	}

	public static String formataDataAtualHoraMinutoSegundo(LocalDateTime data) {

		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd_HH-mm-ss");

		String formatDateTime = data.format(formatter);

		return formatDateTime;
	}

	public static String getValorFormatado(BigDecimal valor) {
		if (valor != null) {
			Locale brasil = new Locale("pt", "BR");
			DecimalFormat formatterBR = new DecimalFormat("#,##0.00", new DecimalFormatSymbols(brasil));
			StringBuilder sb = new StringBuilder();
			sb.append("R$ ");
			sb.append(formatterBR.format(valor));
			return sb.toString();
		}

		return ConstantesAESA.DADO_NAO_ENCONTRADO;

	}

	public static String formataMoeda(BigDecimal valor) {
		if (valor != null) {
			Locale brasil = new Locale("pt", "BR");
			DecimalFormat formatterBR = new DecimalFormat("#,##0.00", new DecimalFormatSymbols(brasil));
			StringBuilder sb = new StringBuilder();
			sb.append("R$ ");
			sb.append(formatterBR.format(valor));
			return sb.toString();
		}
		return "";
	}

	public static String retornaValorObjetoSemEspacoOuNull(String valor) {
		return valor != null ? valor.trim() : null;

	}

	public static String formataDataYYYYMMDD(Date dataAFormatar) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		String dataRetorno = StringUtils.EMPTY;
		if (dataAFormatar != null) {
			dataRetorno = sdf.format(dataAFormatar);
		}

		return dataRetorno;
	}

	public static String formataDataDD_MM_YYYY(Date dataAFormatar) {
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		String dataRetorno = StringUtils.EMPTY;
		if (dataAFormatar != null) {
			dataRetorno = sdf.format(dataAFormatar);
		}

		return dataRetorno;
	}

	public static Integer retornaAnoYYYY(Date data) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy");
		String dataRetorno = StringUtils.EMPTY;
		if (data != null) {
			dataRetorno = sdf.format(data);
			return Integer.parseInt(dataRetorno);
		}

		return 0;
	}

	public static String formataDataPontoSisapYYYYMMDD(Date dataAFormatar) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy.MM.dd");
		String dataRetorno = StringUtils.EMPTY;
		if (dataAFormatar != null) {
			dataRetorno = sdf.format(dataAFormatar);
		}
		return dataRetorno;
	}


	public static Long validaRetornoValorLong(Long valor) {
		if (valor == null)
			return 0L;
		else
			return valor;
	}

	public static Integer validaRetornoValorInteger(Integer valor) {
		if (valor == null)
			return 0;
		else
			return valor;
	}

	public static String validaValorString(String valor) {
		if (valor == null)
			return "";
		else
			return valor.trim();
	}

	public static String trocarCaracteresEspeciaisURL(String valor) {
		return valor.replace("+", " ");
	}

	public static Integer tratarPagina(Integer pagina) {
		return pagina > 0 ? pagina - 1 : 0;
	}

	public static Boolean isStringUsable(String valor) {
		return valor != null && !valor.isEmpty();
	}

	public static Boolean idIsValid(Long id) {
		return id != null && id != 0;
	}


	public static String geraHashParaLocalizarErro(String erro, Date data) {
		StringBuilder sb = new StringBuilder();
		sb.append(" ");
		sb.append(data);
		sb.append(" - ");
		sb.append(erro);
		return calcularHashDoErro(sb.toString());
	}

	public static RetornoAesa gerarErroPadraoExcecao(RetornoAesa retorno, Exception e, Logger logger) {
		Date data = new Date();
		String message = UtilAESA.obtemMensagemCompletaException(e);

		String hash = UtilAESA.geraHashParaLocalizarErro(message, data);
		String mensagemCompleta = UtilAESA.geraMensagemHashCompletaParaLocalizarErro(message, hash, data);
		String mensagemCompletaGraylog = mensagemCompleta.replace("\n", " ");
		logger.error(mensagemCompletaGraylog);
		retorno.setMensagem(mensagemCompleta);
		retorno.setHashErro(hash);
		return retorno;
	}

	public static RetornoAesa gerarErroPadraoExcecaoMensagemPersonalizada(RetornoAesa retorno, Exception e, Logger logger, String mensagemPersonalizada) {
		Date data = new Date();
		String message = String.format("%s-%s", mensagemPersonalizada, UtilAESA.obtemMensagemCompletaException(e));

		String hash = UtilAESA.geraHashParaLocalizarErro(message, data);
		String mensagemCompleta = UtilAESA.geraMensagemHashCompletaParaLocalizarErro(message, hash, data);
		String mensagemCompletaGraylog = mensagemCompleta.replace("\n", " ");
		logger.error(mensagemCompletaGraylog);
		retorno.setMensagem(mensagemCompleta);
		retorno.setHashErro(hash);
		return retorno;
	}

	public static RetornoAesa gerarRetornoDeSucesso(Object objeto) {
		RetornoAesa retorno = new RetornoAesa();
		retorno.setCodigoMensagem(ConstantesAESA.SUCESSO_CODIGO);
		retorno.setMensagem(ConstantesAESA.SUCESSO_MENSAGEM);
		retorno.setDataServidor(new Date());
		retorno.setObjeto(objeto);
		return retorno;
	}

	public static RetornoAesa gerarRetornoErro(Object objeto) {
		RetornoAesa retorno = new RetornoAesa();
		retorno.setCodigoMensagem(ConstantesAESA.CRITICA_CODIGO);
		retorno.setMensagem(ConstantesAESA.CRITICA_MENSAGEM);
		retorno.setDataServidor(new Date());
		retorno.setObjeto(objeto);
		return retorno;
	}

	public static RetornoAesa gerarRetornoErro(Exception e) {
		RetornoAesa retorno = new RetornoAesa();
		retorno.setCodigoMensagem(ConstantesAESA.CRITICA_CODIGO);
		retorno.setMensagem(ConstantesAESA.CRITICA_MENSAGEM);
		retorno.setDataServidor(new Date());
		retorno.setObjeto(UtilAESA.obtemMensagemCompletaException(e));

		return retorno;
	}

	public static String geraMensagemHashCompletaParaLocalizarErro(String erro, String hash, Date data) {
		StringBuilder sb = new StringBuilder();
		sb.append(" ");
		sb.append(data);
		sb.append(" - ");
		sb.append(erro);
		String mensagem = sb.toString();
		sb.setLength(0);
		sb.append(" ErroComHash ");
		sb.append(hash);
		sb.append(" - ");
		sb.append(mensagem);
		return sb.toString();
	}

	private static String calcularHashDoErro(String ex) {
		if (ex != null && !ex.isEmpty()) {
			try {
				MessageDigest md = MessageDigest.getInstance("SHA-256");
				byte[] hashBytes = md.digest(ex.getBytes(StandardCharsets.UTF_8));
				StringBuilder hexString = new StringBuilder(2 * hashBytes.length);

				for (byte b : hashBytes) {
					hexString.append(String.format("%02X", b));
				}

				return hexString.toString();
			} catch (NoSuchAlgorithmException e) {
				return "(erro ao calcular)";
			}
		}
		return "";
	}

	public static String obtemMensagemCompletaException(Exception e) {
		StringBuilder mensagemFinal = new StringBuilder("");
		if (e.getMessage() != null && !e.getMessage().contains("Erro ao processar a transação!")) {
			mensagemFinal.append("Erro ao processar a transação! ");
		}
		mensagemFinal.append(e.getMessage());

		Throwable causa = e.getCause();
		if (causa != null) {
			Throwable causaRaiz = causa.getCause();
			if (causaRaiz != null && causaRaiz.getMessage() != null) {
				mensagemFinal.append(" - ").append(causaRaiz.getMessage());
			}
		}

		for (StackTraceElement element : e.getStackTrace()) {
			if (element.toString().contains("sisapmg")) {
				String classe = element.getClassName();
				String metodo = element.getMethodName();
				Integer linha = element.getLineNumber();
				mensagemFinal.append(" - '").append(classe).append(".").append(metodo).append("() - Linha:'").append(linha);
				break;
			}
		}

		return mensagemFinal.toString();
	}

	public static String encodeFileToBase64Binary(byte[] imagem) {
		String encodedfile = null;
		try {
			encodedfile = Base64.getEncoder().encodeToString(imagem);
		} catch (Exception e) {
			throw new ViolacaoDeRegraEx(UtilAESA.obtemMensagemCompletaException(e));
		}

		return encodedfile;
	}

	public static String retornaSemCaracteresEspeciais(String texto) {

		if (texto != null) {

			return texto.replaceAll("[^0-9]", "");
		}
		return "";

	}

	public static String formataCPF(String cpf) {
		String cpfFormatado = ConstantesAESA.DADO_NAO_ENCONTRADO;
		if (cpf != null) {
			cpfFormatado = UtilAESA.maskValues(11, "###.###.###-##", cpf);
		}
		return cpfFormatado;
	}

	public static String formataCEP(String cep) {
		String cepFormatado = ConstantesAESA.DADO_NAO_ENCONTRADO;
		if (cep != null) {
			cepFormatado = UtilAESA.maskValues(8, "#####-###", cep);
		}
		return cepFormatado;
	}

	public static String gerarUUID() {
		UUID uuid = UUID.randomUUID();
		return uuid.toString();
	}

	public static String toUpperCase(String str) {
		return str == null ? null : str.toUpperCase().trim();

	}

	public static String removeEspacosEmBrancos(String input) {
		return input.replaceAll("\\s+", "");
	}

	public static String formataMensagemRegraDeNegocio(String texto) {

		StringBuilder stringBuilder = new StringBuilder();
		stringBuilder.append("@@");
		stringBuilder.append(texto);
		stringBuilder.append("@@");
		return texto != null ? stringBuilder.toString() : "";
	}
}