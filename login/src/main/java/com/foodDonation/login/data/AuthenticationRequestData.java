package com.foodDonation.login.data;

import jakarta.persistence.*;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data //getters and setters
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "userData")
public class AuthenticationRequestData {

    @Id
    @Column(updatable = false,insertable = false)
    private Integer id;     //initial planned to use phone no as user id but the phone no is too big for Integer and online recommended solution is to use string for phone no
    //private String uniqueUserName;


    private String phoneNo;


//    private String firstName;
//
//    private String lastName;
//
//
//    private String email;

    private String password;

//
//    private String districtName;
//    private String fullAddress;
//
//    @Enumerated(EnumType.STRING)
//    private Role role;

}

