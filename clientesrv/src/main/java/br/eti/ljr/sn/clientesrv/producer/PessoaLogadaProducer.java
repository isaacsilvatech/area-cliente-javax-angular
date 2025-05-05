package br.eti.ljr.sn.clientesrv.producer;

import javax.enterprise.context.RequestScoped;
import javax.enterprise.event.Observes;
import javax.enterprise.inject.Produces;

import br.eti.ljr.sn.clientesrv.domain.Pessoa;
import br.eti.ljr.sn.clientesrv.filter.PessoaLogada;

@RequestScoped
public class PessoaLogadaProducer {

	@Produces
	@RequestScoped
	@PessoaLogada
	private Pessoa pessoaLogada;

	public void handleAuthenticationEvent(@Observes @PessoaLogada Pessoa pessoaLogada) {
		this.pessoaLogada = pessoaLogada;
	}
}
