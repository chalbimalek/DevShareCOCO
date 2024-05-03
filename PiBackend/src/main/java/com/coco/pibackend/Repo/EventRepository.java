package com.coco.pibackend.Repo;

import com.coco.pibackend.Entity.Event;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface EventRepository extends JpaRepository<Event, Long> {

    List<Event> findByTopic(String topic);


    Event findById(Integer eventId);
}
