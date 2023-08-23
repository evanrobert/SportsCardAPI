package com.evanrobert.Json.API.Model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.sun.istack.NotNull;
import lombok.*;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotEmpty;

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

    @JsonIgnore
    public boolean isEmpty() {
        return player == null || player.isEmpty();
    }

}