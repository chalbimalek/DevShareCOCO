package com.coco.pibackend.Service;

import com.coco.pibackend.Entity.Cart;

import java.util.List;

public interface CartServiceInterface {
    public void deleteCartItem(Integer cartId);
    public Cart addToCart(Integer productId);
    public List<Cart> getCartDetails();


}
