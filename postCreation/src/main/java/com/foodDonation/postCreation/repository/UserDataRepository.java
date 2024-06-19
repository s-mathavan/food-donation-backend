package com.foodDonation.postCreation.repository;

import com.foodDonation.postCreation.data.UserData;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserDataRepository extends JpaRepository<UserData,Integer> {
}
