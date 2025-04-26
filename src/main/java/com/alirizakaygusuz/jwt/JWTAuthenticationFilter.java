package com.alirizakaygusuz.jwt;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.alirizakaygusuz.exception.BaseException;
import com.alirizakaygusuz.exception.ErrorMessage;
import com.alirizakaygusuz.exception.ErrorType;

import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.SignatureException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class JWTAuthenticationFilter extends OncePerRequestFilter {

    @Autowired
    private JWTService jwtService;

    @Autowired
    private UserDetailsService userDetailsService;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {

        String header = request.getHeader("Authorization");

        if (header == null || !header.startsWith("Bearer ")) {
            filterChain.doFilter(request, response);
            return;
        }

        String token = header.substring(7);

        try {
            String userNameByToken = jwtService.getUsernameByToken(token);

            if (userNameByToken != null && SecurityContextHolder.getContext().getAuthentication() == null) {
                UserDetails userDetails = userDetailsService.loadUserByUsername(userNameByToken);

                if (userDetails != null && jwtService.isTokenValid(token)) {
                    UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(
                            userNameByToken, null, userDetails.getAuthorities());

                    authenticationToken.setDetails(userDetails);

                    SecurityContextHolder.getContext().setAuthentication(authenticationToken);
                }
            }
        } catch (ExpiredJwtException e) {
            throw new BaseException(new ErrorMessage(ErrorType.EXPIRED_TOKEN, "Token has expired. Please log in again."));
        } catch (SignatureException e) {
            // Invalid signature error
            throw new BaseException(new ErrorMessage(ErrorType.INVALID_TOKEN, "Token has an invalid signature."));
        } catch (Exception e) {
            throw new BaseException(new ErrorMessage(ErrorType.GENERAL_TOKEN_EXCEPTION, "An error occurred while processing the token."));
        }

        filterChain.doFilter(request, response);
    }
}
