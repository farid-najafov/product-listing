package login.service;

import io.jsonwebtoken.*;
import login.util.Constants;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.Optional;

@Log4j2
@Service
@PropertySource("classpath:jwt.properties")
public class JwtService {

    @Value("${jwt.expiry.normal}")
    private Long expiration_normal;

    @Value("${jwt.expiry.remember}")
    private Long expiration_remember;

    @Value("${jwt.secret}")
    private String jwtSecret;

    public String generateToken(String randomId, Boolean rememberMe) {

        Date now = new Date();
        final long delta = rememberMe ? expiration_remember : expiration_normal;
        Date expiration = new Date(now.getTime() + delta);

        return Jwts.builder()
                .setSubject(randomId)
                .setIssuedAt(now)
                .setExpiration(expiration)
                .signWith(SignatureAlgorithm.HS512, jwtSecret)
                .compact();
    }

    public Optional<String> extractToken(HttpServletRequest request) {
        return Optional.ofNullable(request.getHeader(Constants.HEADER))
                .filter(token -> token.startsWith(Constants.PREFIX))
                .map(token -> token.substring(Constants.PREFIX.length()));
    }

    public Optional<Jws<Claims>> tokenToClaims(String token) {
        try {
            return Optional.of(Jwts.parser()
                    .setSigningKey(jwtSecret)
                    .parseClaimsJws(token));
        } catch (SignatureException ex) {
            log.error("JWT: Invalid signature");
        } catch (MalformedJwtException ex) {
            log.error("JWT: Invalid token");
        } catch (ExpiredJwtException ex) {
            log.error("JWT: Expired token");
        } catch (UnsupportedJwtException ex) {
            log.error("JWT: Unsupported token");
        } catch (IllegalArgumentException ex) {
            log.error("JWT: token is empty.");
        }
        return Optional.empty();
    }

    public String claimsToId(Jws<Claims> claims) {
        return claims.getBody().getSubject();
    }
}
