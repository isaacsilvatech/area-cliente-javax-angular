package br.eti.ljr.sn.clientesrv.util;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.Map;

public class Resources {

	private Resources() {
	}

	public static String getTemplate(String file, Map<String, String> vars) {
		try (InputStream is = Funcoes.class.getClassLoader().getResourceAsStream("templates/" + file)) {
			if (is == null) {
				throw new IllegalArgumentException("Template não encontrado: " + file);
			}
			String content = new String(is.readAllBytes(), StandardCharsets.UTF_8);
			for (Map.Entry<String, String> entry : vars.entrySet()) {
				content = content.replace("{{" + entry.getKey() + "}}", entry.getValue());
			}
			return content;
		} catch (IOException e) {
			throw new RuntimeException("Erro ao carregar template de e-mail", e);
		}
	}
}
