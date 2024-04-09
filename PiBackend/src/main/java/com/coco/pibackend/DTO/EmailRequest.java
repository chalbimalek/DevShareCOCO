package com.coco.pibackend.DTO;

import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter

public class EmailRequest {

    private String to;
    private String subject;
    private String text;

    // Getters and setters
}

