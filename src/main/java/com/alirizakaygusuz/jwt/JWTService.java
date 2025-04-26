package com.alirizakaygusuz.jwt;

import java.security.Key;
import java.util.Date;
import java.util.function.Function;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import com.alirizakaygusuz.exception.BaseException;
import com.alirizakaygusuz.exception.ErrorMessage;
import com.alirizakaygusuz.exception.ErrorType;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.SignatureException;
import io.jsonwebtoken.UnsupportedJwtException;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;

@Service
public class JWTService {

	@Value("${jwt.secret.key}")
	private String SECRET_KEY;

	public String generateToken(UserDetails userDetails) {
		return Jwts.builder()
				.setSubject(userDetails.getUsername())
				.setIssuedAt(new Date())
				.setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60 * 4))
				.signWith(getKey(), SignatureAlgorithm.HS256)
				.compact();

	}
	
	public String getUsernameByToken(String token) {
		return extractClaimFromToken(token, Claims::getSubject);
	}

	
	public boolean isTokenValid(String token) {
        try {
            Claims claims = getClaims(token);  // Claims'i al
            return new Date().before(claims.getExpiration());  // Geçerlilik süresi kontrolü
        } catch (ExpiredJwtException e) {
            throw new BaseException(new ErrorMessage(ErrorType.EXPIRED_TOKEN, e.getMessage()));
        } catch (SignatureException e) {
            throw new BaseException(new ErrorMessage(ErrorType.SIGNATURE_INVALID, e.getMessage()));
        } catch (MalformedJwtException e) {
            throw new BaseException(new ErrorMessage(ErrorType.MALFORMED_TOKEN, e.getMessage()));
        } catch (UnsupportedJwtException e) {
            throw new BaseException(new ErrorMessage(ErrorType.UNSUPPORTED_TOKEN, e.getMessage()));
        } catch (Exception e) {
            throw new BaseException(new ErrorMessage(ErrorType.GENERAL_TOKEN_EXCEPTION, e.getMessage()));
        }
    }
	
	
	public <T> T extractClaimFromToken(String token, Function<Claims, T> claimsFunction) {
		Claims claims = getClaims(token);

		return claimsFunction.apply(claims);

	}

	public Claims getClaims(String token) {
		return Jwts.parserBuilder().setSigningKey(getKey()).build().parseClaimsJws(token).getBody();

	}


	public Key getKey() {
		byte[] bytes = Decoders.BASE64.decode(SECRET_KEY);

		return Keys.hmacShaKeyFor(bytes);
	}
}
