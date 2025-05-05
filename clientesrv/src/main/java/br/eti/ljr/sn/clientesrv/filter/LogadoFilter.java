package br.eti.ljr.sn.clientesrv.filter;

import java.io.IOException;
import java.util.Objects;

import javax.annotation.Priority;
import javax.ejb.EJB;
import javax.enterprise.event.Event;
import javax.inject.Inject;
import javax.ws.rs.Priorities;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;
import javax.ws.rs.container.ResourceInfo;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.Provider;

import br.eti.ljr.sn.clientesrv.business.PessoaBss;
import br.eti.ljr.sn.clientesrv.business.TokenBss;
import br.eti.ljr.sn.clientesrv.domain.Pessoa;
import br.eti.ljr.sn.clientesrv.exception.UnauthorizedException;

@Provider
@Logado
@Priority(Priorities.AUTHENTICATION)
public class LogadoFilter implements ContainerRequestFilter {

	@Context
	private ResourceInfo resourceInfo;

	@Inject
	@PessoaLogada
	private Event<Pessoa> pessoaLogadaEvent;

	@EJB
	private TokenBss tokenBss;

	@EJB
	private PessoaBss pessoaBss;

	@Override
	public void filter(ContainerRequestContext requestContext) throws IOException {

		String authorizationHeader = requestContext.getHeaderString(HttpHeaders.AUTHORIZATION);

		try {
			String token = authorizationHeader.substring("Bearer".length()).trim();

			Long codPessoa = tokenBss.getCodPessoaByClaimsWithValidation(token);
			Pessoa pessoaLogada = pessoaBss.getEntity(codPessoa);

			if (Objects.isNull(pessoaLogada)) {
				throw new UnauthorizedException("Você não tem permissão para acessar esse módulo!");
			}

			pessoaLogadaEvent.fire(pessoaLogada);

		} catch (UnauthorizedException e) {
			requestContext.abortWith(Response.status(Response.Status.UNAUTHORIZED).entity(e.getMessage()).build());
		} catch (Exception e) {
			requestContext.abortWith(Response.status(Response.Status.UNAUTHORIZED).build());
		}
	}
}
