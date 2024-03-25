package com.coco.pibackend.Controller;

import com.coco.pibackend.Entity.OrderDetail;
import com.coco.pibackend.Entity.OrderInput;
import com.coco.pibackend.ServiceIMp.OrderDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class OrderDetailController {

    @Autowired
    private OrderDetailService orderDetailService;



   // @PreAuthorize("hasRole('User')")
    @PostMapping({"/placeOrder/{isSingleProductCheckout}"})
    public void placeOrder(@PathVariable(name= "isSingleProductCheckout") boolean isSingleProductCheckout, @RequestBody OrderInput orderInput) {
        orderDetailService.placeOrder(orderInput, isSingleProductCheckout);

    }

   // @PreAuthorize("hasRole('User')")
    @GetMapping({"/getOrderDetails"})
    public List<OrderDetail> getOrderDetails() {
        return orderDetailService.getOrderDetails();
    }

  //  @PreAuthorize("hasRole('Admin')")
    @GetMapping({"/getAllOrderDetails"})
    public List<OrderDetail> getAllOrderDetails() {
        return orderDetailService.getAllOrderDetails();
    }

    @GetMapping({"/markOrderAsDelivered/{orderId}"})
    public void markOrderAsDelivered(@PathVariable(name= "orderId")int orderId ){
        orderDetailService.markOrderAsDelivered(orderId);

    }

}
