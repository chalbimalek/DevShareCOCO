package com.coco.pibackend.Controller;

import com.coco.pibackend.Service.AnnonceCarpolingService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/carpooling")
public class AnnonceCarpooling {
    private final AnnonceCarpolingService annonceCarpolingService;
}
