package com.coco.pibackend.Repo;

import com.coco.pibackend.Entity.AnnonceCollocation;
import com.coco.pibackend.Entity.RendezVous;
import com.coco.pibackend.Entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RendezVousRepo extends JpaRepository<RendezVous, Integer> {

    List<RendezVous>findByAnnonceCollocation(AnnonceCollocation a);
    @Query("SELECT r FROM RendezVous r WHERE (r.owner = :user OR r.client = :user) AND r.status = true")
    List<RendezVous> findActiveRendezVousByUser(User user);

    List<RendezVous>findByOwnerAndStatus(User owner,boolean status);
}
