package com.coco.pibackend.Entity;

import com.coco.pibackend.Enum.Category;
import com.coco.pibackend.ServiceIMp.NotificationServiceImpl;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Carpooling {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idCarpolling;
    private String gouvernorat;
    private int numero;
    @JsonProperty("DateSorite")
    @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
    private LocalDateTime DateSortie;
    private String pointArrivee;
    private String pointSorite;
    private String name;
    private float departLatitude;
    private float  departLongitude;
    private float destinationLatitude;
    private float  destinationLongitude;
    private String title;

    private Integer nbrPlaceDisponible ;
    private float price;
    private String description;

    //annonceur
    @ManyToOne( cascade = CascadeType.ALL)
    @JsonIgnore
    @JoinColumn(name = "user_id") // Nom de la colonne de clé étrangère dans la table carpooling
    private User user;
    @JsonIgnore
    // Utilisateur qui annonce le covoiturage
    @OneToMany(mappedBy = "carpooling")
    private Set<Notification> notifications;
    @ManyToMany( cascade = CascadeType.REMOVE)
    @JsonIgnore
    private List<User> chercheurs;
    @Enumerated(EnumType.STRING)
    private Category category;
    @ManyToMany(cascade = CascadeType.REMOVE)
    private Set<User> utilisateursAcceptes = new HashSet<>();
    @ManyToMany(fetch = FetchType.EAGER ,cascade =  CascadeType.ALL)
    @JoinTable(name = "carpooling_image" ,joinColumns = {@JoinColumn(name = "carpooling_idd")}
            ,inverseJoinColumns = {@JoinColumn (name = "image_idd")})
    private Set<ImageModel> imageModels;
    private Boolean status;

}
