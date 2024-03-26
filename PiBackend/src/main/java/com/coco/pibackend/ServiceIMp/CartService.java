package com.coco.pibackend.ServiceIMp;


import com.coco.pibackend.Entity.Cart;
import com.coco.pibackend.Entity.Product;
import com.coco.pibackend.Entity.User;
import com.coco.pibackend.Repo.CartDao;
import com.coco.pibackend.Repo.ProductRepo;
import com.coco.pibackend.Repo.UserRepo;
import com.coco.pibackend.Security.JWT.AuthTokenFilter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CartService {


    private final CartDao cartDao;


    private final ProductRepo productDao;


    private final UserRepo userDao;

    public void deleteCartItem(Integer cartId) {
        cartDao.deleteById(cartId);
    }

    public Cart addToCart(Integer productId) {
     /*   Product product = productDao.findById(productId).orElse(null);
        if (product == null) {
            System.out.println("Le produit avec l'ID " + productId + " n'a pas été trouvé.");
        }

        String username = AuthTokenFilter.CURRENT_USER;
        User user = null;
        System.out.println(username +"useernamme ");
        if (StringUtils.hasText(username)) {
            user = userDao.findByUsername(username).orElse(null);
            if (user == null) {
                System.out.println("L'utilisateur avec le nom d'utilisateur " + username + " n'a pas été trouvé.");
            }
        } else {
            System.out.println("Le nom d'utilisateur est nul ou vide.");
        }

        if (product != null && user != null) {
            // Logique pour ajouter le produit au panier
            List<Cart> cartList = cartDao.findByUser(user);
            List<Cart> filteredList = cartList.stream().filter(x -> x.getProduct().getIdProduct() == productId).collect(Collectors.toList());

            if (filteredList.isEmpty()) {
                Cart cart = new Cart(product, user);
                return cartDao.save(cart);
            } else {
                return null; // Ou gérer le cas où le produit est déjà dans le panier
            }
        } else {
            System.out.println("Impossible d'ajouter le produit au panier car le produit ou l'utilisateur est null.");

            // Ajoutez des messages de débogage pour vérifier la récupération de l'utilisateur
            if (product == null) {
                System.out.println("Le produit est null.");
            }
            if (user == null) {
                System.out.println("L'utilisateur est null.");
            }

            return null;
        }}*/
        Product product = productDao.findById(productId).get();

        String username = AuthTokenFilter.CURRENT_USER;

        User user = null;

        if (username != null) {
            user = userDao.findByUsername(username).get();

        }

        List<Cart> cartList = cartDao.findByUser(user);
        List<Cart> filteredList = cartList.stream().filter(x -> x.getProduct().getIdProduct() == productId).collect(Collectors.toList());

        if (filteredList.size() > 0) {
            return null;
        }


        if (product != null && user != null) {
            Cart cart = new Cart(product, user);
            return cartDao.save(cart);
        }
        return null;
    }





    public List<Cart> getCartDetails(){
        String username = AuthTokenFilter.CURRENT_USER;
        User user = userDao.findByUsername(username).get();
        return cartDao.findByUser(user);

    }
public User getuserfromusername(){
    User user = null;
    String username= AuthTokenFilter.CURRENT_USER;
    user = userDao.findById(username).orElse(null);
return user;
}


}