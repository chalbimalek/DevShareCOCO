package com.coco.pibackend.ServiceIMp;

import com.coco.pibackend.Repo.ProductREpo;
import com.coco.pibackend.Service.MarketplaceService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MarketPlaceImp implements MarketplaceService {

    private final ProductREpo productREpo;

}
