package senla.com.security;

import jakarta.servlet.FilterChain;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

@Slf4j
@Component
@RequiredArgsConstructor
public class JwtAuthFilter extends OncePerRequestFilter {

    private final JwtService jwtService;
    private final UserDetailsService userDetailsService;

    @Override
    @SneakyThrows
    protected void doFilterInternal(@NonNull HttpServletRequest request, @NonNull HttpServletResponse response, @NonNull FilterChain filterChain) {
        final String authHeader = request.getHeader("Authorization");
        final String jwtToken;
        final String email;

        if(authHeader == null || !authHeader.startsWith("Bearer ")) {
            log.info("NO HEADER");
            filterChain.doFilter(request, response);
            return;
        }

        jwtToken = authHeader.substring(7);
        email = jwtService.extractEmail(jwtToken);

        if(email != null && SecurityContextHolder.getContext().getAuthentication() == null){
            log.info("GET USER DETAILS");
            UserDetails userDetails = this.userDetailsService.loadUserByUsername(email);

            if(jwtService.isTokenValid(jwtToken, userDetails)){
                log.info("IS TOKEN VALID");

                UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
                authToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                SecurityContextHolder.getContext().setAuthentication(authToken);
            }
        }

        filterChain.doFilter(request, response);
    }
}
