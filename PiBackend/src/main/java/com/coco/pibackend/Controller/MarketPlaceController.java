package com.coco.pibackend.Controller;

import com.coco.pibackend.Service.MarketplaceService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/marketplace")
@RequiredArgsConstructor
public class MarketPlaceController {
    private final MarketplaceService  marketplaceService;
}
