package com.coco.pibackend.Repo;

import com.coco.pibackend.Entity.OrderDetail;
import com.coco.pibackend.Entity.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface OrderDetailDao extends CrudRepository<OrderDetail, Integer>{

    public List<OrderDetail> findByUser(User user);

}