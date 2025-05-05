package br.eti.ljr.sn.clientesrv.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity(name = "ADM")
public class Adm {

	@Id
	@Column(name = "PARAMETRO")
	private String parametro;

	@Column(name = "VALOR")
	private String valor;

	@Column(name = "COD_SISTEMA")
	private Integer codSistema;

	public String getParametro() {
		return parametro;
	}

	public void setParametro(String parametro) {
		this.parametro = parametro;
	}

	public String getValor() {
		return valor;
	}

	public void setValor(String valor) {
		this.valor = valor;
	}

	public Integer getCodSistema() {
		return codSistema;
	}

	public void setCodSistema(Integer codSistema) {
		this.codSistema = codSistema;
	}

}
