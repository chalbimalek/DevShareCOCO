package com.coco.pibackend.Repo;

import com.coco.pibackend.Entity.Blog;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BlogRepository extends JpaRepository<Blog, Integer> {
}