package com.coco.pibackend.Repo;

import com.coco.pibackend.Entity.OrderDetail;
import com.coco.pibackend.Entity.User;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface OrderDetailDao extends CrudRepository<OrderDetail, Integer>{

    public List<OrderDetail> findByUser(User user);

    @Query("SELECT p.category, COUNT(p.category) AS categoryCount " +
            "FROM OrderDetail o " +
            "JOIN o.product p " + // Joindre la table Product à travers la liste de produits dans OrderDetail
            "GROUP BY p.category " +
            "ORDER BY categoryCount DESC")
    List<String> findMostPurchasedCategory();



}