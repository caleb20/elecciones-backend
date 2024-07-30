package com.caleb.elecciones.auth;

import com.caleb.elecciones.exception.VotoExistenteException;
import com.caleb.elecciones.model.Token;
import com.caleb.elecciones.model.Usuario;
import com.caleb.elecciones.repository.TokenRepository;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;

@Service
public class JwtService {
    @Value("${security.jwt.secret-key}")
    private String secretKey;

    @Value("${security.jwt.expiration-time}")
    private long jwtExpiration;

    @Autowired
    private TokenRepository tokenRepository;

    public String extractUsername(String token) {
        return extractClaim(token, Claims::getSubject);
    }

    public <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = extractAllClaims(token);
        return claimsResolver.apply(claims);
    }

    public String generateToken(Usuario userDetails) {
        Map<String, Object> extraClaims = new HashMap<>();
        extraClaims.put("codigoAlumno", userDetails.getCodigo());
        return generateToken(extraClaims, userDetails);
    }

    public String generateToken(Map<String, Object> extraClaims, Usuario userDetails) {
        return buildToken(extraClaims, userDetails, jwtExpiration);
    }

    public String generateRefreshToken(Usuario userDetails) {
        Map<String, Object> extraClaims = new HashMap<>();
        extraClaims.put("codigoAlumno", userDetails.getCodigo());
        return buildToken(extraClaims, userDetails, jwtExpiration * 5);
    }

    public long getExpirationTime() {
        return jwtExpiration;
    }

    private String buildToken(
            Map<String, Object> extraClaims,
            UserDetails userDetails,
            long expiration
    ) {
        String token = Jwts
                .builder()
                .setClaims(extraClaims)
                .setSubject(userDetails.getUsername())
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + expiration))
                .signWith(getSignInKey(), SignatureAlgorithm.HS256)
                .compact();

        Token tokenEntity = new Token();
        tokenEntity.setToken(token);
        tokenEntity.setCorreo(userDetails.getUsername());
        tokenEntity.setIsExpired(false);
        tokenEntity.setIsRevoked(false);
        tokenRepository.save(tokenEntity);

        return token;
    }

    public boolean isTokenValid(String token, UserDetails userDetails) {
        final String username = extractUsername(token);
        Token storedToken = tokenRepository.findByToken(token)
                .orElseThrow(() -> new RuntimeException("Token not found"));

        Boolean isvalid = (username.equals(userDetails.getUsername())) &&
                !isTokenExpired(token) &&
                !storedToken.getIsExpired() &&
                !storedToken.getIsRevoked();
        if (isvalid.equals(Boolean.FALSE)) {
            throw new RuntimeException("Token not found");
        }
        return isvalid;
    }

    private boolean isTokenExpired(String token) {
        return extractExpiration(token).before(new Date());
    }

    private Date extractExpiration(String token) {
        return extractClaim(token, Claims::getExpiration);
    }

    private Claims extractAllClaims(String token) {
        return Jwts
                .parserBuilder()
                .setSigningKey(getSignInKey())
                .build()
                .parseClaimsJws(token)
                .getBody();
    }

    private Key getSignInKey() {
        byte[] keyBytes = Decoders.BASE64.decode(secretKey);
        return Keys.hmacShaKeyFor(keyBytes);
    }

    public void invalidateAllTokens(String correo) {
        List<Token> tokens = tokenRepository.findAllByCorreo(correo);
        tokens.forEach(token -> {
            token.setIsExpired(true);
            token.setIsRevoked(true);
        });
        tokenRepository.saveAll(tokens);
    }

    public void invalidateToken(String token) {
        Token storedToken = tokenRepository.findByToken(token)
                .orElseThrow(() -> new RuntimeException("Token not found"));
        storedToken.setIsExpired(true);
        storedToken.setIsRevoked(true);
        tokenRepository.save(storedToken);
    }
}