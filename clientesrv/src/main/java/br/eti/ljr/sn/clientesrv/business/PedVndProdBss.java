package br.eti.ljr.sn.clientesrv.business;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.List;
import java.util.Objects;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import br.eti.ljr.sn.clientesrv.domain.PedVndProd;
import br.eti.ljr.sn.clientesrv.domain.ProdutoImg;

@Stateless
public class PedVndProdBss extends Bss<PedVndProd> {

	private static final long serialVersionUID = 1L;

	@EJB
	private ProdutoDetalheImagemBss produtoDetalheImagemBss;

	@EJB
	private ProdutoImgBss produtoImgBss;

	@EJB
	private AdmBss admBss;

	public List<PedVndProd> getList(Integer codPedVnd) {
		return dao.getListByCond("id.codPedVnd = " + codPedVnd);
	}

	public byte[] getImagem(Integer codProduto, Integer codProdutoDetalhe) throws IOException {

		byte[] imagem = produtoDetalheImagemBss.getImagem(codProduto, codProdutoDetalhe);
		if (Objects.nonNull(imagem)) {
			return imagem;
		}
		ProdutoImg produtoImg = produtoImgBss.getEntity(codProduto);
		if (Objects.nonNull(produtoImg)) {
			return produtoImg.getImagem();
		}
		File file = fileTempProdImg(codProduto, codProdutoDetalhe);
		if (file.exists()) {
			return Files.readAllBytes(file.toPath());
		}

		return null;
	}

	private File fileTempProdImg(Integer codProduto, Integer codProdutoDetalhe) {

		String repo = admBss.getValor("REPO");

		StringBuilder path = new StringBuilder(repo);

		path.append("/onmi/temp/prod/img");
		path.append("/prod-");
		path.append(codProduto);
		path.append("-");
		path.append(codProdutoDetalhe);
		path.append(".jpg");

		return new File(path.toString());
	}
}
