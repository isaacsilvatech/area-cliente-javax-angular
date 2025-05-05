package br.eti.ljr.sn.clientesrv.endpoint;

import javax.ejb.EJB;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import br.eti.ljr.sn.clientesrv.business.PessoaBss;
import br.eti.ljr.sn.clientesrv.domain.Pessoa;
import br.eti.ljr.sn.clientesrv.dto.PessoaDados;
import br.eti.ljr.sn.clientesrv.filter.Logado;
import br.eti.ljr.sn.clientesrv.filter.PessoaLogada;

@Logado
@Path("/perfil")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class PerfilEp {

	@EJB
	private PessoaBss pessoaBss;

	@Inject
	@PessoaLogada
	private Pessoa pessoaLogada;

	@GET
	public PessoaDados getDados(@PathParam("codPessoa") Long codPessoa) {
		return new PessoaDados(pessoaLogada);
	}
	
	@PUT
	public PessoaDados salvar(PessoaDados dados) {
		return pessoaBss.salvar(dados);
	}
}
