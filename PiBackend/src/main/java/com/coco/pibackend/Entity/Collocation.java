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
public class Collocation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idCollocation;
    private String lieux;
    private int numero;
    private String distanceAfac;
    private String name;
    private String adresse;
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
    @JoinTable(name = "collocation_image" ,joinColumns = {@JoinColumn(name = "collocation_idd")}
            ,inverseJoinColumns = {@JoinColumn (name = "image_idd")})
    private Set<ImageModel> imageModels;

}


