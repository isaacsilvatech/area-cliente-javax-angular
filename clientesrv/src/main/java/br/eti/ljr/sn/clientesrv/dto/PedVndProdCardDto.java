package br.eti.ljr.sn.clientesrv.dto;

public class PedVndProdCardDto {

	private String codProd;
	private byte[] imagem;

	public String getCodProd() {
		return codProd;
	}

	public void setCodProd(String codProd) {
		this.codProd = codProd;
	}

	public byte[] getImagem() {
		return imagem;
	}

	public void setImagem(byte[] imagem) {
		this.imagem = imagem;
	}

}
