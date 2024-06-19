package com.foodDonation.postCreation.data;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Data;

@Entity
@Table(name = "postData")
@Data
@Builder
public class PostData {


    @Id
    @GeneratedValue
    private Integer postId;

    private Integer id;         //id of the user

    private String description;

    private String type;

    @Lob //used to store binary data
    @Column(length = 1000)
    private byte[] imageData;
}
