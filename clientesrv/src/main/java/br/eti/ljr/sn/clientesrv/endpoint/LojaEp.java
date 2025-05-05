package br.eti.ljr.sn.clientesrv.endpoint;

import javax.ejb.EJB;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import br.eti.ljr.sn.clientesrv.business.AdmBss;

@Path("/loja")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class LojaEp {

	@EJB
	private AdmBss admBss;

	@GET
	@Path("/enderecos")
	public String getEnderecos() {
		return admBss.getValor("URL_ENDERECO_LOJA");
	}
}
