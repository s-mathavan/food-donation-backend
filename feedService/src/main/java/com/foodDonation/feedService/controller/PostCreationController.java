package com.foodDonation.feedService.controller;

import com.foodDonation.feedService.data.PostData;
import com.foodDonation.feedService.service.FeedService;


import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;

import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/v1/post")
@AllArgsConstructor
public class PostCreationController {


    private FeedService feedService;

    @GetMapping("/feeds")
    public Page<PostData> getProducts(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "5") int size) {
        return feedService.getFeed(page, size);
    }

}
