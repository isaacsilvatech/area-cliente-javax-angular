package br.eti.ljr.sn.clientesrv.dto;

import br.eti.ljr.sn.clientesrv.domain.Pessoa;

public record PessoaLogadaDto(Long codPessoa, String nomeSimples, String nomeCompleto) {

	public PessoaLogadaDto(Pessoa pessoa) {
		this(pessoa.getCodPessoa(), pessoa.getNomeRazaoSocial().split(" ")[0], pessoa.getNomeRazaoSocial());
	}
}
