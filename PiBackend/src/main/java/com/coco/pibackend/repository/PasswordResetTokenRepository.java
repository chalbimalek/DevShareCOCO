package com.coco.pibackend.repository;

import com.coco.pibackend.Entity.PasswordResetToken;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PasswordResetTokenRepository extends JpaRepository<PasswordResetToken , Long> {
    PasswordResetToken findByToken(String token);
}
