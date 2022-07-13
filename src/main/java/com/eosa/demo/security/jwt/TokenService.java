package com.eosa.demo.security.jwt;

import java.security.Key;
import java.util.Base64;
import java.util.Date;

import javax.annotation.PostConstruct;
import javax.crypto.spec.SecretKeySpec;
import javax.xml.bind.DatatypeConverter;

import org.springframework.stereotype.Service;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class TokenService {
    
    private String secretKey = "token-secret-key";

    SignatureAlgorithm signatureAlgorithm = SignatureAlgorithm.HS256;
    byte[] secretKeyBytes = DatatypeConverter.parseBase64Binary(secretKey);
    Key signingKey = new SecretKeySpec(secretKeyBytes, signatureAlgorithm.getJcaName());

    @PostConstruct
    protected void init() {
        secretKey = Base64.getEncoder().encodeToString(secretKey.getBytes());
    }

    public Token generateToken(String uid, String role) {
        long tokenPeriod = 1000L * 60L * 10L; // about 10 minute(?)
        long refreshPeriod = 1000L * 60L * 60L * 24L * 30L * 3L;        

        Claims claims = Jwts.claims()
            .setSubject(uid);
        claims.put("role", role);

        log.info("## claims: {}", claims);

        Date now = new Date();
        return new Token(
                "accesstoken",
                "refreshToken");
    }

    public boolean verifyToken(String token) {
        try {           
            Jws<Claims> claims = Jwts.parser()
                .setSigningKey(secretKey)
                .parseClaimsJws(token);
             return claims.getBody()
                .getExpiration()
                .after(new Date());
        }
        catch(Exception e) {
            return false;
        }

    }

    public String getUid(String token) {
        return Jwts.parser().setSigningKey(secretKey).parseClaimsJws(token).getBody().getSubject();
    }

}
