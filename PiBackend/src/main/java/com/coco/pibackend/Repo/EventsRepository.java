package com.coco.pibackend.Repo;

import com.coco.pibackend.Entity.Event;
import com.coco.pibackend.Entity.Events;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface EventsRepository extends JpaRepository<Events, Integer> {



}