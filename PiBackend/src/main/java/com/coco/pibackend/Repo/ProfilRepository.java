package com.coco.pibackend.Repo;

import com.coco.pibackend.Entity.Profil;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProfilRepository extends JpaRepository<Profil, String> {
}