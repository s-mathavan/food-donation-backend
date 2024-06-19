package com.foodDonation.feedService.repository;

import com.foodDonation.feedService.data.UserData;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserDataRepository extends JpaRepository<UserData,Integer> {
}
