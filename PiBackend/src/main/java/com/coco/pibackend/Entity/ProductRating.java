package com.coco.pibackend.Entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
public class ProductRating {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    private User user;
    @ManyToOne
    private Product product;
    private int rating;
    private String comment;

    public ProductRating(Product product, User user) {
        super();
        this.product=product;
        this.user=user;
        }

    // Getters and setters
}
