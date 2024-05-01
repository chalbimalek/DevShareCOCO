package com.coco.pibackend.Entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "notifications")
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Notification {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String message;
    private LocalDateTime timestamp;
    @ManyToOne(cascade = CascadeType.REMOVE)
    private User userEnvoyer;
    @ManyToOne(cascade = CascadeType.REMOVE)
    private User userDestiner;

    @ManyToOne(cascade = CascadeType.REMOVE)
    private Carpooling carpooling;

    private boolean acceptee;
    private boolean refusee;
    // Constructeur, getters et setters
    public Notification(String message) {
        this.message = message;
        this.timestamp = LocalDateTime.now();
    }
}