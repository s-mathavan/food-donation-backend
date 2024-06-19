package com.foodDonation.login.data;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
//import lombok.NoArgsConstructor;

@Entity
@Table(name = "userData")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ClaimsForJwt {

    @Id
    Integer id;

    @Enumerated(EnumType.STRING)
    Role role;

    String firstName;

}
