package com.coco.pibackend.Entity;

import com.coco.pibackend.Enum.Type_Event;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;
import java.util.Set;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Events implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id_events;
    private String title;
    private String description;
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    private LocalDateTime startDate;
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    private LocalDateTime endDate;
    private String location_event;
    @Enumerated(EnumType.STRING)
    private Type_Event type_Event;
    private int price;
    private LocalDate created;
    @ManyToMany(fetch = FetchType.EAGER ,cascade =  CascadeType.ALL)
    @JoinTable(name = "event_image" ,joinColumns = {@JoinColumn(name = "event_idd")}
            ,inverseJoinColumns = {@JoinColumn (name = "image_iddd")})
    private Set<ImageModel> imageModels;
    @ManyToOne
    User user;





}
