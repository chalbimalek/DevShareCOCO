package com.coco.pibackend.Repo;

import com.coco.pibackend.Entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentRepository extends JpaRepository<Student, Integer> {
}