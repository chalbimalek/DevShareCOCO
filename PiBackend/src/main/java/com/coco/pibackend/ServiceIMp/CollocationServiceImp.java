package com.coco.pibackend.ServiceIMp;


import com.coco.pibackend.Entity.Collocation;
import com.coco.pibackend.Repo.CollocationRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CollocationServiceImp {
    private final CollocationRepo collocationRepo;

    public Collocation saveCarpooling (Collocation collocation) {


        return collocationRepo.save(collocation);
    }


    public Collocation getCarpolingById (int id){

        return collocationRepo.findById(id).orElse(null);
    }

    public List<Collocation> getAllCarpooling () {
        return collocationRepo.findAll();
    }


    public void deleteCarpooling(int id){
        collocationRepo.deleteById(id);
    }

    public List<Collocation> searchbygouvernerat(String lieux){
        return collocationRepo.getCollocationsByLieux(lieux);
    }

}


