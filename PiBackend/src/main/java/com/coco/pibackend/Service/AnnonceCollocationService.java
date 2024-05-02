package com.coco.pibackend.Service;

import com.coco.pibackend.Entity.AnnonceCollocation;
import org.springframework.data.domain.Page;

import java.util.Date;
import java.util.List;

public interface AnnonceCollocationService {
   AnnonceCollocation addAnnonceCollocation(AnnonceCollocation annonceCollocation);
    AnnonceCollocation updateAnnonceCollocation(AnnonceCollocation annonceCollocation);
    void deleteAnnonceCollocation(int id);
    AnnonceCollocation getAnnonceCollocationById(int id);
    List<AnnonceCollocation> getAllAnnonceCollocation();
    List<AnnonceCollocation> getAnnonceCollocationByUser(int id_user);
  Page<AnnonceCollocation> getAllAnnoncesCollocationOrderByMontantContrubitionAsc(int pageNumber, int pageSize);
    void SendRequestMeet(int id,String message);

}
