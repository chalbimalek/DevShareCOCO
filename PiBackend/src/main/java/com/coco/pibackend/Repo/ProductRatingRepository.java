package com.coco.pibackend.Repo;

import com.coco.pibackend.Entity.ProductRating;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ProductRatingRepository extends JpaRepository<ProductRating, Long> {


@Query("select r.product.category, SUM (r.rating) as rating from ProductRating r group by r.product.category order by rating")
    List<String> statistiqueRating();
}
