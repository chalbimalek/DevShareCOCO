package com.coco.pibackend.Entity;

import com.coco.pibackend.Enum.StatusClaims;
import com.coco.pibackend.Enum.TypeClaim;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Claims implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idClaims")
    private Integer idClaims;
    private String title;
    private String description;
    @Enumerated(EnumType.STRING)
    private TypeClaim typeClaim;
    @Enumerated(EnumType.STRING)
    private StatusClaims statusClaims;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDateTime CreatedAt;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDateTime   ConsultAt;
    @ManyToOne(fetch = FetchType.LAZY)
    private User user;


}
