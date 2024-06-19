package com.foodDonation.feedService.repository;

import com.foodDonation.feedService.data.PostData;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostDataRepository extends JpaRepository<PostData,Integer> {
}
