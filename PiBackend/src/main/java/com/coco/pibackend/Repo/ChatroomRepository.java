package com.coco.pibackend.Repo;

import com.coco.pibackend.Entity.Chatroom;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ChatroomRepository extends JpaRepository<Chatroom, Integer> {
}