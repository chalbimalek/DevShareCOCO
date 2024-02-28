package com.coco.pibackend.Controller;

import com.coco.pibackend.Entity.AnnonceCarpoling;
import com.coco.pibackend.Service.AnnonceCarpolingService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/carpooling")
public class AnnonceCarpoolingController {


    private final AnnonceCarpolingService annonceCarpolingService;

@PostMapping("/addCarpooling")
    public AnnonceCarpoling addCarpooling(@RequestBody AnnonceCarpoling annonceCarpoling) {
        return annonceCarpolingService.addCarpooling(annonceCarpoling);
    }
@PutMapping("/update")
    public AnnonceCarpoling updateCarpooling(@RequestBody AnnonceCarpoling annonceCarpoling) {
        return annonceCarpolingService.updateCarpooling(annonceCarpoling);
    }
@DeleteMapping("/delete/{id}")
    public void Delete(@PathVariable("id") int id) {
        annonceCarpolingService.Delete(id);
}

@GetMapping("/getall")
    public List<AnnonceCarpoling> Getcarpooling() {
        return annonceCarpolingService.Getcarpooling();

}













    }
