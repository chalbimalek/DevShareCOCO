package com.coco.pibackend.Controller;

import com.coco.pibackend.Entity.MarketPlace;
import com.coco.pibackend.Service.MarketplaceService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/marketplace")
@RequiredArgsConstructor
public class MarketPlaceController {
    private final MarketplaceService  marketplaceService;

@PostMapping("/addProduct")
    public MarketPlace addCarpooling(@RequestBody MarketPlace marketPlace) {
        return marketplaceService.addProduct(marketPlace);

    }
@PutMapping("/updateProduct")
    public MarketPlace updateProduct(@RequestBody MarketPlace marketPlace) {
            return marketplaceService.updateProduct(marketPlace);
}
@DeleteMapping("delete/{id}")
    public void Delete(int id) {
            marketplaceService.Delete(id);
    }
@GetMapping
public List<MarketPlace> GEtList() {
        return marketplaceService.GEtList();
}

}
