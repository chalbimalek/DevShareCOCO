package com.coco.pibackend.Controller;


import com.coco.pibackend.Entity.Carpooling;
import com.coco.pibackend.Entity.ImageModel;
import com.coco.pibackend.Entity.Notification;
import com.coco.pibackend.ServiceIMp.CarpoolingService;
import com.coco.pibackend.ServiceIMp.NotificationServiceImpl;
import com.coco.pibackend.ServiceIMp.UserServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
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
@CrossOrigin("*")
@RequestMapping("/carpooling")
public class CarpoolingController {

    private final CarpoolingService carpoolingService;
    private final UserServiceImpl userService;
    private final NotificationServiceImpl    notificationService;


    @PreAuthorize("hasRole('ROLE_MEMBRE')")

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
    @PreAuthorize("hasRole('ROLE_MEMBRE')")
    @PostMapping("/adddd")
    public Carpooling addCarpooling(@RequestBody Carpooling carpooling) {
        return carpoolingService.saveCarpooling(carpooling);
    }

    @GetMapping("searchbygouvernerat/{Gouvernorat}")
    public List<Carpooling> searchbygouvernerat(@PathVariable String Gouvernorat) {
        return carpoolingService.searchbygouvernerat(Gouvernorat);
    }
    @PreAuthorize("hasRole('ROLE_MEMBRE')")

    @PostMapping("/reserver")
    public ResponseEntity<String> reserverCovoiturage(@RequestParam Integer carpoolingId) {
        try {
            carpoolingService.reserverCovoiturage(carpoolingId);
            return ResponseEntity.ok("Réservation effectuée avec succès");
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        } catch (IllegalStateException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }
    @PreAuthorize("hasRole('ROLE_MEMBRE')")

    @PostMapping("/accepterOuRefuser/{carpoolingId}/{accepter}/{userId}")
    public ResponseEntity<String> accepterOuRefuserCovoiturage(@PathVariable("carpoolingId") Integer carpoolingId, @PathVariable("accepter") boolean accepter, @PathVariable("userId") Integer userId) {
        try {
            carpoolingService.accepterOuRefuserCovoiturage(carpoolingId,userId, accepter );
            return ResponseEntity.ok(accepter ? "Réservation acceptée" : "Réservation refusée");
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Une erreur s'est produite");
        }
    }
    @PreAuthorize("hasRole('ROLE_MEMBRE')")

    @PostMapping("/annulerAcceptation/{carpoolingId}/{userId}")
    public ResponseEntity<String> annulerAcceptationCovoiturage(@PathVariable("carpoolingId") Integer carpoolingId,@PathVariable("userId") Integer userId) {
        try {
            // Appeler le service pour annuler l'acceptation du covoiturage
            carpoolingService.annulerAcceptationCovoiturage(carpoolingId,userId);

            // Retourner une réponse OK avec un message approprié
            return ResponseEntity.ok("Acceptation du covoiturage annulée avec succès");
        } catch (IllegalArgumentException e) {
            // Gérer les exceptions liées à la non-validation des paramètres
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        } catch (Exception e) {
            // Gérer toute autre exception interne
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Une erreur s'est produite");
        }
    }
    @PreAuthorize("hasRole('ROLE_MEMBRE')")

    @GetMapping("/user")
    public ResponseEntity<List<Notification>> getNotificationsForUser() {

        List<Notification> notifications = notificationService.getNotificationsForUser();
        return ResponseEntity.ok(notifications);
    }

    @PreAuthorize("hasRole('ROLE_MEMBRE')")

    @PostMapping("/send/{carpoolingid}/{id}")
    public ResponseEntity<String> sendNotification(@PathVariable("carpoolingid") int carpoolingid,@RequestBody String message,@PathVariable("id") long id) {
        try {
            notificationService.envoyerNotification( carpoolingid, message,id);
            return ResponseEntity.ok("Notification envoyée avec succès !");
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erreur lors de l'envoi de la notification");
        }
    }
    @PreAuthorize("hasRole('ROLE_MEMBRE')")

    @GetMapping("/gain-carpooling")
    public int calculateCarpoolingGain() {
        return carpoolingService.nbrdecovoiturage();
    }

    @GetMapping("/calculatePoints")
    public int calculatePoints() {
        int totalCarpoolings = carpoolingService.calculatePoints();

        return totalCarpoolings;
    }
    }
