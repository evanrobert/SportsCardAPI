package com.evanrobert.Json.API.Model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.sun.istack.NotNull;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
public class Cards {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotEmpty

    private String sport;

    @NotEmpty
    private String player;

    private boolean numbered;

    @NotNull
    private Double price;

    @NotEmpty
    private String yearOfCard;

    private boolean rookie;

    @NotEmpty
    private String brand;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private UserInfo userinfo;
    @ManyToOne
    @JoinColumn(name = "user_login_id")
    private UserDetailService userDetailService;



    @JsonIgnore
    public boolean isEmpty() {
        return player == null || player.isEmpty();
    }

}