package com.coco.pibackend.Controller;


import com.coco.pibackend.Entity.Carpooling;
import com.coco.pibackend.Entity.ImageModel;
import com.coco.pibackend.ServiceIMp.CarpoolingService;
import com.coco.pibackend.ServiceIMp.UserServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@RestController
@RequiredArgsConstructor
@RequestMapping("/carpooling")
public class CarpoolingController {

    private final CarpoolingService carpoolingService;
    private final UserServiceImpl userService;


    @PostMapping(value = {"/addd"},consumes = {MediaType.MULTIPART_FORM_DATA_VALUE})
    public Carpooling addCarpooling(@RequestPart("product") Carpooling carpooling, @RequestPart("imageFile") MultipartFile[] file ){
        try {
                Set<ImageModel> imageModelSet = uploadImage(file);
                carpooling.setImageModels(imageModelSet);
                return carpoolingService.saveCarpooling(carpooling);

        } catch (Exception e) {
            System.out.println(e.getMessage());
            return null;
        }
    }
    public Set<ImageModel> uploadImage(MultipartFile[] multipartFiles) throws IOException {

        Set<ImageModel> imageModels=new HashSet<>();
        for(MultipartFile file:multipartFiles){
            String fileName = StringUtils.cleanPath(file.getOriginalFilename());
            ImageModel imageModel = new ImageModel();
            imageModel.setFilePath("C:\\xampp\\htdocs\\Carpooling\\" + fileName); // Utilisation du chemin souhaité
            imageModel.setBytes(file.getBytes());

            // Sauvegarder physiquement le fichier sur le système de fichiers
            saveImageToFileSystem(file, fileName);
            imageModels.add( imageModel);
        }
        return imageModels;
    }
    public void saveImageToFileSystem(MultipartFile file, String fileName) throws IOException {
        String uploadDir = "C:\\xampp\\htdocs\\Carpooling\\"; // Chemin vers le dossier de destination

        // Créer le dossier s'il n'existe pas déjà
        Path uploadPath = Paths.get(uploadDir);
        Files.createDirectories(uploadPath);

        // Écrire le fichier sur le système de fichiers
        Path filePath = uploadPath.resolve(fileName);
        Files.write(filePath, file.getBytes());
    }
    @GetMapping("/{pid}")
    public Carpooling getCarpolingById (@PathVariable int pid) {
    return carpoolingService.getCarpolingById(pid);
    }
    @GetMapping("/getall")
    public List<Carpooling> getAllCarpooling () {
        return carpoolingService.getAllCarpooling();
    }

    @DeleteMapping("/delete/{id}")
    public void deleteCarpooling(@PathVariable("id") int id) {
            carpoolingService.deleteCarpooling(id);
    }


    @GetMapping("searchbygouvernerat/{Gouvernorat}")
    public List<Carpooling> searchbygouvernerat(@PathVariable String Gouvernorat) {
        return carpoolingService.searchbygouvernerat(Gouvernorat);
    }







    }
