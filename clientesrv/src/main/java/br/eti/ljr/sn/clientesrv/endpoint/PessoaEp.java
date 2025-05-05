package br.eti.ljr.sn.clientesrv.endpoint;

import javax.ejb.EJB;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import br.eti.ljr.sn.clientesrv.business.PessoaBss;
import br.eti.ljr.sn.clientesrv.domain.Pessoa;
import br.eti.ljr.sn.clientesrv.dto.PessoaLogadaDto;
import br.eti.ljr.sn.clientesrv.filter.Logado;
import br.eti.ljr.sn.clientesrv.filter.PessoaLogada;

@Logado
@Path("/pessoa")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class PessoaEp {

	@Inject
	@PessoaLogada
	private Pessoa pessoaLogada;

	@EJB
	private PessoaBss pessoaBss;

	@GET
	public PessoaLogadaDto getPessoaLogada() {
		return new PessoaLogadaDto(pessoaLogada);
	}
}
