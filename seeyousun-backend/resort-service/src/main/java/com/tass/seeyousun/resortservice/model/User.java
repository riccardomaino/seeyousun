package com.tass.seeyousun.resortservice.model;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "users")
public class User {

    @Id
    @Column(name = "username", length = 100)
    private String username; // contains user email from firebase

    @Column(name = "user_id", length = 2000)
    @Lob
    private String userId; // contains idtoken from firebase

}
