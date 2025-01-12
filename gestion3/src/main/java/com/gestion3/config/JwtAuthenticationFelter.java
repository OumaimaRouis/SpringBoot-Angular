package com.gestion3.config;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.AllArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Service;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
@AllArgsConstructor
@Service
public class JwtAuthenticationFelter extends OncePerRequestFilter {
    private JwtService jwtService;
    private UserDetailsService userDetailsService;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        if (request.getServletPath().equals("/login"))
        {
            filterChain.doFilter(request,response);
            return;
        }
        String token= jwtService.getTokenFromHeader(request);

        if (token !=null)
        {
            String username=jwtService.getUserNameFromToken(token);
            UserDetails userDetails=userDetailsService.loadUserByUsername(username);
            if(jwtService.validate(userDetails,token))
            {
                if (SecurityContextHolder.getContext().getAuthentication()==null)
                {
                    UsernamePasswordAuthenticationToken authenticationToken=new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
                    authenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                    SecurityContextHolder.getContext().setAuthentication(authenticationToken);
                }
            }
            else SecurityContextHolder.clearContext();
        }
        filterChain.doFilter(request, response);

    }
}
