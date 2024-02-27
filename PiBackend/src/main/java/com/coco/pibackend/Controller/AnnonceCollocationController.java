package com.coco.pibackend.Controller;

import com.coco.pibackend.Entity.AnnonceCollocation;
import com.coco.pibackend.Service.AnnonceCollocationService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/collcation")
public class AnnonceCollocationController {
    private final AnnonceCollocationService annonceCollocationService;

    @PostMapping("/add")
    public AnnonceCollocation addAnnonceCollocation(@RequestBody AnnonceCollocation annonceCollocation) {
        return annonceCollocationService.addAnnonceCollocation(annonceCollocation);
    }

    @DeleteMapping("/{id_anno_colo}")
    public void deleteAnnonceCollocation(@PathVariable int id_anno_colo) {
        annonceCollocationService.deleteAnnonceCollocation(id_anno_colo);
    }

    @PutMapping("/update")
    public AnnonceCollocation updateAnnonceCollocation(@RequestBody AnnonceCollocation annonceCollocation) {
        return annonceCollocationService.updateAnnonceCollocation(annonceCollocation);
    }

    @GetMapping("/{id_anno_colo}")
    public AnnonceCollocation getAnnonceCollocationById(@PathVariable int id_anno_colo) {
        return annonceCollocationService.getAnnonceCollocationById(id_anno_colo);
    }

    @GetMapping("/all")
    public List<AnnonceCollocation> getAllAnnonceCollocation() {
        return annonceCollocationService.getAllAnnonceCollocation();
    }

    @GetMapping("/user/{id_user}")
    public List<AnnonceCollocation> getAnnonceCollocationByUser(@PathVariable int id_user) {
        return annonceCollocationService.getAnnonceCollocationByUser(id_user);
    }




}
