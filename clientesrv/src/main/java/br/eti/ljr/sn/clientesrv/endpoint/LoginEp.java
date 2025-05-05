package br.eti.ljr.sn.clientesrv.endpoint;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.EJB;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import br.eti.ljr.sn.clientesrv.business.AdmBss;
import br.eti.ljr.sn.clientesrv.business.LoginBss;
import br.eti.ljr.sn.clientesrv.business.PessoaBss;
import br.eti.ljr.sn.clientesrv.domain.Pessoa;
import br.eti.ljr.sn.clientesrv.dto.AlterarSenha;
import br.eti.ljr.sn.clientesrv.dto.AlterarSenhaEsquecida;
import br.eti.ljr.sn.clientesrv.dto.LoginCheckDto;
import br.eti.ljr.sn.clientesrv.dto.LoginTokenDto;
import br.eti.ljr.sn.clientesrv.dto.OpcaoRecuperarSenha;
import br.eti.ljr.sn.clientesrv.dto.RecuperacaoSenha;
import br.eti.ljr.sn.clientesrv.filter.Logado;
import br.eti.ljr.sn.clientesrv.filter.PermitidoAlterarSenha;
import br.eti.ljr.sn.clientesrv.util.Funcoes;

@Path("/login")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class LoginEp {

	@EJB
	private LoginBss loginBss;

	@EJB
	private AdmBss admBss;

	@EJB
	private PessoaBss pessoaBss;

	@POST
	public LoginTokenDto check(LoginCheckDto login) {
		return loginBss.check(login);
	}

	@GET
	@Path("/cpf-valido/{cpf}")
	public Boolean cpfExiste(@PathParam("cpf") String cpf) {
		Pessoa cliente = pessoaBss.getEntityByCpfCnpj(cpf);
		return cliente != null && cliente.getSenha() != null && cliente.getEmail() != null;
	}

	@GET
	@Path("/opcoes-recuperar-senha/{cpf}")
	public List<OpcaoRecuperarSenha> getOpcoesRecuperarSenha(@PathParam("cpf") String cpf) {
		Pessoa cliente = pessoaBss.getEntityByCpfCnpj(cpf);
		List<OpcaoRecuperarSenha> opcoes = new ArrayList<>();
		if (cliente.getEmail() != null) {
			opcoes.add(new OpcaoRecuperarSenha("email", Funcoes.mascararEmail(cliente.getEmail())));
		}
		return opcoes;
	}

	@POST
	@Path("/enviar-recuperacao-senha")
	public void enviarRecuperacaoSenha(RecuperacaoSenha dto) {
		loginBss.enviarRecuperacaoSenha(dto.cpf(), dto.opcao().tipo());
	}

	@POST
	@PermitidoAlterarSenha
	@Path("/alterar-senha-esquecida")
	public String alterarSenhaEsquecida(AlterarSenhaEsquecida dto) {
		return loginBss.alterarSenha(dto.codPessoa(), dto.senha());
	}
	
	@Logado
	@PUT
	@Path("/alterar-senha")
	public String alterarSenha(AlterarSenha dto) {
		return loginBss.alterarSenha(dto.codPessoa(), dto.senhaAtual(), dto.senhaNova());
	}
}
