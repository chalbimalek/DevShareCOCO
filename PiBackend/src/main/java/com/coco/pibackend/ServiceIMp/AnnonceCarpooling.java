package com.coco.pibackend.ServiceIMp;

import com.coco.pibackend.Repo.AnnonceCarpolingRepository;
import com.coco.pibackend.Service.AnnonceCarpolingService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AnnonceCarpooling implements AnnonceCarpolingService {
    private final AnnonceCarpolingRepository   annonceCarpolingRepository;


}
