package com.foodDonation.login.service;

import com.foodDonation.login.data.AuthenticationRequestData;
import com.foodDonation.login.data.ClaimsForJwt;
import com.foodDonation.login.repository.ClaimsForJwtRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class ClaimsDataRetrievalService {

    ClaimsForJwtRepository claimsRepository;

    public ClaimsForJwt getClaimsFromDB(AuthenticationRequestData authenticationRequestData){

        return claimsRepository.findById(authenticationRequestData.getId()).get();
    }

}
