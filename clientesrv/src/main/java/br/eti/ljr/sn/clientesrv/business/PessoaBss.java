package br.eti.ljr.sn.clientesrv.business;

import java.time.LocalDate;
import java.util.Date;

import javax.ejb.Stateless;

import br.eti.ljr.sn.clientesrv.domain.Pessoa;
import br.eti.ljr.sn.clientesrv.dto.PessoaDados;
import br.eti.ljr.sn.clientesrv.util.Funcoes;

@Stateless
public class PessoaBss extends Bss<Pessoa> {

	private static final long serialVersionUID = 1L;
	
	public static final byte TIPO_DATA_NASCIMENTO = 1;
	public static final byte TIPO_DATA_NASC_DIA_MES = 2;

	public Pessoa getEntityByCpfCnpj(String cpfCnpj) {
		return dao.getEntityByCond("cpfCnpj = '" + cpfCnpj + "'");
	}

	public PessoaDados salvar(PessoaDados dados) {
		
		Pessoa pessoa = getEntityByCpfCnpj(dados.cpfCnpj());
		
		pessoa.setNomeRazaoSocial(dados.nomeRazaoSocial());
		pessoa.setDataAtualizacao(new Date());
		pessoa.setTipoNascimento(dados.tipoNascimento());
		pessoa.setNascDia(dados.nascDia());
		pessoa.setNascMes(dados.nascMes());
		pessoa.setDataNacimento(dados.dataNacimento());
		pessoa.setCelular(dados.celular());
		pessoa.setReceberContatoColaborador(dados.receberContatoColaborador());
		pessoa.setReceberOfertas(dados.receberOfertas());

		replaceData(pessoa);
		
		pessoa = merge(pessoa);
		
		return new PessoaDados(pessoa);
	}
	
	private void replaceData(Pessoa cliente) {

		String nomeRazaoSocial = Funcoes.removeEspacosDuplos(cliente.getNomeRazaoSocial());
		cliente.setNomeRazaoSocial(nomeRazaoSocial.toUpperCase());

		preencherDataNascimento(cliente);
	}
	
	
	
	private void preencherDataNascimento(Pessoa entity) {

		if (entity.getTipoNascimento() == null) {
			entity.setDataNacimento(null);
			entity.setNascDia(null);
			entity.setNascMes(null);

		} else if (entity.getTipoNascimento() == 2) {
			entity.setDataNacimento(null);
		}

		if (entity.getDataNacimento() != null) {
			LocalDate date = Funcoes.dateToLocalDate(entity.getDataNacimento());
			entity.setTipoNascimento(TIPO_DATA_NASCIMENTO);
			entity.setNascDia((byte) date.getDayOfMonth());
			entity.setNascMes((byte) date.getMonthValue());
		}

	}

}
