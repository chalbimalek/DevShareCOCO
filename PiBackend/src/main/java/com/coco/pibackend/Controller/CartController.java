package com.coco.pibackend.Controller;


import com.coco.pibackend.Entity.Cart;
import com.coco.pibackend.ServiceIMp.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class CartController {

    @Autowired
    private CartService cartService;

   // @PreAuthorize("hasRole('User')")
    @GetMapping({"/addToCart/{productId}"})
    public Cart addTocart(@PathVariable(name="productId") Integer productId) {
        return cartService.addToCart(productId);

    }

    @DeleteMapping({"/deleteCartItem/{cartId}"})
    public void deleteCartItem(@PathVariable(name= "cartId") Integer cartId) {
        cartService.deleteCartItem(cartId);
    }



   // @PreAuthorize("hasRole('User')")
    @GetMapping({"/getCartDetails"})
    public List<Cart> getCartDetails() {
        return cartService.getCartDetails();

    }


}