package com.foodDonation.postCreation.config;

import com.foodDonation.postCreation.data.UserData;
import com.foodDonation.postCreation.data.UserId;
import com.foodDonation.postCreation.repository.UserDataRepository;
import com.foodDonation.postCreation.service.JwtService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.lang.NonNull;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.RequestScope;
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

        //UserId userId = userIdFactory.createUserId(id);         //create userid bean


        if(firstName != null && SecurityContextHolder.getContext().getAuthentication() == null){
            UserData userData =userDataRepository.findById(userId).get();

            if (jwtService.isTokenValid(jwt,userData)){

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
        // Set userId as a request attribute
        request.setAttribute("userId", userId);     //this is the best approach to send the data to controller

        filterChain.doFilter(request,response);

        //userIdFactory.destroyUserId(userId);    //destroy the userId bean
    }

//    @Bean
//    @RequestScope
//    public UserId setUserId(){          //error can be caused by this method
//        UserId data =new UserId(userId);
//        return data;
//    }


}
