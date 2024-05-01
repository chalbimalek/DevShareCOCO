package com.coco.pibackend.Entity;

import com.coco.pibackend.repository.PasswordResetTokenRepository;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;


@Getter
@Setter
@Data
@Entity
@Table(name = "password_reset_token")
public class PasswordResetToken {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String token;

    @OneToOne(targetEntity = User.class, fetch = FetchType.EAGER)
    @JoinColumn(nullable = false, name = "user_id")
    private User user;

    @Column(nullable = false)
    private LocalDateTime expiryDate;

    public PasswordResetToken() {
        // Default constructor required by JPA
    }

    public PasswordResetToken(String token, User user) {
        this.token = token;
        this.user = user;
        this.expiryDate = calculateExpiryDate();
    }

    private LocalDateTime calculateExpiryDate() {
        // Customize the expiry date logic as per your requirements
        return LocalDateTime.now().plusHours(24); // Expiry after 24 hours
    }

    // Méthode pour vérifier si le token est expiré
    public boolean isExpired() {
        return LocalDateTime.now().isAfter(expiryDate);
    }

    // Méthode pour trouver un token par son valeur
    public static PasswordResetToken findByToken(String token, PasswordResetTokenRepository tokenRepository) {
        return tokenRepository.findByToken(token);

    }
}
