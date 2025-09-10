package com.steam.service.jwt;

import com.steam.domain.entity.User;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.time.Instant;
import java.util.Collections;
import java.util.Date;

@Slf4j
@Service
public class JwtService {
    @Value("${spring.token.signing.key}")
    private String secretKey;

    public String generateAccessToken(User user) {
        return Jwts.builder()
                .setSubject(user.getSteamId())
                .setIssuedAt(Date.from(Instant.now()))
                .setExpiration(Date.from(Instant.now().plusSeconds(3600)))
                .signWith(getSigningKey(), SignatureAlgorithm.HS512)
                .compact();
    }

    public boolean validateToken(String token) {
        try {
            var parser = Jwts.parserBuilder()
                    .setSigningKey(getSigningKey())
                    .build();
            var claims = parser.parseClaimsJws(token).getBody();
            var steamId = claims.getSubject();

            var expiryDate = claims.getExpiration();
            var expiryInstant = expiryDate.toInstant();
            var now = Instant.now();

            var auth = new UsernamePasswordAuthenticationToken(
                    steamId, "", Collections.emptyList());
            SecurityContextHolder.getContext().setAuthentication(auth);

            if (expiryInstant.isBefore(now)) {
                log.info("Срок действия токена JWT истек");
                return false;
            }
            return true;
        } catch (JwtException | IllegalArgumentException e) {
            log.info("Недопустимый токен JWT: {}", e.getMessage());
            return false;
        }
    }

    public String getSteamIdFromToken(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(getSigningKey())
                .build()
                .parseClaimsJws(token)
                .getBody()
                .getSubject();
    }

    private SecretKey getSigningKey() {
        byte[] keyBytes = Decoders.BASE64.decode(secretKey);
        return Keys.hmacShaKeyFor(keyBytes);
    }

}
