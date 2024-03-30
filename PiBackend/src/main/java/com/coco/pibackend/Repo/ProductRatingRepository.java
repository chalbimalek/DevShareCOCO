package com.coco.pibackend.Repo;

import com.coco.pibackend.Entity.ProductRating;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductRatingRepository extends JpaRepository<ProductRating, Long> {

List<ProductRating> getProductRatingByProduct_IdProduct(int id);



}
