package com.coco.pibackend.Repo;

import com.coco.pibackend.Entity.Event;
import com.coco.pibackend.Entity.Rating;
import com.coco.pibackend.Enum.Type_Event;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RateRepository extends JpaRepository<Rating, Integer> {
    // Find a rating by event


    @Query("SELECT AVG(r.value) FROM Rating r WHERE r.event.id = ?1")
    Double getAverageRatingForEvent(Integer eventId);

    @Query("select r from Rating r where r.event.id=?1 ")
    List<Rating> findByEvent_Id(Integer eventId);




    Rating findByEvent(Event byId);
}