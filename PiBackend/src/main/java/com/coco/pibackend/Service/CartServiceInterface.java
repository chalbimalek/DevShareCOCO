package com.coco.pibackend.Service;

import com.coco.pibackend.Entity.Cart;
import com.coco.pibackend.Entity.Product;
import com.coco.pibackend.Entity.User;

import java.util.List;

public interface CartServiceInterface {
    public void deleteCartItem(Integer cartId);
    public Cart addToCart(Integer productId);
    public List<Product> getCartDetails();


}
