package com.coco.pibackend.Repo;

import com.coco.pibackend.Entity.Event;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface EventsRepository extends JpaRepository<Event, Integer> {

    List<Event> findByTopic(String topic);


}