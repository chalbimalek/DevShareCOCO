package com.coco.pibackend.Repo;

import com.coco.pibackend.Entity.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentRepository extends JpaRepository<Comment, Long> {
}