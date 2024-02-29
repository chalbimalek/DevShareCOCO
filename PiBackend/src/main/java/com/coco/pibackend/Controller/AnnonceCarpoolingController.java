package com.coco.pibackend.Controller;

import com.coco.pibackend.Entity.AnnonceCarpooling;
import com.coco.pibackend.Service.AnnonceCarpolingService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

//import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/carpooling")
public class AnnonceCarpoolingController {
    private final AnnonceCarpolingService annonceCarpolingService;

    //@PostMapping("/add")
   /* public AnnonceCarpooling addCarpooling(@RequestBody AnnonceCarpooling carpooling) {
        return annonceCarpolingService.addCarpooling(carpooling);
    }
    @GetMapping("/getAll")
    public List<AnnonceCarpooling> retrieveAllCarpooling() {
        return annonceCarpolingService.retrieveAllCarpooling();
    }
    @GetMapping("/get/{idCarpooling}")
    public AnnonceCarpooling retrieveCarpooling(@PathVariable("idCarpooling") int id_anno_cov) {
        return annonceCarpolingService.retrieveCarpooling(id_anno_cov);
    }
    @DeleteMapping("/del/{idcarp}")
    public void remooveCarpooling(@PathVariable("idcarp") int id_anno_cov) {
        annonceCarpolingService.remooveCarpooling(id_anno_cov);
    }*/
}
