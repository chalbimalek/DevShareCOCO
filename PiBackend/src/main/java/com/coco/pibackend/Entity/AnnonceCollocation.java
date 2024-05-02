package com.coco.pibackend.Entity;

import com.coco.pibackend.Enum.Type_annon_Collocation;
import com.coco.pibackend.Enum.Type_logement;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.Set;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AnnonceCollocation  implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id_anno_colo;
    private boolean status;
    private String Addresse;
    private String ville;
    private String pays;
    private Date date_disponiblite;
    private int nbrChambre;
    private String meuble;
    private String photos;
    private float cautionnement;
    private String sexe;
    @Enumerated(EnumType.STRING)
    private Type_logement typeLogement;
    private String description;
    private float montantContrubition;
    @Enumerated(EnumType.STRING)
    private Type_annon_Collocation typeAnnonCollocation;
    private int nbrPersonne;
    @ManyToMany(mappedBy = "annonceCollocations")
    private Set<Avis> avis;
    @ManyToOne
    private User user;
    @OneToMany(mappedBy = "annonceCollocation",cascade = CascadeType.ALL)
    @JsonIgnore
    private List<RendezVous>rendezVous;







}
