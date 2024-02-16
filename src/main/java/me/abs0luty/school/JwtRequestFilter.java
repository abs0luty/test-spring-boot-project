package me.abs0luty.school;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.AllArgsConstructor;
import me.abs0luty.school.service.JwtService;
import me.abs0luty.school.service.UserService;
import org.springframework.lang.NonNull;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@AllArgsConstructor
@Component
public class JwtRequestFilter extends OncePerRequestFilter {

    private final JwtService jwtService;
    private final UserService userService;

    @Override
    protected void doFilterInternal(
        @NonNull HttpServletRequest request,
        @NonNull HttpServletResponse response,
        @NonNull FilterChain filterChain) throws ServletException, IOException {
       final String header = request.getHeader("Authorization");

       if (header == null || !header.startsWith("Bearer ")) {
           filterChain.doFilter(request, response);
           return;
       }

       final String token = header.substring(7);
       final String email = jwtService.extractEmail(token);

       if (email == null || SecurityContextHolder.getContext().getAuthentication() != null) {
           filterChain.doFilter(request, response);
           return;
       }

       final UserDetails userDetails = userService.loadUserByUsername(email);

       final UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(
           userDetails,
           null,
           userDetails.getAuthorities()
       );
       authToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));

       SecurityContextHolder.getContext().setAuthentication(authToken);
    }
}
