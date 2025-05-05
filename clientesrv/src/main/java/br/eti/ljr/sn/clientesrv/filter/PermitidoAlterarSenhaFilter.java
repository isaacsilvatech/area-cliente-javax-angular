package br.eti.ljr.sn.clientesrv.filter;

import java.io.IOException;
import java.util.Objects;

import javax.annotation.Priority;
import javax.ejb.EJB;
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
import br.eti.ljr.sn.clientesrv.exception.TokenException;
import br.eti.ljr.sn.clientesrv.exception.UnauthorizedException;

@Provider
@PermitidoAlterarSenha
@Priority(Priorities.AUTHENTICATION)
public class PermitidoAlterarSenhaFilter implements ContainerRequestFilter {

	@Context
	private ResourceInfo resourceInfo;

	@EJB
	private PessoaBss pessoaBss;

	@EJB
	private TokenBss tokenBss;

	@Override
	public void filter(ContainerRequestContext requestContext) throws IOException {

		String authorizationHeader = requestContext.getHeaderString(HttpHeaders.AUTHORIZATION);

		try {
			String token = authorizationHeader.substring("Bearer".length()).trim();

			Long codPessoa = tokenBss.getCodPessoaByClaimsNotValidation(token);
			Pessoa pessoa = pessoaBss.getEntity(codPessoa);
			if (Objects.isNull(pessoa)) {
				throw new UnauthorizedException("Você não tem permissao para alterar a senha!");
			}
			tokenBss.validateTokenWithNewKeyPessoa(token, pessoa);

		} catch (UnauthorizedException | TokenException e) {
			requestContext.abortWith(Response.status(Response.Status.UNAUTHORIZED).entity(e.getMessage()).build());
		} catch (Exception e) {
			requestContext.abortWith(Response.status(Response.Status.UNAUTHORIZED).build());
		}
	}
}
