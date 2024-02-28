package com.coco.pibackend.ServiceIMp;

import com.coco.pibackend.Entity.AnnonceCarpoling;
import com.coco.pibackend.Repo.AnnonceCarpolingRepository;
import com.coco.pibackend.Service.AnnonceCarpolingService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AnnonceCarpooling implements AnnonceCarpolingService {
    private final AnnonceCarpolingRepository   annonceCarpolingRepository;


    @Override
    public AnnonceCarpoling addCarpooling(AnnonceCarpoling annonceCarpoling) {
         return annonceCarpolingRepository.save(annonceCarpoling);
    }

    @Override
    public AnnonceCarpoling updateCarpooling(AnnonceCarpoling annonceCarpoling) {
        return annonceCarpolingRepository.save(annonceCarpoling);
    }

    @Override
    public void Delete(int id) {
        annonceCarpolingRepository.deleteById(id);

    }

    @Override
    public List<AnnonceCarpoling> Getcarpooling() {
        return annonceCarpolingRepository.findAll();
    }
}
