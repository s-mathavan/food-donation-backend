package com.foodDonation.signup.data;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;



//@Builder
@Data //getters and setters
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "userData")
public class UserData {

    @Id
    @GeneratedValue
    private Integer id;     //initial planned to use phone no as user id but the phone no is too big for Integer and online recommended solution is to use string for phone no
    //private String uniqueUserName;

    @NotNull(message = "Phone number is required")
    @NotBlank(message = "phone number is required")
    private String phoneNo;

    @NotNull(message = "firstname is mandatory")
    private String firstName;

    private String lastName;

    @NotNull(message = "email is required")
    @Email(message = "email should be valid")
    private String email;

    @NotNull(message = "enter your password")
    @Size(min = 6,message = "Password should have atleast 6 characters")
    private String password;

    @NotNull(message = "enter your district")
    private String districtName;
    private String fullAddress;

    @Enumerated(EnumType.STRING)
    private Role role;

}
