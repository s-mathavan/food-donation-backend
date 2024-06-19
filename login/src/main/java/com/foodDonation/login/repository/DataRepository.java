package com.foodDonation.login.repository;

import com.foodDonation.login.data.AuthenticationRequestData;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
//import org.springframework.data.jpa.repository.Query;
//import org.springframework.data.repository.query.Param;

public interface DataRepository extends JpaRepository<AuthenticationRequestData, Integer> {

    //@Query("SELECT new com.foodDonation.login.data.UserData(u.phoneNo , u.password) from user_data u where u.phoneNo= :phoneNo")
    Optional<AuthenticationRequestData> getPasswordByPhoneNo(String phoneNo);
}
