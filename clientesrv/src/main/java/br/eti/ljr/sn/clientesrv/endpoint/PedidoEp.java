package br.eti.ljr.sn.clientesrv.endpoint;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.List;
import java.util.Objects;

import javax.ejb.EJB;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import br.eti.ljr.sn.clientesrv.business.PedVndBss;
import br.eti.ljr.sn.clientesrv.business.PedVndProdBss;
import br.eti.ljr.sn.clientesrv.business.ProdutoBss;
import br.eti.ljr.sn.clientesrv.domain.PedVnd;
import br.eti.ljr.sn.clientesrv.domain.PedVndProd;
import br.eti.ljr.sn.clientesrv.domain.Pessoa;
import br.eti.ljr.sn.clientesrv.domain.Produto;
import br.eti.ljr.sn.clientesrv.dto.PedVndCardDto;
import br.eti.ljr.sn.clientesrv.dto.PedVndProdCardDto;
import br.eti.ljr.sn.clientesrv.filter.Logado;
import br.eti.ljr.sn.clientesrv.filter.PessoaLogada;
import br.eti.ljr.sn.clientesrv.util.Images;

@Logado
@Path("/pedido")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class PedidoEp {

	@Inject
	@PessoaLogada
	private Pessoa pessoaLogada;

	@EJB
	private PedVndBss pedVndBss;

	@EJB
	private PedVndProdBss pedVndProdBss;

	@EJB
	private ProdutoBss produtoBss;

	@GET
	public List<Integer> getList(@QueryParam("codPedVnd") String codPedido, @QueryParam("cpfCnpj") String cpfCnpj,
			@QueryParam("gravado") String gravado, @QueryParam("enviado") String enviado,
			@QueryParam("pago") String pago, @QueryParam("despachado") String despachado,
			@QueryParam("entregue") String entregue, @QueryParam("finalizado") String finalizado,
			@QueryParam("cancelado") String cancelado, @QueryParam("expirado") String expirado,
			@QueryParam("dataDe") String dataDe, @QueryParam("dataAte") String dataAte) {

		return pedVndBss.getList(codPedido, pessoaLogada.getCodPessoa(), Boolean.valueOf(gravado),
				Boolean.valueOf(enviado), Boolean.valueOf(pago), Boolean.valueOf(despachado), Boolean.valueOf(entregue),
				Boolean.valueOf(finalizado), Boolean.valueOf(cancelado), Boolean.valueOf(expirado), dataDe, dataAte);

	}

	@GET
	@Path("/card/{codPedVnd}")
	public PedVndCardDto getPedVndCardDto(@PathParam("codPedVnd") Integer codPedVnd) {

		PedVndCardDto dto = new PedVndCardDto();

		PedVnd pedVnd = pedVndBss.getEntity(codPedVnd);

		dto.setCodPedVnd(pedVnd.getCodPedVnd());
		dto.setDataCadastro(pedVnd.getDataCadastro());
		dto.setStatus(pedVnd.getStatus());

		List<PedVndProd> pedVndProdLsit = pedVndProdBss.getList(codPedVnd);

		BigDecimal valorTotalBruto = pedVndProdLsit.stream().map(PedVndProd::getValorUnitarioBruto)
				.reduce(BigDecimal.ZERO, BigDecimal::add).add(pedVnd.getValorFrete());
		BigDecimal valorTotalDesconto = pedVndProdLsit.stream().map(PedVndProd::getValorUnitarioDesconto)
				.reduce(BigDecimal.ZERO, BigDecimal::add);

		dto.setValorTotalBruto(valorTotalBruto);
		dto.setValorTotalDesconto(valorTotalDesconto);

		return dto;
	}

	@GET
	@Path("/card/prod/{codPedVnd}")
	public List<PedVndProdCardDto> getPedVndProdCardDtoList(@PathParam("codPedVnd") Integer codPedVnd) {

		return pedVndProdBss.getList(codPedVnd).stream().map(prod -> {

			PedVndProdCardDto prodDto = new PedVndProdCardDto();

			Produto produto = produtoBss.getEntity(prod.getCodProduto());

			String codProd = String.valueOf(produto.getCodigoPesq());
			codProd += "." + prod.getCodProdutoDetalhe();
			codProd += "." + prod.getCodProdutoDetalheTam();

			prodDto.setCodProd(codProd);

			try {
				byte[] imagem = pedVndProdBss.getImagem(prod.getCodProduto(), prod.getCodProdutoDetalhe());
				if (Objects.nonNull(imagem)) {
					byte[] imagem64x64 = Images.rezise(imagem, 64, 64);
					prodDto.setImagem(imagem64x64);
				}
			} catch (IOException e) {
				throw new RuntimeException(e);
			}

			return prodDto;
		}).toList();
	}
}
