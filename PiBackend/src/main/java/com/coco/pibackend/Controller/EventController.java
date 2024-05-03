package com.coco.pibackend.Controller;

import com.coco.pibackend.Entity.*;

import com.coco.pibackend.Enum.Category;
import com.coco.pibackend.Enum.Type_Event;
import com.coco.pibackend.Repo.EventsRepository;
import com.coco.pibackend.Service.IEventservice;
import com.coco.pibackend.ServiceIMp.Eventservice;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
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
import java.util.Optional;
import java.util.Set;

@RestController
@AllArgsConstructor
@CrossOrigin("*")
@RequestMapping("event")
public class EventController {

    private final Eventservice eventservice;


    @PreAuthorize("hasRole('ROLE_MEMBRE')")

    @PostMapping(value = {"/addd"},consumes = {MediaType.MULTIPART_FORM_DATA_VALUE})
    public Events addCarpooling(@RequestPart("product") Events events, @RequestPart("imageFile") MultipartFile[] file ){
        try {
            Set<ImageModel> imageModelSet = uploadImage(file);
            events.setImageModels(imageModelSet);
            return eventservice.addEvents(events);

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
            imageModel.setFilePath("C:\\xampp\\htdocs\\Events\\" + fileName); // Utilisation du chemin souhaité
            imageModel.setBytes(file.getBytes());

            // Sauvegarder physiquement le fichier sur le système de fichiers
            saveImageToFileSystem(file, fileName);
            imageModels.add( imageModel);
        }
        return imageModels;
    }
    public void saveImageToFileSystem(MultipartFile file, String fileName) throws IOException {
        String uploadDir = "C:\\xampp\\htdocs\\Events\\"; // Chemin vers le dossier de destination

        // Créer le dossier s'il n'existe pas déjà
        Path uploadPath = Paths.get(uploadDir);
        Files.createDirectories(uploadPath);

        // Écrire le fichier sur le système de fichiers
        Path filePath = uploadPath.resolve(fileName);
        Files.write(filePath, file.getBytes());
    }
    @GetMapping("/{pid}")
    public Events getCarpolingById (@PathVariable int pid) {
        return eventservice.getProductById(pid);
    }
    @GetMapping("/getall")
    public List<Events> getAllCarpooling () {
        return eventservice.getAllProduct();
    }


    @GetMapping("/events")
    public List<Events> getProductsByCategory(@RequestParam("category") String category) {
        Type_Event categoryEnum = Type_Event.valueOf(category.toUpperCase());

        return eventservice.getProductsByCategory(categoryEnum);
    }











    /*
    @Autowired
   // private IEventservice eventservice;
    @Autowired
    EventsRepository eventRepository;

    @PostMapping("/Ajouter")
    @ResponseBody
    public Event ajouterEvents(@RequestBody Event event){

        return eventservice.ajouterEvents(event);

    }
    @GetMapping("/retrieveallevents")
    public List<Event> getEvents() {
        List<Event> listEvents = eventservice.retrieveAllEvent();
        return listEvents;
    }
    @PutMapping("/modifyEvent/{id}")
    @ResponseBody
    public Event modifyEvent(@PathVariable("id") Long id, @RequestBody Event updatedevent) {
        return eventservice.updateEvent(id, updatedevent);
    }


    @DeleteMapping("/removeEvent/{id}")
    @ResponseBody
    public void removeEvent(@PathVariable("id") Long id) {
        eventservice.deleteEvent(id);

    }
    //recherche
    @GetMapping("/events/topic")
    public ResponseEntity<List<Event>> getEventsByTopic(@RequestParam String topic){
        return new ResponseEntity<List<Event>>(eventRepository.findByTopic(topic), HttpStatus.OK);


    }
    @GetMapping("/events/{id}")
    public Optional<Event> getEventsByID(@PathVariable("id") Long id){
        return eventservice.retrievEventByID(id);


    }
    @PostMapping("/searchAdvancedEvents")
    @ResponseBody
    public List<Event> searchAdvancedEvents(@RequestBody Event searchCriteria) {
        return eventservice.searchEventAdvanced(searchCriteria);
    }
    @PostMapping("/AddeventFile/{id}")
    public ResponseEntity<Event> AddPostFile(@RequestParam("file") MultipartFile multipartFile,
                                             @RequestBody Long id
    )
    {
        Event messsage = eventservice.addFile(multipartFile,id);
        return ResponseEntity.ok().body(messsage);
    }

    @GetMapping("/retrieveFile/{id}")
    public ResponseEntity<byte[]> retrieveFile(@PathVariable("id") Long id) throws IOException {
        return eventservice.retrieveFile(id);
    }
*/


}
