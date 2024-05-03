package com.coco.pibackend.Entity;

import com.coco.pibackend.Enum.Type_Event;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Event implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String location_event;
    @JsonProperty("startDate")
    @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
    private Date startDate;

    @JsonProperty("endDate")
    @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
    private Date endDate;

    private String topic;
    private int NumberParticipant;
    @Enumerated(EnumType.STRING)
    private Type_Event typeEvent;
    private Date created;
    /*@JsonIgnore
    @ManyToMany(mappedBy="event", cascade = CascadeType.ALL)
    private List<User> users;*/
    @JsonIgnore
    @OneToMany(cascade = CascadeType.ALL, mappedBy="event")
    private List<Post> posts;
    @OneToMany(cascade = CascadeType.ALL)
    private List<Media> poster;



}