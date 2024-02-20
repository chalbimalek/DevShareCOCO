package com.coco.pibackend.Repo;

import com.coco.pibackend.Entity.MarketPlace;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductREpo extends JpaRepository<MarketPlace,Integer> {
}
