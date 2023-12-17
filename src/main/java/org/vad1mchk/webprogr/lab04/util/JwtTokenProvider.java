package org.vad1mchk.webprogr.lab04.util;

import io.jsonwebtoken.*;
import java.util.Date;
import io.jsonwebtoken.security.Keys;
import io.jsonwebtoken.security.SecureDigestAlgorithm;
import jakarta.ejb.EJB;
import jakarta.ejb.Stateless;
import org.vad1mchk.webprogr.lab04.database.DisposedJwtDao;

import javax.crypto.SecretKey;

@Stateless
public class JwtTokenProvider {
    public static final long EXPIRATION_TIMEOUT_MS = 24 * 60 * 60 * 1000; // 24 hours

    private final SecretKey secretKey;

    @EJB
    private DisposedJwtDao disposedJwtDao;

    {
        String secret = System.getenv("w_SECRET_KEY");
        secretKey = Keys.hmacShaKeyFor(secret.getBytes());
    }

    public String generateToken(String subject) {
        return Jwts.builder()
                .subject(subject)
                .issuedAt(new Date())
                .expiration(new Date(System.currentTimeMillis() + EXPIRATION_TIMEOUT_MS))
                .signWith(secretKey, Jwts.SIG.HS256)
                .compact();
    }

    public boolean validateToken(String token) {
        try {
            if (disposedJwtDao.exists(token)) return false;
            Jwts.parser()
                    .verifyWith(secretKey)
                    .build()
                    .parseSignedClaims(token);
            return true;
        } catch (JwtException | IllegalArgumentException e) {
            // Log the exception details (optional)
            return false;
        }
    }

    public String getSubject(String token) {
        Claims claims = Jwts.parser()
                .verifyWith(secretKey)
                .build()
                .parseSignedClaims(token)
                .getBody();
        return claims.getSubject();
    }
}
