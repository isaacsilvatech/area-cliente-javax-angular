package br.eti.ljr.sn.clientesrv.domain;
// Generated 15/05/2020 16:28:32 by Hibernate Tools 4.0.0.Final

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.UniqueConstraint;

@Entity
@Table(name = "PESSOA", schema = "CLICK", uniqueConstraints = @UniqueConstraint(columnNames = "CPF_CNPJ"))
public class Pessoa implements Serializable {

	private static final long serialVersionUID = 1L;
	private long codPessoa;
	private String cpfCnpj;
	private Date dataAtualizacao;
	private String nomeRazaoSocial;
	private String apelidoNomeFantasia;
	private Long matricula;
	private String sexo;
	private Date dataCadastro;
	private String senha;
	private Byte tipoLogin;
	private Boolean clienteAtivo;
	private Boolean trasnportadoraAtivo;
	private Byte colaboradorStatus;
	private Date dataAtuSenha;
	private Boolean colaborador;
	private Long codCadastrador;
	private Byte tipoNascimento;
	private Byte nascDia;
	private Byte nascMes;
	private Date dataNacimento;
	private Boolean fornecedor;
	private Boolean cliente;
	private Boolean transpotadora;
	private Boolean fornecedorAtivo;
	private String rg;
	private String inscricaoEstadual;
	private String rgOrgaoExpedidor;
	private Date dataExpedicao;
	private String telefone;
	private String facebook;
	private String instagram;
	private String celular;
	private Integer codLojaCadastrador;
	private Integer codLojaRegistro;
	private Integer codLojaTrabalha;
	private String email;
	private Boolean receberContatoColaborador;
	private Boolean receberOfertas;
	private Byte codProdDetTam;
	private Date dataFidelizacao;
	private Integer codRendaMensal;
	private Integer codGrupoCliente;

	public Pessoa() {
	}

	public Pessoa(long codPessoa) {
		this.codPessoa = codPessoa;
	}

	@Id

	@Column(name = "COD_PESSOA", unique = true, nullable = false, precision = 14, scale = 0)
	public long getCodPessoa() {
		return this.codPessoa;
	}

	public void setCodPessoa(long codPessoa) {
		this.codPessoa = codPessoa;
	}

	@Column(name = "CPF_CNPJ", unique = true, length = 14)
	public String getCpfCnpj() {
		return this.cpfCnpj;
	}

	public void setCpfCnpj(String cpfCnpj) {
		this.cpfCnpj = cpfCnpj;
	}

	@Column(name = "DATA_ATUALIZACAO")
	public Date getDataAtualizacao() {
		return this.dataAtualizacao;
	}

	public void setDataAtualizacao(Date dataAtualizacao) {
		this.dataAtualizacao = dataAtualizacao;
	}

	@Column(name = "NOME_RAZAO_SOCIAL", length = 100)
	public String getNomeRazaoSocial() {
		return this.nomeRazaoSocial;
	}

	public void setNomeRazaoSocial(String nomeRazaoSocial) {
		this.nomeRazaoSocial = nomeRazaoSocial;
	}

	@Column(name = "APELIDO_NOME_FANTASIA", length = 100)
	public String getApelidoNomeFantasia() {
		return this.apelidoNomeFantasia;
	}

	public void setApelidoNomeFantasia(String apelidoNomeFantasia) {
		this.apelidoNomeFantasia = apelidoNomeFantasia;
	}

	@Column(name = "MATRICULA", precision = 10, scale = 0)
	public Long getMatricula() {
		return this.matricula;
	}

	public void setMatricula(Long matricula) {
		this.matricula = matricula;
	}

	@Column(name = "SENHA", length = 32)
	public String getSenha() {
		return this.senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	@Column(name = "TIPO_LOGIN", precision = 2, scale = 0)
	public Byte getTipoLogin() {
		return this.tipoLogin;
	}

	public void setTipoLogin(Byte tipoLogin) {
		this.tipoLogin = tipoLogin;
	}

	@Column(name = "COLABORADOR_STATUS", precision = 2, scale = 0)
	public Byte getColaboradorStatus() {
		return this.colaboradorStatus;
	}

	public void setColaboradorStatus(Byte colaboradorStatus) {
		this.colaboradorStatus = colaboradorStatus;
	}

	@Column(name = "DATA_ATU_SENHA")
	public Date getDataAtuSenha() {
		return this.dataAtuSenha;
	}

	public void setDataAtuSenha(Date dataAtuSenha) {
		this.dataAtuSenha = dataAtuSenha;
	}

	@Column(name = "COLABORADOR", precision = 1, scale = 0)
	public Boolean getColaborador() {
		return this.colaborador;
	}

	public void setColaborador(Boolean colaborador) {
		this.colaborador = colaborador;
	}

	@Column(name = "TIPO_NASCIMENTO", precision = 2, scale = 0)
	public Byte getTipoNascimento() {
		return tipoNascimento;
	}

	public void setTipoNascimento(Byte tipoNascimento) {
		this.tipoNascimento = tipoNascimento;
	}

	@Column(name = "NASC_DIA")
	public Byte getNascDia() {
		return nascDia;
	}

	public void setNascDia(Byte nascDia) {
		this.nascDia = nascDia;
	}

	@Column(name = "NASC_MES")
	public Byte getNascMes() {
		return nascMes;
	}

	public void setNascMes(Byte nascMes) {
		this.nascMes = nascMes;
	}

	@Column(name = "DATA_NACIMENTO")
	public Date getDataNacimento() {
		return this.dataNacimento;
	}

	public void setDataNacimento(Date dataNacimento) {
		this.dataNacimento = dataNacimento;
	}

	@Column(name = "FORNECEDOR")
	public Boolean getFornecedor() {
		return this.fornecedor;
	}

	public void setFornecedor(Boolean fornecedor) {
		this.fornecedor = fornecedor;
	}

	@Column(name = "FORNECEDOR_ATIVO")
	public Boolean getFornecedorAtivo() {
		return this.fornecedorAtivo;
	}

	public void setFornecedorAtivo(Boolean fornecedorAtivo) {
		this.fornecedorAtivo = fornecedorAtivo;
	}

	@Column(name = "RG")
	public String getRg() {
		return rg;
	}

	public void setRg(String rg) {
		this.rg = rg;
	}

	@Column(name = "INSCRICAO_ESTADUAL")
	public String getInscricaoEstadual() {
		return inscricaoEstadual;
	}

	public void setInscricaoEstadual(String inscricaoEstadual) {
		this.inscricaoEstadual = inscricaoEstadual;
	}

	@Column(name = "RG_ORGAO_EXPEDIDOR")
	public String getRgOrgaoExpedidor() {
		return rgOrgaoExpedidor;
	}

	public void setRgOrgaoExpedidor(String rgOrgaoExpedidor) {
		this.rgOrgaoExpedidor = rgOrgaoExpedidor;
	}

	@Column(name = "DATA_EXPEDICAO")
	public Date getDataExpedicao() {
		return dataExpedicao;
	}

	public void setDataExpedicao(Date dataExpedicao) {
		this.dataExpedicao = dataExpedicao;
	}

	@Column(name = "TELEFONE")
	public String getTelefone() {
		return telefone;
	}

	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}

	@Column(name = "FACEBOOK")
	public String getFacebook() {
		return facebook;
	}

	public void setFacebook(String facebook) {
		this.facebook = facebook;
	}

	@Column(name = "INSTAGRAM")
	public String getInstagram() {
		return instagram;
	}

	public void setInstagram(String instagram) {
		this.instagram = instagram;
	}

	@Column(name = "CELULAR")
	public String getCelular() {
		return celular;
	}

	public void setCelular(String celular) {
		this.celular = celular;
	}

	@Column(name = "COD_LOJA_REGISTRO", precision = 9, scale = 0)
	public Integer getCodLojaRegistro() {
		return this.codLojaRegistro;
	}

	public void setCodLojaRegistro(Integer codLojaRegistro) {
		this.codLojaRegistro = codLojaRegistro;
	}

	@Column(name = "COD_LOJA_TRABALHA", precision = 9, scale = 0)
	public Integer getCodLojaTrabalha() {
		return this.codLojaTrabalha;
	}

	public void setCodLojaTrabalha(Integer codLojaTrabalha) {
		this.codLojaTrabalha = codLojaTrabalha;
	}

	@Column(name = "SEXO", length = 1)
	public String getSexo() {
		return sexo;
	}

	public void setSexo(String sexo) {
		this.sexo = sexo;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "DATA_CADASTRO", length = 7)
	public Date getDataCadastro() {
		return dataCadastro;
	}

	public void setDataCadastro(Date dataCadastro) {
		this.dataCadastro = dataCadastro;
	}

	@Column(name = "EMAIL", length = 100)
	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	@Column(name = "CLIENTE_ATIVO", precision = 1, scale = 0)
	public Boolean getClienteAtivo() {
		return this.clienteAtivo;
	}

	public void setClienteAtivo(Boolean clienteAtivo) {
		this.clienteAtivo = clienteAtivo;
	}

	@Column(name = "RECEBER_CONTATO_COLABORADOR")
	public Boolean getReceberContatoColaborador() {
		return receberContatoColaborador;
	}

	public void setReceberContatoColaborador(Boolean receberContatoColaborador) {
		this.receberContatoColaborador = receberContatoColaborador;
	}

	@Column(name = "RECEBER_OFERTAS", precision = 1, scale = 0)
	public Boolean getReceberOfertas() {
		return this.receberOfertas;
	}

	public void setReceberOfertas(Boolean receberOfertas) {
		this.receberOfertas = receberOfertas;
	}

	@Column(name = "COD_PROD_DET_TAM", precision = 2, scale = 0)
	public Byte getCodProdDetTam() {
		return this.codProdDetTam;
	}

	public void setCodProdDetTam(Byte codProdDetTam) {
		this.codProdDetTam = codProdDetTam;
	}

	@Column(name = "DATA_FIDELIZACAO")
	public Date getDataFidelizacao() {
		return dataFidelizacao;
	}

	public void setDataFidelizacao(Date dataFidelizacao) {
		this.dataFidelizacao = dataFidelizacao;
	}

	@Column(name = "COD_CADASTRADOR", precision = 14, scale = 0)
	public Long getCodCadastrador() {
		return this.codCadastrador;
	}

	public void setCodCadastrador(Long codCadastrador) {
		this.codCadastrador = codCadastrador;
	}

	@Column(name = "COD_LOJA_CADASTRADOR", precision = 9, scale = 0)
	public Integer getCodLojaCadastrador() {
		return this.codLojaCadastrador;
	}

	public void setCodLojaCadastrador(Integer codLojaCadastrador) {
		this.codLojaCadastrador = codLojaCadastrador;
	}

	@Column(name = "COD_RENDA_MENSAL", precision = 9, scale = 0)
	public Integer getCodRendaMensal() {
		return this.codRendaMensal;
	}

	public void setCodRendaMensal(Integer codRendaMensal) {
		this.codRendaMensal = codRendaMensal;
	}

	@Column(name = "CLIENTE")
	public Boolean getCliente() {
		return cliente;
	}

	public void setCliente(Boolean cliente) {
		this.cliente = cliente;
	}

	@Column(name = "TRASNPORTADORA_ATIVO")
	public Boolean getTrasnportadoraAtivo() {
		return trasnportadoraAtivo;
	}

	public void setTrasnportadoraAtivo(Boolean trasnportadoraAtivo) {
		this.trasnportadoraAtivo = trasnportadoraAtivo;
	}

	@Column(name = "TRANSPOTADORA")
	public Boolean getTranspotadora() {
		return transpotadora;
	}

	public void setTranspotadora(Boolean transpotadora) {
		this.transpotadora = transpotadora;
	}

	@Column(name = "COD_GRUPO_CLIENTE")
	public Integer getCodGrupoCliente() {
		return codGrupoCliente;
	}

	public void setCodGrupoCliente(Integer codGrupoCliente) {
		this.codGrupoCliente = codGrupoCliente;
	}
}
