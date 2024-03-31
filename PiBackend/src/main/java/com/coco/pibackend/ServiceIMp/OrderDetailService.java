package com.coco.pibackend.ServiceIMp;


import com.coco.pibackend.Entity.*;
import com.coco.pibackend.Repo.CartDao;
import com.coco.pibackend.Repo.OrderDetailDao;
import com.coco.pibackend.Repo.UserRepo;
import com.coco.pibackend.Security.JWT.AuthTokenFilter;
import com.coco.pibackend.dao.ProductDao;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Service
public class OrderDetailService {

    private static final String ORDER_PLACED = "Placed";

    @Autowired
    private OrderDetailDao orderDetailDao;

    @Autowired
    private ProductDao productDao;

    @Autowired
    private UserRepo userDao;

    @Autowired
    private CartDao cartDao;

    public List<OrderDetail> getAllOrderDetails(){
        List<OrderDetail> orderDetails = new ArrayList<>();
        orderDetailDao.findAll().forEach(e -> orderDetails.add(e));

        return orderDetails;
    }

    public List<OrderDetail> getOrderDetails() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();        User user = userDao.findByUsername(username).get();

        return orderDetailDao.findByUser(user);
    }

    public void placeOrder(OrderInput orderInput, boolean isSingleProductCheckout) {
        List<OrderProductQuantity> productQuantityList = orderInput.getOrderProductQuantityList();

        for(OrderProductQuantity o: productQuantityList) {
            Product product = productDao.findById(o.getProductId()).get();
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            String username = authentication.getName();
            User user= userDao.findByUsername(username).get();

            OrderDetail orderDetail = new OrderDetail(
                    orderInput.getFullName(),
                    orderInput.getFullAddress(),
                    orderInput.getContactNumber(),
                    orderInput.getAlternateContactNumber(),
                    ORDER_PLACED,
                    product.getPrice()*o.getQuantity(),
                    Collections.singletonList(product),
                    Collections.singletonList(user));

            if(!isSingleProductCheckout) {
                List<Cart> carts= cartDao.findByUser(user);
                carts.stream().forEach(x -> cartDao.deleteById(x.getCartId()));

            }
            orderDetailDao.save(orderDetail);
        }
    }
@Transactional
    public void markOrderAsDelivered(int orderId){
        OrderDetail orderDetail=orderDetailDao.findById(orderId).get();
        if (orderDetail !=null){
            orderDetail.setOrderStatus("Delivered");
            orderDetailDao.save(orderDetail);
        }
    }
    public List<String> getMostPurchasedCategory() {
        return orderDetailDao.findMostPurchasedCategory();
    }


}