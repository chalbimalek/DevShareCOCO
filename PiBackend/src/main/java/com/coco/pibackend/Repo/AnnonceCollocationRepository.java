package com.coco.pibackend.Repo;

import com.coco.pibackend.Entity.AnnonceCollocation;
import com.coco.pibackend.Entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AnnonceCollocationRepository extends JpaRepository<AnnonceCollocation, Integer> {
    List<AnnonceCollocation> findByUser(User user);
}
