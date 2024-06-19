package com.foodDonation.postCreation.data;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
@Scope("request")
@Data
@AllArgsConstructor
public class UserId {
    Integer uid;
}
