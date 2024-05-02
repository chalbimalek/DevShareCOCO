package com.coco.pibackend.Entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.util.List;
import java.util.Set;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class User implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id_user;
    private String username;
    private String email;
    private String password;
    @OneToMany(mappedBy = "user")
    private Set<AnnonceCarpoling> annonceCarpolingSet;
    @OneToMany(mappedBy = "user")
    private Set<MarketPlace> marketPlaces;
    @OneToMany(mappedBy = "user")
    @JsonIgnore
    private Set<AnnonceCollocation>annonceCollocations;
    @OneToMany(mappedBy = "user")
    private Set<Blog>blogs;

    @OneToMany(mappedBy = "user")
    private Set<Events> events;
    @OneToMany(mappedBy = "user")
    @JsonIgnore
    private Set<Claims> claims;
    @OneToMany(mappedBy = "owner")
    @JsonIgnore
    private List<RendezVous> myrendez;
    @OneToMany(mappedBy = "client")
    @JsonIgnore
    private List<RendezVous> hisrendez;


}
