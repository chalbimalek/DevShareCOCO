package com.coco.pibackend.Controller;

import com.coco.pibackend.Entity.ProductRating;
import com.coco.pibackend.ServiceIMp.ProductRatingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/product-ratings")
public class ProductRatingController {

    @Autowired
    private ProductRatingService productRatingService;

    @PostMapping("{prdouctid}")
    public ProductRating saveProductRating(@PathVariable(name="prdouctid") int prdouctid,@RequestBody ProductRating productRating ) {
        return productRatingService.saveProductRating(prdouctid,productRating);
    }

    @GetMapping
    public List<ProductRating> getAllProductRatings() {
        return productRatingService.getAllProductRatings();
    }

    @GetMapping("/{id}")
    public ProductRating getProductRatingById(@PathVariable Long id) {
        return productRatingService.getProductRatingById(id);
    }

    @GetMapping("/product/{productId}")
    public List<ProductRating> getProductRatingByProductId(@PathVariable int productId) {
        return productRatingService.getProductRatingByproductId(productId);
    }

    // Vous pouvez ajouter d'autres méthodes du contrôleur selon vos besoins
}