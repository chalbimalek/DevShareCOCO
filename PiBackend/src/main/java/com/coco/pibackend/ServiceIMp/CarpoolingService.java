package com.coco.pibackend.ServiceIMp;

import com.coco.pibackend.Entity.Carpooling;
import com.coco.pibackend.Repo.CarpoolingRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CarpoolingService {

    private final CarpoolingRepo carpoolingRepo;

    public Carpooling saveCarpooling (Carpooling carpooling) {


        return carpoolingRepo.save(carpooling);
    }


    public Carpooling getCarpolingById (int id){

        return carpoolingRepo.findById(id).orElse(null);
    }

    public List<Carpooling> getAllCarpooling () {
        return carpoolingRepo.findAll();
    }


    public void deleteCarpooling(int id){
        carpoolingRepo.deleteById(id);
    }

    public List<Carpooling> searchbygouvernerat(String Gouvernorat){
        return carpoolingRepo.getCarpoolingByGouvernorat(Gouvernorat);
    }

}
