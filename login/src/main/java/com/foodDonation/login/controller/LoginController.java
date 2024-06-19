package com.foodDonation.login.controller;

import com.foodDonation.login.data.AuthenticationRequestData;

import com.foodDonation.login.data.AuthenticationResponseData;
import com.foodDonation.login.service.ClaimsDataRetrievalService;
import com.foodDonation.login.service.JwtService;
import com.foodDonation.login.service.AuthenticateService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1")
@AllArgsConstructor
public class LoginController {

    private AuthenticateService service;
    private JwtService jwtService;
    private ClaimsDataRetrievalService claimsDataRetrievalService;

    @PostMapping("/login")
    public ResponseEntity<AuthenticationResponseData> loginPanra(@RequestBody AuthenticationRequestData authenticationRequestData){
        //service.retrieveData(authenticationRequestData.getPhoneNo());
        if(service.isValid(authenticationRequestData)){
            return ResponseEntity.ok(
                    jwtService.generateToken(
                            claimsDataRetrievalService.getClaimsFromDB(authenticationRequestData)
                    )
            );
        }
        else{
            AuthenticationResponseData responseData=AuthenticationResponseData.builder()
                    .message("Incorrect phone number or password")
                    .token(null)
                    .build();
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(responseData);
        }
    }
}
