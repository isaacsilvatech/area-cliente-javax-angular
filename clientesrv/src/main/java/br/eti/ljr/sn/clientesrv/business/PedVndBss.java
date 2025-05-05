package br.eti.ljr.sn.clientesrv.business;

import java.math.BigDecimal;
import java.util.List;

import javax.ejb.Stateless;

import br.eti.ljr.sn.clientesrv.domain.PedVnd;

@Stateless
public class PedVndBss extends Bss<PedVnd> {

	private static final long serialVersionUID = 1L;

	public static final byte STATUS_GRAVADO = 1;
	public static final byte STATUS_ENVIADO = 2;
	public static final byte STATUS_PAGO = 3;
	public static final byte STATUS_DESPACHADO = 4;
	public static final byte STATUS_ENTREGUE = 5;
	public static final byte STATUS_FINALIZADO = 6;
	public static final byte STATUS_CANCELADO = 7;
	public static final byte STATUS_EXPIRADO = 8;

	public List<Integer> getList(String codPedido, Long codCliente, Boolean gravado, Boolean enviado,
			Boolean pago, Boolean despachado, Boolean entregue, Boolean finalizado, Boolean cancelado, Boolean expirado,
			String dataDe, String dataAte) {

		StringBuilder sql = new StringBuilder();
		sql.append("""
				SELECT
				    PED_VND.COD_PED_VND
				FROM
				    PED_VND
				    LEFT JOIN PED_VND_PROD ON PED_VND_PROD.COD_PED_VND = PED_VND.COD_PED_VND
				WHERE
				        PED_VND.COD_PED_VND <> 0
										""");

		sql.append(" AND PED_VND.COD_CLIENTE = '" + codCliente + "'");

		if (!codPedido.equals("null") && !codPedido.isBlank()) {
			sql.append(" AND PED_VND.COD_PED_VND = " + codPedido);
		}

		if (!dataDe.equals("null") && !dataDe.isBlank()) {
			sql.append(" AND TRUNC(PED_VND.DATA_CADASTRO) >= TO_DATE('" + dataDe + "', 'DD/MM/YYYY')");
		}

		if (!dataAte.equals("null") && !dataAte.isBlank()) {
			sql.append(" AND TRUNC(PED_VND.DATA_CADASTRO) <= TO_DATE('" + dataAte + "', 'DD/MM/YYYY')");
		}

		if (gravado || enviado || pago || despachado || entregue || finalizado || cancelado || expirado) {

			sql.append(" AND ( ");
			sql.append(" PED_VND.STATUS < 0 ");

			if (gravado) {
				sql.append(" OR PED_VND.STATUS = " + STATUS_GRAVADO);
			}

			if (enviado) {
				sql.append(" OR PED_VND.STATUS = " + STATUS_ENVIADO);
			}

			if (pago) {
				sql.append(" OR PED_VND.STATUS = " + STATUS_PAGO);
			}

			if (despachado) {
				sql.append(" OR PED_VND.STATUS = " + STATUS_DESPACHADO);
			}

			if (entregue) {
				sql.append(" OR PED_VND.STATUS = " + STATUS_ENTREGUE);
			}
			if (finalizado) {
				sql.append(" OR PED_VND.STATUS = " + STATUS_FINALIZADO);
			}

			if (cancelado) {
				sql.append(" OR PED_VND.STATUS = " + STATUS_CANCELADO);
			}

			if (expirado) {
				sql.append(" OR PED_VND.STATUS = " + STATUS_EXPIRADO);
			}
			sql.append(" ) ");
		}

		sql.append("""
				ORDER BY
					PED_VND.DATA_CADASTRO DESC
								""");

		List<?> list = dao.getListByNativeQueryTypeless(sql.toString());

		return list.stream().map(obj -> ((BigDecimal) obj).intValue()).toList();
	}
}
