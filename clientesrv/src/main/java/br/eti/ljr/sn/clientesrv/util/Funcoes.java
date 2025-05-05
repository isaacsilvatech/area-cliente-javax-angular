package br.eti.ljr.sn.clientesrv.util;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.time.LocalDate;
import java.util.Calendar;
import java.util.Date;

public class Funcoes {

	public static String getHash(String senha) {
		try {
			MessageDigest md = MessageDigest.getInstance("MD5");
			BigInteger hash = new BigInteger(1, md.digest(senha.getBytes()));
			String sen = hash.toString(16);
			return sen;
		} catch (NoSuchAlgorithmException e) {
			throw new RuntimeException(e);
		}
	}

	public static String mascararEmail(String email) {
		int atIndex = email.indexOf('@');
		if (atIndex <= 4) {
			// Caso o nome de usuário seja muito curto, retorna com menos asteriscos
			return email.charAt(0) + "*".repeat(atIndex - 2) + email.substring(atIndex - 1);
		}

		String prefixo = email.substring(0, atIndex);
		String sufixo = email.substring(atIndex); // inclui o @ e o domínio

		String inicio = prefixo.substring(0, 2);
		String fim = prefixo.substring(prefixo.length() - 2);

		String mascarado = inicio + "*".repeat(prefixo.length() - 4) + fim + sufixo;
		return mascarado;
	}

	public static String removeEspacosDuplos(String str) {
		return str.trim().replace("  ", " ");
	}

	public static LocalDate dateToLocalDate(Date input) {

		Calendar cal = Calendar.getInstance();
		cal.setTime(input);
		LocalDate date = LocalDate.of(cal.get(Calendar.YEAR), cal.get(Calendar.MONTH) + 1,
				cal.get(Calendar.DAY_OF_MONTH));

		return date;
	}

}
