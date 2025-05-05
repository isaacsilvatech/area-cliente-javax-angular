package br.eti.ljr.sn.clientesrv.dto;

import java.util.Date;

import br.eti.ljr.sn.clientesrv.domain.Pessoa;

public record PessoaDados(String cpfCnpj, String nomeRazaoSocial, String celular, String email, Boolean colaborador,
		Byte tipoNascimento, Byte nascDia, Byte nascMes, Date dataNacimento, Boolean receberContatoColaborador,
		Boolean receberOfertas) {

	public PessoaDados(Pessoa pessoa) {
		this(pessoa.getCpfCnpj(), pessoa.getNomeRazaoSocial(), pessoa.getCelular(), pessoa.getEmail(),
				pessoa.getColaborador(), pessoa.getTipoNascimento(), pessoa.getNascDia(), pessoa.getNascMes(),
				pessoa.getDataNacimento(), pessoa.getReceberContatoColaborador(), pessoa.getReceberOfertas());
	}

}
