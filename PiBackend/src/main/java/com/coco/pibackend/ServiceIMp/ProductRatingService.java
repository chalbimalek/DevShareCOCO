package com.coco.pibackend.ServiceIMp;

import com.coco.pibackend.Entity.Cart;
import com.coco.pibackend.Entity.Product;
import com.coco.pibackend.Entity.ProductRating;
import com.coco.pibackend.Entity.User;
import com.coco.pibackend.Repo.ProductRatingRepository;
import com.coco.pibackend.Repo.ProductRepo;
import com.coco.pibackend.Repo.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductRatingService {

    @Autowired
    private ProductRatingRepository productRatingRepository;
    @Autowired
    private UserRepo  userRepo;
    @Autowired
    private ProductRepo productRepo;

    public ProductRating saveProductRating(int prdouctid ,ProductRating productRatingRequest) {
        Product product=productRepo.findProductByIdProduct(prdouctid);
        // Récupérer l'utilisateur authentifié
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();
        User user = userRepo.findByUsername(username).orElse(null);
        if (user == null) {
            System.out.println("L'utilisateur avec le nom d'utilisateur " + username + " n'a pas été trouvé.");
            return null;
        }

        if (product == null) {
            System.out.println("Le produit avec l'ID " + product.getIdProduct() + " n'a pas été trouvé.");
            return null;
        }

        // Créer une nouvelle notation du produit avec les détails de la requête
        ProductRating productRating = new ProductRating();
        productRating.setProduct(product);
        productRating.setUser(user);
        productRating.setRating(productRatingRequest.getRating());
        productRating.setComment(productRatingRequest.getComment());

        // Enregistrer la notation du produit dans la base de données
        return productRatingRepository.save(productRating);
    }

    public List<ProductRating> getAllProductRatings() {
        return productRatingRepository.findAll();
    }

    public ProductRating getProductRatingById(Long id) {
        return productRatingRepository.findById(id).orElse(null);
    }

    public List<ProductRating> getProductRatingByproductId(int id) {
        return productRatingRepository.getProductRatingByProduct_IdProduct(id);
    }


    // Vous pouvez ajouter d'autres méthodes de service selon vos besoins
}
