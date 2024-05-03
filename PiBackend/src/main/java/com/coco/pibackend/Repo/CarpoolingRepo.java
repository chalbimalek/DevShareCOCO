package com.coco.pibackend.Repo;

import com.coco.pibackend.Entity.Carpooling;
import com.coco.pibackend.Entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface CarpoolingRepo extends JpaRepository<Carpooling,Integer> {
    List<Carpooling> getCarpoolingByGouvernorat(String Gouvernorat);

    List<Carpooling> findByUser(User user);

    @Query("SELECT c FROM Carpooling c WHERE c.status = false AND c.DateSortie > CURRENT_DATE()")
    List<Carpooling> getCarpooling();
}
