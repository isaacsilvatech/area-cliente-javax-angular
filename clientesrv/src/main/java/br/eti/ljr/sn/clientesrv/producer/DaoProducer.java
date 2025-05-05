package br.eti.ljr.sn.clientesrv.producer;

import java.lang.reflect.ParameterizedType;

import javax.enterprise.context.Dependent;
import javax.enterprise.inject.Produces;
import javax.enterprise.inject.spi.InjectionPoint;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import br.eti.ljr.sn.clientesrv.persistence.Dao;

@Dependent
public class DaoProducer {

	@PersistenceContext
	private EntityManager em;

	@Produces
	public <T> Dao<T> createDao(InjectionPoint point) {

		ParameterizedType type = (ParameterizedType) point.getType();
		@SuppressWarnings("unchecked")
		Class<T> clazz = (Class<T>) type.getActualTypeArguments()[0];

		return new Dao<T>(clazz, em);
	}
}
