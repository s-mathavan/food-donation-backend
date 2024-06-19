package com.foodDonation.postCreation.controller;

import com.foodDonation.postCreation.data.UserId;
import com.foodDonation.postCreation.service.UploadService;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.servlet.http.HttpServletRequest;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequestMapping("/v1/post")
@AllArgsConstructor
public class PostCreationController {


    private UploadService uploadService;

    @PostMapping("/create")
    public ResponseEntity<String> createPost(
            @RequestParam("file") MultipartFile multipartFile,
            //@RequestParam("userId") String userId,
            @RequestParam("description") String message,
            HttpServletRequest httpServletRequest
            ) throws IOException {
        Integer userId = (Integer) httpServletRequest.getAttribute("userId");
        return ResponseEntity.ok().body(uploadService.uploadImage(multipartFile,userId,message));
    }


}
