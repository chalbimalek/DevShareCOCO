package com.coco.pibackend.Controller;


import com.coco.pibackend.Entity.Cart;
import com.coco.pibackend.Entity.Product;
import com.coco.pibackend.Entity.User;
import com.coco.pibackend.Repo.UserRepo;
import com.coco.pibackend.Security.JWT.AuthTokenFilter;
import com.coco.pibackend.Service.CartServiceInterface;
import com.coco.pibackend.ServiceIMp.CartService;
import com.coco.pibackend.ServiceIMp.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class CartController {

    @Autowired
    private CartServiceInterface cartService;
    @Autowired
    private UserRepo userDao;
    @Autowired
    private ProductService productService;
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
    public List<Product> getCartDetails() {
        return cartService.getCartDetails();

    }
    @PreAuthorize("hasRole('ROLE_MEMBRE')")
    @GetMapping("/api/removeProductFromCart/{productId}")
    public void removeProductFromCart(@PathVariable(name= "productId") Integer productId) {
        productService.removeProductFromCart(productId);
    }

    /*@GetMapping("/getUserFromUsername")
    public User getUserFromUsername() {
        return cartService.getuserfromusername();
    }
*/
}