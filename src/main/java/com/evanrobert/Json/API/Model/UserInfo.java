package com.evanrobert.Json.API.Model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;


@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserInfo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String email;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "user_Login_id")
    private UserDetailService userDetailService;

    @OneToMany(mappedBy = "userinfo")
    private List<Cards> cards;
}





