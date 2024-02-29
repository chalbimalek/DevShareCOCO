package com.coco.pibackend.ServiceIMp;

import com.coco.pibackend.Controller.AnnonceCarpoolingController;
import com.coco.pibackend.Entity.AnnonceCarpooling;
import com.coco.pibackend.Repo.AnnonceCarpolingRepository;
import com.coco.pibackend.Service.AnnonceCarpolingService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
//import java.util.List;
@Service
@RequiredArgsConstructor
public class AnnonceCarpoolingImpl implements AnnonceCarpolingService {
    private final AnnonceCarpolingRepository   annonceCarpolingRepository;

/*
    @Override
    public AnnonceCarpooling addCarpooling(AnnonceCarpooling carpooling) {
        return  annonceCarpolingRepository.save(carpooling);
    }

    @Override
    public List<AnnonceCarpooling> retrieveAllCarpooling() {
        return  annonceCarpolingRepository.findAll();
    }

    @Override
    public AnnonceCarpooling retrieveCarpooling(int id_anno_cov) {
        return  annonceCarpolingRepository.findById(id_anno_cov).orElse(null);
    }

    @Override
    public void remooveCarpooling(int id_anno_cov) {
        annonceCarpolingRepository.deleteById(id_anno_cov);
    } */
}
