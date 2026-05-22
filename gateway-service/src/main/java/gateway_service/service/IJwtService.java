package gateway_service.service;

public interface IJwtService {

    void extractClaims(String token);

    boolean validateToken(String token);
}