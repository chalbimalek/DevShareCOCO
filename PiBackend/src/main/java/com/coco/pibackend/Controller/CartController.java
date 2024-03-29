package com.coco.pibackend.Controller;


import com.coco.pibackend.Entity.Cart;
import com.coco.pibackend.Entity.User;
import com.coco.pibackend.Repo.UserRepo;
import com.coco.pibackend.Security.JWT.AuthTokenFilter;
import com.coco.pibackend.Service.CartServiceInterface;
import com.coco.pibackend.ServiceIMp.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class CartController {

    @Autowired
    private CartServiceInterface cartService;
    @Autowired
    private UserRepo userDao;
    @PreAuthorize("hasRole('ROLE_MEMBRE')")

   // @PreAuthorize("hasRole('User')")
    @GetMapping({"/addToCart/{productId}"})
    public Cart addTocart(@PathVariable(name="productId") Integer productId) {
        return cartService.addToCart(productId);

    }
    @PreAuthorize("hasRole('ROLE_MEMBRE')")


    @DeleteMapping({"/deleteCartItem/{cartId}"})
    public void deleteCartItem(@PathVariable(name= "cartId") Integer cartId) {
        cartService.deleteCartItem(cartId);
    }



    @PreAuthorize("hasRole('ROLE_MEMBRE')")
    @GetMapping({"/getCartDetails"})
    public List<Cart> getCartDetails() {
        return cartService.getCartDetails();

    }
    /*@GetMapping("/getUserFromUsername")
    public User getUserFromUsername() {
        return cartService.getuserfromusername();
    }
*/
}