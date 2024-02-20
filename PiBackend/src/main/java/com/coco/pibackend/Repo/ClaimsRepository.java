package com.coco.pibackend.Repo;

import com.coco.pibackend.Entity.Claims;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClaimsRepository extends JpaRepository<Claims, Integer> {
}