package com.foodDonation.feedService.service;

import com.foodDonation.feedService.data.PostData;
import com.foodDonation.feedService.repository.PostDataRepository;
import com.foodDonation.feedService.utils.ImageUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class FeedService {

    private final PostDataRepository repository;

    public Page<PostData> getFeed(Integer page, Integer size){
        Pageable pageable = PageRequest.of(page, size);
        Page<PostData> pageResult = repository.findAll(pageable);

        pageResult.getContent().forEach(postData -> {
            postData.setImageData(ImageUtils.decompressImage(postData.getImageData()));
        });

        return pageResult;
    }
}
