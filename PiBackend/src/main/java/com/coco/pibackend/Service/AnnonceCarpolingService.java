package com.coco.pibackend.Service;

import com.coco.pibackend.Entity.AnnonceCarpoling;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface AnnonceCarpolingService {

    public AnnonceCarpoling addCarpooling(AnnonceCarpoling annonceCarpoling);
    public AnnonceCarpoling updateCarpooling(AnnonceCarpoling annonceCarpoling);
    public void Delete(int id);
    public List<AnnonceCarpoling> Getcarpooling();



}
