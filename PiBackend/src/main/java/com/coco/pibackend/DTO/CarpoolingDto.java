package com.coco.pibackend.DTO;

import com.coco.pibackend.Entity.ImageModel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CarpoolingDto {
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
    private Set<ImageModel> imageModels;
    private String username;
}
