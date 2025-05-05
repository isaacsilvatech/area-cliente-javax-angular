package br.eti.ljr.sn.clientesrv.business;

import javax.ejb.Stateless;
import javax.persistence.NoResultException;

import br.eti.ljr.sn.clientesrv.domain.Administrativa;
import br.eti.ljr.sn.clientesrv.exception.BssException;

@Stateless
public class AdministrativaBss extends Bss<Administrativa> {

	private static final long serialVersionUID = 1L;

	public String getValor(String parametro) throws BssException {
		try {
			Administrativa adm = dao.getEntity(parametro);
			return adm.getValor();
		} catch (NoResultException e) {
			throw new BssException("Parametro não existente.");
		}
	}

}
