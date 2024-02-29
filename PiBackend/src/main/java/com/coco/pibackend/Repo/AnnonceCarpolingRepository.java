package com.coco.pibackend.Repo;

import com.coco.pibackend.Entity.AnnonceCarpooling;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AnnonceCarpolingRepository extends JpaRepository<AnnonceCarpooling, Integer> {
    /*AnnonceCarpooling getAnnonceCarpoolingById_anno_cov(int id_anno_cov);

    AnnonceCarpooling deleteAnnonceCarpoolingById_anno_cov(int id_anno_cov);

    AnnonceCarpooling findAnnonceCarpoolingByid_user(int id_user);*/
}