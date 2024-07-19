package com.gov.aesa.seguranca;

import java.security.SecureRandom;
import java.util.Base64;
import java.util.Date;
import org.springframework.stereotype.Component;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.UnsupportedJwtException;

@Component
public class JwtUtil {
	private String secretKey;

	public JwtUtil() {
		// Gerar uma chave secreta aleatória
		SecureRandom random = new SecureRandom();
		byte[] keyBytes = new byte[64];
		random.nextBytes(keyBytes);
		this.secretKey = Base64.getEncoder().encodeToString(keyBytes);
	}

	public String gerarToken(String username) {
		long nowMillis = System.currentTimeMillis();
		long expMillis = nowMillis + 3600000; // Token expira em 1 hora
		Date exp = new Date(expMillis);

		return Jwts.builder()
				.setSubject(username)
				.setIssuedAt(new Date(nowMillis))
				.setExpiration(exp)
				.signWith(SignatureAlgorithm.HS256, secretKey)
				.compact();
	}

	public String extractUsername(String token) {
		return Jwts.parser()
				.setSigningKey(secretKey)
				.parseClaimsJws(token)
				.getBody()
				.getSubject();
	}

	public boolean validateToken(String token) {
		try {
			Jwts.parser().setSigningKey(secretKey).parseClaimsJws(token);
			return true;
		} catch (MalformedJwtException | ExpiredJwtException | UnsupportedJwtException | IllegalArgumentException e) {
			return false;
		}
	}
}
