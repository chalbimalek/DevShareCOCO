package com.coco.pibackend.Entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.io.Serializable;
import java.util.Date;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Comment implements Serializable {
    @Id
   @Column
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Temporal(TemporalType.DATE)
    private Date date;
    @NotEmpty
    private String text;
    @NotBlank(message = "React is mandatory")
    private String react;
    @Lob
    private byte[] data;
    private String nameFile;
    @JsonIgnore
    @ManyToOne
    private User user;
    @ManyToOne
    private Post post;


}
