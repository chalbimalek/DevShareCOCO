package com.coco.pibackend.ServiceIMp;


import com.coco.pibackend.Entity.Cart;
import com.coco.pibackend.Entity.Product;
import com.coco.pibackend.Entity.User;
import com.coco.pibackend.Repo.CartDao;
import com.coco.pibackend.Repo.ProductRepo;
import com.coco.pibackend.Repo.UserRepo;
import com.coco.pibackend.Security.JWT.AuthTokenFilter;
import com.coco.pibackend.Service.CartServiceInterface;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CartService implements CartServiceInterface {


    private final CartDao cartDao;


    private final ProductRepo productDao;


    private final UserRepo userDao;
@Override
    public void deleteCartItem(Integer cartId) {
        cartDao.deleteById(cartId);
    }
    @Override
    public Cart addToCart(Integer productId) {
        Product product = productDao.findById(productId).orElse(null);
        if (product == null) {
            System.out.println("Le produit avec l'ID " + productId + " n'a pas été trouvé.");
        }
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();
        User user = null;
        System.out.println(username +"useernamme ");
        if (username!=null) {
            user = userDao.findByUsername(username).orElse(null);
            if (user == null) {
                System.out.println("L'utilisateur avec le nom d'utilisateur " + username + " n'a pas été trouvé.");
            }
        } else {
            System.out.println("Le nom d'utilisateur est nul ou vide.");
        }

            // Logique pour ajouter le produit au panier
            List<Cart> cartList = cartDao.findByUser(user);
            List<Cart> filteredList = cartList.stream().filter(x -> x.getProduct().getIdProduct() == productId).collect(Collectors.toList());


            if(filteredList.size() > 0) {
                return null;
            }


            if(product != null && user != null) {
                Cart cart = new Cart(product, user);
                return cartDao.save(cart);
            }

            return null;
        }
     /*   Product product = productDao.findById(productId).get();

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();
        User user = null;

        if (username != null) {
            user = userDao.findByUsername(username).get();

        }

        List<Cart> cartList = cartDao.findByUser(user);
        //List<Cart> filteredList = cartList.stream().filter(x -> x.getProduct().getIdProduct() == productId).collect(Collectors.toList());

       /* if (filteredList.size() > 0) {
            return null;
        }*/

/*
        if (product != null && user != null) {
            Cart cart = new Cart(product, user);
            return cartDao.save(cart);
        }
        return null;
   */




    @Override
    public List<Cart> getCartDetails(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();
        User user = userDao.findByUsername(username).get();
        return cartDao.findByUser(user);

    }
public User getuserfromusername(){
    User user = null;
    Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
    String username = authentication.getName();
    user = userDao.findByUsername(username).orElse(null);

    System.out.println("Current User: " + user);

    return user;
}


}