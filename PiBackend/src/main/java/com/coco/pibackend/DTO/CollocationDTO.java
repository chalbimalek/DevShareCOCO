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
public class CollocationDTO {
    private Integer idCollocation;
    private String Lieux;
    private int numero;
    private String distanceAfac;
    private String name;
    @DateTimeFormat
    private LocalDateTime DateSortie;
    private Integer nbrPlaceDisponible ;
    private float price;
    private String description;
    private Set<ImageModel> imageModels;
    private String username;
}
