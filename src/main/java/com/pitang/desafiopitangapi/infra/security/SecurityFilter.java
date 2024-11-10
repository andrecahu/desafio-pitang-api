package com.pitang.desafiopitangapi.infra.security;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.pitang.desafiopitangapi.exceptions.InvalidTokenException;
import com.pitang.desafiopitangapi.infra.RestErrorMessage;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import com.pitang.desafiopitangapi.model.User;
import com.pitang.desafiopitangapi.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.Collections;

@Component
public class SecurityFilter extends OncePerRequestFilter {

    @Autowired
    TokenService tokenService;
    @Autowired
    UserRepository userRepository;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        try {
            var token = tokenService.recoverToken(request);
            if ((token == null ||token.isEmpty()) && !request.getRequestURI().contains("/signin") && !request.getRequestURI().contains("/users"))
                throw new InvalidTokenException("Unauthorized");

            var login = tokenService.verifyToken(token);

            if (login != null) {
                User user = userRepository.findByLogin(login).orElseThrow(() -> new InvalidTokenException("Unauthorized - invalid session"));
                var authorities = Collections.singletonList(new SimpleGrantedAuthority("ROLE_USER"));
                var authentication = new UsernamePasswordAuthenticationToken(user, null, authorities);
                SecurityContextHolder.getContext().setAuthentication(authentication);
            }
            filterChain.doFilter(request, response);
        }catch (InvalidTokenException e) {
            sendErrorResponse(response, e.getMessage());
        }
    }

    private void sendErrorResponse(HttpServletResponse response, String message) throws IOException {
        RestErrorMessage restErrorMessage = new RestErrorMessage(message, HttpStatus.UNAUTHORIZED);
        String json = new ObjectMapper().writerWithDefaultPrettyPrinter()
                .writeValueAsString(restErrorMessage);

        response.setStatus(HttpStatus.UNAUTHORIZED.value());
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        response.getWriter().write(json);

    }
}