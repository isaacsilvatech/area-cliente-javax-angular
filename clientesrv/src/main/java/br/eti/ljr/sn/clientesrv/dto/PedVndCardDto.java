package br.eti.ljr.sn.clientesrv.dto;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

public class PedVndCardDto implements Serializable {

	private static final long serialVersionUID = 1L;

	private Integer codPedVnd;
	private Date dataCadastro;
	private Byte status;
	private BigDecimal valorTotalBruto;
	private BigDecimal valorTotalDesconto;
	
	public PedVndCardDto() {
	}

	public Integer getCodPedVnd() {
		return codPedVnd;
	}

	public void setCodPedVnd(Integer codPedVnd) {
		this.codPedVnd = codPedVnd;
	}

	public Date getDataCadastro() {
		return dataCadastro;
	}

	public void setDataCadastro(Date dataCadastro) {
		this.dataCadastro = dataCadastro;
	}

	public Byte getStatus() {
		return status;
	}

	public void setStatus(Byte status) {
		this.status = status;
	}

	public BigDecimal getValorTotalBruto() {
		return valorTotalBruto;
	}

	public void setValorTotalBruto(BigDecimal valorTotalBruto) {
		this.valorTotalBruto = valorTotalBruto;
	}

	public BigDecimal getValorTotalDesconto() {
		return valorTotalDesconto;
	}

	public void setValorTotalDesconto(BigDecimal valorTotalDesconto) {
		this.valorTotalDesconto = valorTotalDesconto;
	}

}
