package com.foodDonation.login.service;

import com.foodDonation.login.data.AuthenticationRequestData;
import com.foodDonation.login.repository.DataRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
public class AuthenticateService {

    DataRepository dataRepository;
    PasswordEncoder passwordEncoder;

    public void retrieveData(String phoneNo){
//        System.out.println(dataRepository.getPasswordByPhoneNo(phoneNo).getPassword());
//        System.out.println(dataRepository.getPasswordByPhoneNo(phoneNo).getId());

    }

    public boolean isValid(AuthenticationRequestData userData){
//        userData.setId(dataRepository.);
        Optional<AuthenticationRequestData> fromdb = dataRepository.getPasswordByPhoneNo(userData.getPhoneNo());
        String password= String.valueOf(fromdb.get().getPassword());
        userData.setId(Integer.valueOf(fromdb.get().getId()));
        return passwordEncoder.matches(userData.getPassword(), password);
    }

}
