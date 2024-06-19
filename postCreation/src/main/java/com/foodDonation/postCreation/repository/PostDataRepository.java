package com.foodDonation.postCreation.repository;

import com.foodDonation.postCreation.data.PostData;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostDataRepository extends JpaRepository<PostData,Integer> {
}
