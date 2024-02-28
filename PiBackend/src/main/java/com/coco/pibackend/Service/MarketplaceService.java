package com.coco.pibackend.Service;

import com.coco.pibackend.Entity.AnnonceCarpoling;
import com.coco.pibackend.Entity.MarketPlace;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface MarketplaceService {

    public MarketPlace addProduct(MarketPlace marketPlace);
    public MarketPlace updateProduct(MarketPlace marketPlace);
    public void Delete(int id);
    public List<MarketPlace> GEtList();
}
