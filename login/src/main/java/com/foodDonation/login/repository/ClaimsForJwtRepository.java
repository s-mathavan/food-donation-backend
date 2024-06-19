package com.foodDonation.login.repository;

import com.foodDonation.login.data.ClaimsForJwt;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClaimsForJwtRepository extends JpaRepository<ClaimsForJwt,Integer> {

}
