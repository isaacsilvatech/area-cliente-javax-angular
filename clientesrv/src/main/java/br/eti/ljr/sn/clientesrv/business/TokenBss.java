package br.eti.ljr.sn.clientesrv.business;

import java.io.Serializable;
import java.nio.charset.StandardCharsets;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;

import javax.crypto.SecretKey;
import javax.ejb.EJB;
import javax.ejb.Stateless;

import br.eti.ljr.sn.clientesrv.domain.Pessoa;
import br.eti.ljr.sn.clientesrv.exception.TokenException;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;

@Stateless
public class TokenBss implements Serializable {

	private static final long serialVersionUID = 1L;

	private static final String SISTEMA = "clientesrv";
	private static final String API_KEY = "API_KEY";

	@EJB
	private AdmBss admBss;

	public String generateTokenWithCodPessoa(Long codPessoa) {

		SecretKey apiKey = getApiKey();

		Claims userDatails = Jwts.claims();
		userDatails.put("codPessoa", codPessoa);

		String jwtToken = Jwts.builder().setIssuer(SISTEMA).setClaims(userDatails).setIssuedAt(new Date())
				.setExpiration(Date.from(LocalDateTime.now().plusHours(6L).atZone(ZoneId.systemDefault()).toInstant()))
				.signWith(apiKey).compact();

		return jwtToken;
	}

	private SecretKey getApiKey() {
		String apiKeyValue = admBss.getValor(API_KEY) + SISTEMA;
		return Keys.hmacShaKeyFor(apiKeyValue.getBytes(StandardCharsets.UTF_8));
	}

	public Long getCodPessoaByClaimsWithValidation(String token) {
		try {
			return Long.valueOf(Jwts.parserBuilder().setSigningKey(getApiKey()).build().parseClaimsJws(token).getBody()
					.get("codPessoa").toString());
		} catch (Exception e) {
			throw new TokenException("Token inválido!");
		}
	}

	private SecretKey newKey(Pessoa pessoa) {
		String apiKeyValue = admBss.getValor(API_KEY) + SISTEMA + pessoa.getCpfCnpj() + "newKey";
		return Keys.hmacShaKeyFor(apiKeyValue.getBytes(StandardCharsets.UTF_8));
	}

	public String generateTokenByNewKeyWithPessoa(Pessoa pessoa, Date expiration) {

		SecretKey key = newKey(pessoa);

		Claims userDatails = Jwts.claims();
		userDatails.put("codPessoa", pessoa.getCodPessoa());

		String jwtToken = Jwts.builder().setIssuer(SISTEMA).setClaims(userDatails).setIssuedAt(new Date())
				.setExpiration(expiration).signWith(key).compact();

		return jwtToken;
	}

	public void validateTokenWithNewKeyPessoa(String token, Pessoa pessoa) {
		SecretKey key = newKey(pessoa);
		try {
			Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(token);
		} catch (Exception e) {
			throw new TokenException("Token inválido!");
		}
	}

	public Long getCodPessoaByClaimsNotValidation(String token) {
		try {
			Claims claims = getClaimsNotValidation(token);
			return Long.valueOf(claims.get("codPessoa").toString());
		} catch (Exception e) {
			throw new TokenException("Token inválido!");
		}
	}

	private Claims getClaimsNotValidation(String token) {
		String[] partes = token.split("\\.");
		if (partes.length != 3) {
			throw new IllegalArgumentException("Token malformado.");
		}
		return Jwts.parserBuilder().build().parseClaimsJwt(partes[0] + "." + partes[1] + ".").getBody();
	}
}
