package br.eti.ljr.sn.clientesrv.business;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import br.eti.ljr.sn.clientesrv.domain.ProdutoDetalheImagem;

@Stateless
public class ProdutoDetalheImagemBss extends Bss<ProdutoDetalheImagem> {

	private static final long serialVersionUID = 1L;

	@EJB
	private AdministrativaBss administrativaBss;

	public byte[] getImagem(Integer codProduto, Integer codProdutoDetalhe) {

		List<ProdutoDetalheImagem> imagens = dao
				.getListByCond("id.codProduto = " + codProduto + " and id.codDetProduto = " + codProdutoDetalhe);

		if (imagens.isEmpty()) {
			return null;
		}

		ProdutoDetalheImagem primeiraImagem = imagens.get(0);

		String pathFull = administrativaBss.getValor("IMAGEM_PROD") + "/" + primeiraImagem.getId().getCodProduto() + "-"
				+ primeiraImagem.getId().getCodDetProduto() + "-" + primeiraImagem.getId().getCodProdDetImagem()
				+ ".jpg";
		try {
			File imagem = new File(pathFull);

			if (imagem.exists()) {
				FileInputStream fis;

				fis = new FileInputStream(imagem);

				byte[] buffer = new byte[fis.available()];
				fis.read(buffer);
				fis.close();

				return buffer;
			}
		} catch (IOException e) {
		}
		return null;
	}
}
