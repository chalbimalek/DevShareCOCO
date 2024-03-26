package com.coco.pibackend.Entity;

import com.coco.pibackend.Enum.Category;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;
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
    private String pointArrivee;
    private String pointSorite;
    private String name;
    @DateTimeFormat
    private LocalDateTime DateSortie;
    private Integer nbrPlaceDisponible ;
    private float price;
    private String description;
    @ManyToOne
    private User user;
    @Enumerated(EnumType.STRING)
    private Category category;
    @ManyToMany(fetch = FetchType.EAGER ,cascade =  CascadeType.ALL)
    @JoinTable(name = "carpooling_image" ,joinColumns = {@JoinColumn(name = "carpooling_idd")}
            ,inverseJoinColumns = {@JoinColumn (name = "image_idd")})
    private Set<ImageModel> imageModels;

}
