package login.security;

import io.micrometer.core.lang.NonNullApi;
import login.service.JwtService;
import login.service.MerchantService;
import lombok.extern.log4j.Log4j2;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Log4j2
@Configuration
@NonNullApi
public class AuthorizationFilter extends OncePerRequestFilter {

    private final JwtService jwtService;
    private final MerchantService merchantService;

    public AuthorizationFilter(JwtService jwtService, MerchantService merchantService) {
        this.jwtService = jwtService;
        this.merchantService = merchantService;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain) {
        try {
            jwtService.extractToken(request)
                    .flatMap(jwtService::tokenToClaims)
                    .map(jwtService::claimsToId)
                    .map(merchantService::loadUserById)
                    .map(ud -> new UsernamePasswordAuthenticationToken(ud, null, ud.getAuthorities()))
                    .ifPresent(auth -> {
                        auth.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                        SecurityContextHolder.getContext().setAuthentication(auth);
                    });

            filterChain.doFilter(request, response);

        } catch (Exception ex) {
            log.error(ex.getMessage());
        }
    }
}
