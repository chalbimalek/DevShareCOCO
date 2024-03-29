package com.coco.pibackend.Repo;


import com.coco.pibackend.Entity.Cart;
import com.coco.pibackend.Entity.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CartDao extends CrudRepository<Cart, Integer> {

    public List<Cart> findByUser(User user);
    public Cart findCartByUser(User user);
}