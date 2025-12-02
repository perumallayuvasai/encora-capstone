package com.techademy.orderservice.filter;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

@Component
public class GatewayHeaderFilter extends OncePerRequestFilter {

    private static final Logger logger = LoggerFactory.getLogger(
            GatewayHeaderFilter.class);

    @Override
    protected void doFilterInternal(
            HttpServletRequest request,
            HttpServletResponse response,
            FilterChain filterChain) throws ServletException, IOException {
        String userId = request.getHeader("x-user-id");
        String rolesHeader = request.getHeader("x-user-roles");

        // Add Logger

        if (userId != null && rolesHeader != null) {
            List<SimpleGrantedAuthority> authorities = Arrays.stream(
                    rolesHeader.split(","))
                    .map(role -> new SimpleGrantedAuthority("ROLE_" + role.trim()))
                    .collect(Collectors.toList());

            logger.info(
                    "Authenticated User: {}, Roles: {}",
                    userId,
                    authorities);

            User principal = new User(userId, "PROTECTED", authorities);

            UsernamePasswordAuthenticationToken auth = new UsernamePasswordAuthenticationToken(
                    principal,
                    null,
                    authorities);

            SecurityContextHolder.getContext().setAuthentication(auth);
        } else {
            logger.debug(
                    "Gateway headers missing. x-user-id: {}, x-user-roles: {}",
                    userId,
                    rolesHeader);
        }

        filterChain.doFilter(request, response);
    }
}
