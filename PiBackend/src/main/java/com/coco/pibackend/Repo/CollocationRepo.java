package com.coco.pibackend.Repo;

import com.coco.pibackend.Entity.Collocation;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CollocationRepo extends JpaRepository<Collocation,Integer> {
    List<Collocation> getCollocationsByLieux(String lieux);
}
