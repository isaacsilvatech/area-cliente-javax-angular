package br.eti.ljr.sn.clientesrv.business;

import java.io.Serializable;

import javax.inject.Inject;

import org.jboss.logging.Logger;

import br.eti.ljr.sn.clientesrv.persistence.Dao;

public abstract class Bss<T> implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	protected Logger log;

	@Inject
	protected Dao<T> dao;

	public T getEntity(Object id) {
		return dao.getEntity(id);
	}

	public T merge(T t) {
		return dao.merge(t);
	}

	public void persist(T t) {
		dao.persist(t);
	}
	
	public void detach(T t) {
		dao.detach(t);
	}
	
	public void remove(Object pk) {
		dao.remove(pk);
	}
	
	public void flush() {
		dao.flush();
	}
}
