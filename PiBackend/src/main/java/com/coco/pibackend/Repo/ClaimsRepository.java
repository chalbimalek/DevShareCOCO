package com.coco.pibackend.Repo;

import com.coco.pibackend.Entity.Claims;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClaimsRepository extends JpaRepository<Claims, Integer> {
     public Claims findByIdClaims (Integer idClaims);


}