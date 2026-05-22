package gateway_service.service.impl;

import gateway_service.service.IJwtService;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;

@Service
public class IJwtServiceImpl implements IJwtService {

    @Value("${jwt.secret}")
    private String secretKey;


    public void extractClaims(String token) {
        SecretKey key = Keys.hmacShaKeyFor(secretKey.getBytes(StandardCharsets.UTF_8));
        Jwts.parser()
                .verifyWith(key)
                .build()
                .parseSignedClaims(token)
                .getPayload();
    }

    public boolean validateToken(String token) {
        try {
            extractClaims(token);
            return true;

        } catch (Exception ex) {
            return false;
        }
    }
}