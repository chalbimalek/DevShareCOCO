package com.coco.pibackend.ServiceIMp;

import com.coco.pibackend.Entity.MarketPlace;
import com.coco.pibackend.Repo.ProductREpo;
import com.coco.pibackend.Service.MarketplaceService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MarketPlaceImp implements MarketplaceService {

    private final ProductREpo productREpo;

    @Override
    public MarketPlace addProduct(MarketPlace marketPlace) {
        return productREpo.save(marketPlace);
    }

    @Override
    public MarketPlace updateProduct(MarketPlace marketPlace) {
        return productREpo.save(marketPlace);
    }

    @Override
    public void Delete(int id) {
        productREpo.deleteById(id);
    }

    @Override
    public List<MarketPlace> GEtList() {
        return productREpo.findAll();
    }
}
