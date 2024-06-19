package com.foodDonation.feedService.config;

import com.foodDonation.feedService.data.UserData;
import com.foodDonation.feedService.repository.UserDataRepository;
import com.foodDonation.feedService.service.JwtService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.lang.NonNull;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@RequiredArgsConstructor
@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    private final JwtService jwtService;
    private final UserDataRepository userDataRepository;
    //private UserIdFactory userIdFactory;


    @Override
    protected void doFilterInternal(
            @NonNull HttpServletRequest request,@NonNull HttpServletResponse response,@NonNull FilterChain filterChain
    ) throws ServletException, IOException {

        final String authHeader = request.getHeader("Authorization");
        final String jwt;
        final String firstName;
        final Integer userId;

        if (authHeader == null || !authHeader.startsWith("Bearer ")){
            filterChain.doFilter(request,response);
            return ;
        }

        jwt = authHeader.substring(7);
        firstName = jwtService.extractUsername(jwt);
        userId =jwtService.extractId(jwt);




        if(firstName != null && SecurityContextHolder.getContext().getAuthentication() == null){
            UserData userData = userDataRepository.findById(userId).get();

            if (jwtService.isTokenValid(jwt, userData)) {

                UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(
                        userData,
                        null,
                        userData.getAuthorities()
                );
                authToken.setDetails(
                        new WebAuthenticationDetailsSource().buildDetails(request)
                );
                SecurityContextHolder.getContext().setAuthentication(authToken);
            }

        }

        filterChain.doFilter(request,response);


    }
}
