package com.coco.pibackend.Repo;

import com.coco.pibackend.Entity.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface PostRepository extends JpaRepository<Post, Long> {
    @Query(value = "SELECT * FROM Post p WHERE LOWER(p.topic) LIKE LOWER(CONCAT('%', :word, '%'))", nativeQuery = true)
    List<Post> searchByTitle(@Param("word") String word);
}