//package com.foodDonation.postCreation.config;
//
//import com.foodDonation.postCreation.data.UserId;
//import lombok.AllArgsConstructor;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.context.annotation.Scope;
//
//@Configuration
//@AllArgsConstructor
//public class AppConfig {
//
//    Integer userId;
//
//    @Bean
//    @Scope("prototype")
//    public UserId setUserId(){          //error can be caused by this method
//        UserId data =new UserId(userId);
//        return data;
//    }
//}
