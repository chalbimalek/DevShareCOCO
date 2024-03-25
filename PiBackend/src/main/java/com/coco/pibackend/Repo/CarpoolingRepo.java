package com.coco.pibackend.Repo;

import com.coco.pibackend.Entity.Carpooling;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CarpoolingRepo extends JpaRepository<Carpooling,Integer> {
    List<Carpooling> getCarpoolingByGouvernorat(String Gouvernorat);
}
