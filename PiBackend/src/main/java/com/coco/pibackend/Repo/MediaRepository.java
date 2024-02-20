package com.coco.pibackend.Repo;

import com.coco.pibackend.Entity.Media;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MediaRepository extends JpaRepository<Media, Integer> {
}