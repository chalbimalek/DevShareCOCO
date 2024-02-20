package com.coco.pibackend.Repo;

import com.coco.pibackend.Entity.Events;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EventsRepository extends JpaRepository<Events, Integer> {
}