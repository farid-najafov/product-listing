package login.security;

import com.fasterxml.jackson.databind.ObjectMapper;
import login.model.auth.AuthLoginRq;
import login.model.auth.AuthLoginRs;
import login.model.db.MerchantEntity;
import login.service.JwtService;
import login.service.MerchantService;
import lombok.SneakyThrows;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class AuthenticationFilter extends UsernamePasswordAuthenticationFilter {

    private final MerchantService merchantService;
    private final JwtService jwtService;
    private final ObjectMapper objectMapper = new ObjectMapper();

    public AuthenticationFilter(MerchantService merchantService,
                                AuthenticationManager authManager,
                                JwtService jwtService) {

        super.setAuthenticationManager(authManager);
        this.merchantService = merchantService;
        this.jwtService = jwtService;
    }

    @SneakyThrows
    @Override
    public Authentication attemptAuthentication(HttpServletRequest req,
                                                HttpServletResponse resp) throws AuthenticationException {

        AuthLoginRq loginRq = objectMapper.readValue(req.getInputStream(), AuthLoginRq.class);
        resp.addHeader("rememberMe", loginRq.getRememberMe());

        return getAuthenticationManager().authenticate(
                new UsernamePasswordAuthenticationToken(
                        loginRq.getEmail(),
                        loginRq.getPassword()
                )
        );
    }

    @SneakyThrows
    @Override
    protected void successfulAuthentication(HttpServletRequest req,
                                            HttpServletResponse resp,
                                            FilterChain chain,
                                            Authentication authResult) {

        String username = ((User) authResult.getPrincipal()).getUsername();
        MerchantEntity byEmail = merchantService.findByEmail(username);

        String token = jwtService.generateToken(byEmail.getRandomId(), Boolean.parseBoolean(resp.getHeader("rememberMe")));

        resp.addHeader("token", token);
        resp.addHeader("userId", byEmail.getRandomId());
        resp.setContentType("application/json");
        objectMapper.writeValue(resp.getOutputStream(), AuthLoginRs.Ok());
    }

    @Override
    protected void unsuccessfulAuthentication(HttpServletRequest req,
                                              HttpServletResponse resp,
                                              AuthenticationException failed) throws IOException {

        resp.setStatus(HttpStatus.FORBIDDEN.value());
        resp.setContentType("application/json");
        objectMapper.writeValue(resp.getOutputStream(), AuthLoginRs.Fail());
    }

}
