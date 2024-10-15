package com.gstock.core.service;

import io.jsonwebtoken.Jwts;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.time.Duration;
import java.time.Instant;
import java.util.Date;
import java.util.UUID;

import static com.gstock.core.CoreConstants.PROJECT_NAME;

@Service
public class AuthJwtService {

    public String getToken(String id) {
        String subject = "Auth";
        Date expiredAt = Date.from(Instant.now().plus(Duration.ofHours(2L)));
        Date notBeforeAt = Date.from(Instant.now());
        Date issuedAt = Date.from(Instant.now());
        String jwtId = UUID.randomUUID().toString();
        SecretKey key = Jwts.SIG.HS256.key().build();

        return Jwts.builder()
                .issuer(PROJECT_NAME)
                .subject(subject)
                .expiration(expiredAt)
                .notBefore(notBeforeAt)
                .issuedAt(issuedAt)
                .id(jwtId)
                .signWith(key)
                .compact();
    }
}
