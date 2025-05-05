package br.eti.ljr.sn.clientesrv.exception;

import javax.ejb.EJBException;
import javax.ejb.EJBTransactionRolledbackException;
import javax.inject.Inject;
import javax.ws.rs.client.ResponseProcessingException;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

import org.jboss.logging.Logger;

@Provider
public class ExceptionHandler implements ExceptionMapper<Exception> {

	@Inject
	private Logger log;

	@Override
	public Response toResponse(Exception ex) {

		if (ex instanceof BssException bx) {
			return Response.status(Response.Status.BAD_REQUEST).entity(bx.getMessage()).build();
		}

		if (ex instanceof EJBException ejbx2) {
			if (ejbx2.getCause() instanceof BssException bx) {
				return Response.status(Response.Status.BAD_REQUEST).entity(bx.getMessage()).build();
			}
		}

		if (ex instanceof EJBTransactionRolledbackException ejbx) {
			if (ejbx.getCause() instanceof ResponseProcessingException rpx) {
				if (rpx.getCause() instanceof BssException ux) {
					return Response.status(Response.Status.BAD_REQUEST).entity(ux.getMessage()).build();
				}
			}
		}

		if (ex instanceof UnauthorizedException ux) {
			return Response.status(Response.Status.UNAUTHORIZED).entity(ux.getMessage()).build();
		}

		if (ex instanceof EJBTransactionRolledbackException ejbx) {
			if (ejbx.getCause() instanceof ResponseProcessingException rpx) {
				if (rpx.getCause() instanceof UnauthorizedException ux) {
					return Response.status(Response.Status.UNAUTHORIZED).entity(ux.getMessage()).build();
				}
			}
		}
		log.error(ex);
		return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("Erro interno: " + ex.getMessage())
				.build();
	}

}
