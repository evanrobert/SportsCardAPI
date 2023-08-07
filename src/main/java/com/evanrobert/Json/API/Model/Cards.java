package com.evanrobert.Json.API.Model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Getter
@Setter
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Cards {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String player;
    private String numbered;
    private double price;
    private String yearOfCard;
    private String rookie;


    @JsonIgnore
    public boolean isEmpty() {
        return player == null || player.isEmpty();
    }

}