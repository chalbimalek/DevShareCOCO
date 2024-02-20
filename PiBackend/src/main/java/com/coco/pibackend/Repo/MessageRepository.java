package com.coco.pibackend.Repo;

import com.coco.pibackend.Entity.Message;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MessageRepository extends JpaRepository<Message, Integer> {
}