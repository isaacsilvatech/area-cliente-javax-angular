package br.eti.ljr.sn.clientesrv.producer;

import javax.enterprise.context.Dependent;
import javax.enterprise.inject.Produces;
import javax.enterprise.inject.spi.InjectionPoint;

import org.jboss.logging.Logger;

@Dependent
public class LoggerProducer {

	@Produces
	public Logger createLogger(InjectionPoint point) {
		return Logger.getLogger(point.getMember().getDeclaringClass());
	}
}
