package com.coco.pibackend.Entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@DiscriminatorValue(value = "Student")
public class Student extends User  {

    private String specialite;
    private String classe;
    private boolean fumer;
    private String adresse_domicile;
}
