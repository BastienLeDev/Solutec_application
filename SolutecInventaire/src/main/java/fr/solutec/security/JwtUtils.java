package fr.solutec.security;

import org.springframework.stereotype.Component;
import org.springframework.web.util.WebUtils;

import fr.solutec.services.UserDetailsServiceImpl;

import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import java.util.function.Function;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseCookie;
import org.springframework.security.core.userdetails.UserDetails;

@Component
public class JwtUtils {
	
	 private Key jwtSigningKey = Keys.secretKeyFor(SignatureAlgorithm.HS256);

	    public String extractUsername(String token) {
	        return extractClaim(token, Claims::getSubject);
	    }

	    public Date extractExpiration(String token) {
	        return extractClaim(token, Claims::getExpiration);
	    }

	    public boolean hasClaim(String token, String claimName) {
	        final Claims claims = extractAllClaims(token);
	        return claims.get(claimName) != null;
	    }

	    public <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
	        final Claims claims = extractAllClaims(token);
	        return claimsResolver.apply(claims);
	    }

	    

		private Claims extractAllClaims(String token) {
	        return Jwts.parserBuilder().setSigningKey(jwtSigningKey).build().parseClaimsJws(token).getBody();
	    }

	    private Boolean isTokenExpired(String token) {
	        return extractExpiration(token).before(new Date());
	    }

	    public String generateToken(UserDetails userDetails) {
	        Map<String, Object> claims = new HashMap<>();
	        return createToken(claims, userDetails);
	    }

	    public String generateToken(UserDetails userDetails, Map<String, Object> claims) {
	        return createToken(claims, userDetails);
	    }

		private String createToken(Map<String, Object> claims, UserDetails userDetails) {
	        return Jwts.builder().setClaims(claims)
	                .setSubject(userDetails.getUsername())
	                .claim("authorities", userDetails.getAuthorities())
	                .setIssuedAt(new Date(System.currentTimeMillis()))
	                .setExpiration(new Date(System.currentTimeMillis() + TimeUnit.HOURS.toMillis(24)))
	                .signWith(jwtSigningKey).compact();
	    }

	    public Boolean isTokenValid(String token, UserDetails userDetails) {
	        final String username = extractUsername(token);
	        return (username.equals(userDetails.getUsername()) && !isTokenExpired(token));
	    }

	    
}