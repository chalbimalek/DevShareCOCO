package com.coco.pibackend.Repo;

import com.coco.pibackend.Entity.ProductComment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductCommentRepository extends JpaRepository<ProductComment, Long> {
    List<ProductComment> getProductCommentByProduct_IdProduct(int id);
}
