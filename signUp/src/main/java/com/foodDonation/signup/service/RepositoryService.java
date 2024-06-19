package com.foodDonation.signup.service;

import com.foodDonation.signup.data.Role;
import com.foodDonation.signup.data.UserData;
import com.foodDonation.signup.data.UserRepository;
import lombok.AllArgsConstructor;
//import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;

@org.springframework.stereotype.Service
@AllArgsConstructor

public class RepositoryService {
    private UserRepository userRepository;
    private PasswordEncoder passwordEncoder;

    public void save(UserData userData){
        userData.setPassword(
                passwordEncoder.encode(userData.getPassword())
        );
        //userData.setRole(Role.valueOf("ROLE_"+userData.getRole().name()));    ithu namba validation panra edathula pathukala
        userData.setRole(Role.USER);
        userRepository.save(userData);
    }
}
