package com.dux.security.config.auth;

import com.dux.security.service.impl.AuthServiceImpl;
import com.dux.security.service.impl.TokenService;
import io.micrometer.common.lang.NonNull;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import org.springframework.web.servlet.HandlerExceptionResolver;

import java.io.IOException;

@Component
public class AuthFilter extends OncePerRequestFilter {
    private static final Logger LOGGER = LoggerFactory.getLogger(AuthFilter.class);
    private final HandlerExceptionResolver handlerExceptionResolver;

    private final TokenService tokenService;
    private final UserDetailsService userDetailsService;

    public AuthFilter(
        @Lazy TokenService tokenService,
        @Lazy UserDetailsService userDetailsService,
        HandlerExceptionResolver handlerExceptionResolver
    ) {
        this.tokenService = tokenService;
        this.userDetailsService = userDetailsService;
        this.handlerExceptionResolver = handlerExceptionResolver;
    }

    @Override
    protected void doFilterInternal(
        @NonNull HttpServletRequest request,
        @NonNull HttpServletResponse response,
        @NonNull FilterChain filterChain
    ) throws ServletException, IOException {

        LOGGER.info(":: COMIENZA EJECUCION DE VALIDACION DE TOKEN ::");
        final String authHeader = request.getHeader("Authorization");

        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            filterChain.doFilter(request, response);
            return;
        }

        LOGGER.info(":: VALIDADA EXISTENCIA HEADER AUTHORIZATION  ::");

        try {
            final String jwt = authHeader.substring(7);
            final String userName = tokenService.extractUsername(jwt);

            LOGGER.info(":: USERNAME EXTRAIDO: {}  ::",userName);

            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

            if (userName != null && authentication == null) {
                UserDetails userDetails = this.userDetailsService.loadUserByUsername(userName);

                LOGGER.info(":: USUARIO OBTENIDO MEDIANTE USERNAME: {}  ::",userDetails);
                LOGGER.info(":: JWT A VALIDAR: {}  ::",jwt);

                if ( tokenService.isTokenValid(jwt, userDetails)) {
                    UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(
                        userDetails,
                        null,
                        userDetails.getAuthorities()
                    );

                    authToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                    SecurityContextHolder.getContext().setAuthentication(authToken);
                }else{
                    LOGGER.info(":: FALLO POR TOKEN INVALIDO: {}  ::",userDetails);
                }
            }
            LOGGER.info(":: VALIDACION DE TOKEN EXITOSA ::");

            filterChain.doFilter(request, response);
        } catch (Exception exception) {
            LOGGER.info(":: FALLO EN VALIDACION DE TOKEN, exception: {}  ::",exception.getMessage());
            handlerExceptionResolver.resolveException(request, response, null, exception);
        }
    }
}
