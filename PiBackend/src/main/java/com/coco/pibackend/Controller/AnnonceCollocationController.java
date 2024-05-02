package com.coco.pibackend.Controller;

import com.coco.pibackend.Entity.AnnonceCollocation;
import com.coco.pibackend.Service.AnnonceCollocationService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/collcation")
public class AnnonceCollocationController {
    private final AnnonceCollocationService annonceCollocationService;

    @PostMapping("/add")
    @CrossOrigin(origins = "http://localhost:4200")
    public AnnonceCollocation addAnnonceCollocation(@RequestBody AnnonceCollocation annonceCollocation) {
        return annonceCollocationService.addAnnonceCollocation(annonceCollocation);
    }

    @DeleteMapping("/{id_anno_colo}")
    @CrossOrigin(origins = "http://localhost:4200")
    public void deleteAnnonceCollocation(@PathVariable int id_anno_colo) {
        annonceCollocationService.deleteAnnonceCollocation(id_anno_colo);
    }

    @PostMapping("/update")
    @CrossOrigin(origins = "http://localhost:4200")
    public AnnonceCollocation updateAnnonceCollocation(@RequestBody AnnonceCollocation annonceCollocation) {
        return annonceCollocationService.updateAnnonceCollocation(annonceCollocation);
    }

    @GetMapping("/{id_anno_colo}")
    @CrossOrigin(origins = "http://localhost:4200")
    public AnnonceCollocation getAnnonceCollocationById(@PathVariable("id_anno_colo") int id_anno_colo) {
        return annonceCollocationService.getAnnonceCollocationById(id_anno_colo);
    }

    @GetMapping("/all")
    @CrossOrigin(origins = "http://localhost:4200")
    public List<AnnonceCollocation> getAllAnnonceCollocation() {
        return annonceCollocationService.getAllAnnonceCollocation();
    }

    @GetMapping("/user/{id_user}")
    @CrossOrigin(origins = "http://localhost:4200")
    public List<AnnonceCollocation> getAnnonceCollocationByUser(@PathVariable("id_user") int id_user) {
        return annonceCollocationService.getAnnonceCollocationByUser(id_user);
    }
    @PutMapping("/SmSMeet/{id}/{message}")
    @CrossOrigin(origins = "http://localhost:4200")
    public void SendRequestMeet(@PathVariable("id")int id,@PathVariable("message")String message){
        annonceCollocationService.SendRequestMeet(id,message);
    }

    @GetMapping("/Paggination/{pageNumber}/{pageSize}")
    @CrossOrigin(origins = "http://localhost:4200")
    public Page<AnnonceCollocation> getAllAnnoncesCollocationOrderByMontantContrubitionAsc(@PathVariable("pageNumber") int pageNumber,@PathVariable("pageSize") int pageSize) {

        return annonceCollocationService.getAllAnnoncesCollocationOrderByMontantContrubitionAsc(pageNumber,pageSize);
    }
}
