package com.foodDonation.postCreation.service;

import com.foodDonation.postCreation.data.PostData;
import com.foodDonation.postCreation.repository.PostDataRepository;
import com.foodDonation.postCreation.utils.ImageUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Service
@RequiredArgsConstructor
public class UploadService {

    private final PostDataRepository repository;

    public String uploadImage(MultipartFile file,Integer userId,String description) throws IOException {

        PostData postData = repository.save(PostData.builder()
                                .id(userId)
                                .description(description)
                                //.name(file.getOriginalFilename())
                                .type(file.getContentType())
                                .imageData(ImageUtils.compressImage(file.getBytes())).build());
        if (postData != null) {
            return "file uploaded successfully : " + file.getOriginalFilename();
        }
        return null;
    }
}
