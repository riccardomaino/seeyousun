package com.tass.seeyousun.resortservice.model;


import com.tass.seeyousun.resortservice.enums.Benessere;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Collections;
import java.util.List;

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

    public static void main(String[] args) {
        List<Service> b = Collections.singletonList(new Service(1L,null,Benessere.valueOf("IDROMASSAGGIO")));
        List<Service> c = Collections.singletonList(new Service(1L,null,Benessere.valueOf("IDROMASSAGGIO")));
        System.out.println(b.containsAll(c));
    }
}
