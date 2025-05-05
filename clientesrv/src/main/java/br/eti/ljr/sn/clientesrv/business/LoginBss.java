package br.eti.ljr.sn.clientesrv.business;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.HashMap;
import java.util.Objects;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import br.eti.ljr.sn.clientesrv.domain.Pessoa;
import br.eti.ljr.sn.clientesrv.dto.LoginCheckDto;
import br.eti.ljr.sn.clientesrv.dto.LoginTokenDto;
import br.eti.ljr.sn.clientesrv.util.Funcoes;
import br.eti.ljr.sn.clientesrv.util.Resources;

@Stateless
public class LoginBss implements Serializable {

	private static final long serialVersionUID = 1L;

	@EJB
	private PessoaBss pessoaBss;

	@EJB
	private TokenBss tokenBss;

	@EJB
	private AdmBss admBss;

	@EJB
	private EmailBss emailBss;

	public LoginTokenDto check(LoginCheckDto login) {

		Pessoa cliente = pessoaBss.getEntityByCpfCnpj(login.login());
		if (Objects.isNull(cliente)) {
			return new LoginTokenDto(null, "CLIENTE_NAO_ENCONTRADO");
		}

		String msg = check(cliente, login.senha());
		if (msg.equalsIgnoreCase("ok")) {
			String token = tokenBss.generateTokenWithCodPessoa(cliente.getCodPessoa());
			return new LoginTokenDto(token, msg);
		}

		return new LoginTokenDto(null, msg);
	}

	private String check(Pessoa cliente, String senha) {

		if (!cliente.getClienteAtivo()) {
			return "CLIENTE_INATIVO";
		}

		if (cliente.getSenha() == null)
			return "SENHA_NAO_CADASTRADA";

		String hash = Funcoes.getHash(senha);
		if (!cliente.getSenha().equals(hash))
			return "SENHA_INVALIDA";

		return "ok";
	}

	@SuppressWarnings("serial")
	public void enviarRecuperacaoSenha(String cpf, String opcaoRecupercaoSenha) {

		Pessoa cliente = pessoaBss.getEntityByCpfCnpj(cpf);

		String token = tokenBss.generateTokenByNewKeyWithPessoa(cliente,
				Date.from(LocalDateTime.now().plusMinutes(10L).atZone(ZoneId.systemDefault()).toInstant()));

		String url = admBss.getValor("URL_CLIENTE") + "/login/alterar-senha?t=" + token;

		if (opcaoRecupercaoSenha.equals("email")) {
			String html = Resources.getTemplate("email-recuperacao.html", new HashMap<>() {{ put("URL", url); }});
			emailBss.send(cliente.getEmail(), "Recuperação de Senha - Área do Cliente", html);
		}
	}

	public String alterarSenha(Long codPessoa, String novaSenha) {
		Pessoa cliente = pessoaBss.getEntity(codPessoa);

		String mensagem = isValidaSenha(novaSenha);
		if (!mensagem.equalsIgnoreCase("ok")) {
			return mensagem;
		}

		cliente.setDataAtuSenha(new Date());
		cliente.setSenha(Funcoes.getHash(novaSenha));
		pessoaBss.merge(cliente);

		return "ok";
	}

	private String isValidaSenha(String novaSenha) {

		if (novaSenha.isBlank())
			return "A senha não pode ser vazia";

		if (novaSenha.length() < 7)
			return "Senha precisa ter no minimo 8 caracteres.";

		return "ok";
	}

	public String alterarSenha(Long codPessoa, String senhaAtual, String senhaNova) {
		
		Pessoa pessoa = pessoaBss.getEntity(codPessoa);
		
		String msg = check(pessoa, senhaAtual);
		if (!msg.equalsIgnoreCase("ok")) {
			return msg;
		}
		
		msg = isValidaSenha(senhaNova);
		if (!msg.equalsIgnoreCase("ok")) {
			return msg;
		}
		
		pessoa.setDataAtuSenha(new Date());
		pessoa.setSenha(Funcoes.getHash(senhaNova));
		pessoaBss.merge(pessoa);
		
		return "ok";
	}
}
