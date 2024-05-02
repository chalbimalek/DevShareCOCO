package com.coco.pibackend.Controller;

import com.coco.pibackend.Entity.AnnonceCollocation;
import com.coco.pibackend.Entity.RendezVous;
import com.coco.pibackend.Service.AnnonceCollocationService;
import com.coco.pibackend.ServiceIMp.RendezVousService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/RendezVous")
public class RendezVousContrrrolleur {
    private final RendezVousService rendezVousService;

    @PostMapping("/add/{idu}/{idc}")
    @CrossOrigin(origins = "http://localhost:4200")
    public RendezVous addRendezVous(@RequestBody RendezVous r,@PathVariable("idu") Integer idu,@PathVariable("idc") Integer idc) {
        return rendezVousService.Add(r,idu,idc);
    }
    @PutMapping("/accaepte/{id}")
    @CrossOrigin(origins = "http://localhost:4200")
    public RendezVous Accaepte(@PathVariable("id") Integer id) {
        return rendezVousService.accaepte(id);
    }

    @DeleteMapping("/Refuse/{id}")
    @CrossOrigin(origins = "http://localhost:4200")
    public void Refuse(@PathVariable("id") Integer id) {
         rendezVousService.delteRendezvous(id);
    }

    @DeleteMapping("/remove/{id}")
    @CrossOrigin(origins = "http://localhost:4200")
    public void remove(@PathVariable("id") Integer id) {
        rendezVousService.delte(id);
    }
    @GetMapping("/findbyannoance/{id}")
    @CrossOrigin(origins = "http://localhost:4200")
    public List<RendezVous> findbyannoance(@PathVariable("id") Integer id) {
        return rendezVousService.findbyannoance(id);
    }

    @GetMapping("/findbyOwnerOrclient/{id}")
    @CrossOrigin(origins = "http://localhost:4200")
    public List<RendezVous> findbyOwnerOrclient(@PathVariable("id") Integer id) {
        return rendezVousService.findbyOwnerOrclient(id);
    }
    @GetMapping("/findbyOwner/{id}")
    @CrossOrigin(origins = "http://localhost:4200")
    public List<RendezVous> findbyOwner(@PathVariable("id") Integer id) {
        return rendezVousService.findbyOwner(id);
    }
}
