package com.coco.pibackend.ServiceIMp;


import com.coco.pibackend.Entity.Cart;
import com.coco.pibackend.Entity.Product;
import com.coco.pibackend.Entity.User;
import com.coco.pibackend.Repo.CartDao;
import com.coco.pibackend.Repo.MediaRepo;
import com.coco.pibackend.Repo.ProductRepo;
import com.coco.pibackend.Repo.UserRepo;
import com.coco.pibackend.Security.JWT.AuthTokenFilter;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ProductService {


    private final ProductRepo productRepo;

    private final RefGenerator refGenerator;
    private final UserRepo userRepo;
    private final CartDao cartDao;

    public Product addProduit(Product product) {
        product.setCreationDate(new Date());
        return productRepo.save(product);

    }
        /*String ref = refGenerator.generateRef();
        Product tmp = productRepo.findByReference(ref);
        while (tmp != null) {
            ref = refGenerator.generateRef();
            tmp = productRepo.findByReference(ref);
        }
        product.setReference(ref);


        // Vérification que le shop existe dans la base de données
        // Génération du code-barres
        String barcodeText = "reference: "+ product.getReference()+"\nname: "+
                product.getName();


        int width = 500;
        int height = 250;
        Code93Writer qrCodeWriter = new Code93Writer();
        BitMatrix bitMatrix = qrCodeWriter.encode(barcodeText, BarcodeFormat.CODE_93, width, height);
        ByteArrayOutputStream pngOutputStream = new ByteArrayOutputStream();
        MatrixToImageWriter.writeToStream(bitMatrix, "PNG", pngOutputStream);
        byte[] pngData = pngOutputStream.toByteArray();
        // Enregistrement du produit dans la base de données
        // MultipartFile multipartFile1 = new CommunMultipartFile(
        // new ByteArrayResource(pngData), product.getReference()+".png");

        //product.setBarcodeImage(pngData);
        Media media1 = new Media();
        String url1 = cloudinary.uploader()
                .upload(pngData,
                        Map.of("public_id", UUID.randomUUID().toString()))
                .get("url")
                .toString();
        media1.setImagenUrl(url1);

        media1.setName(product.getReference()+".png");
        product.setImage(mediaRepo.save(media1));
        // Enregistrement de l'image du code-barres
        FileOutputStream fos = new FileOutputStream("src/main/resources/assets/" + product.getReference() + ".png");
        fos.write(pngData);
        fos.flush();
        fos.close();
        // Retour de l'objet Product avec le code-barres et autres attributs
        Product savedProd = productRepo.save(product);
        List<Media> mediaList = new ArrayList<>();
        for (MultipartFile multipartFile : files) {
            Media media = new Media();
            String url = cloudinary.uploader()
                    .upload(multipartFile.getBytes(),
                            Map.of("public_id", UUID.randomUUID().toString()))
                    .get("url")
                    .toString();
            media.setImagenUrl(url);
            media.setName(multipartFile.getName());
            mediaList.add(media);
        }
        mediaRepo.saveAll(mediaList);

        productRepo.save(savedProd);
        return savedProd;
    }
*/
        public Product getProductById ( int id){

            return productRepo.findById(id).orElse(null);
        }

        public List<Product> getAllProduct (int pageNumber) {
            Pageable pageable = PageRequest.of(pageNumber, 4);
            return productRepo.findAll();
        }
   /* public List<Product> searchProducts(String name, String brand, float price) {
        return productRepo.findByNameContainingIgnoreCaseAndBrandAndPriceContainingIgnoreCase(
                name,brand,price);
    }*/

    public void deleteproduit(int id){
        productRepo.deleteById(id);
    }


    public List<Product> getProductDetails(boolean isSingeProductCheckout, Integer productId) {

        if(isSingeProductCheckout && productId != 0) {
            List<Product> list= new ArrayList<>();
            Product product = productRepo.findById(productId).get();
            list.add(product);
            return list;
        }else {

            String username = AuthTokenFilter.CURRENT_USER;
            User user = userRepo.findByUsername(username).get();
            List<Cart>  carts= cartDao.findByUser(user);

            return carts.stream().map(x -> x.getProduct()).collect(Collectors.toList());

        }


    }


}