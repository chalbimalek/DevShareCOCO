package com.coco.pibackend.Controller;

import com.coco.pibackend.Entity.Event;

import com.coco.pibackend.Repo.EventsRepository;
import com.coco.pibackend.Service.IEventservice;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

@RestController
@AllArgsConstructor
@CrossOrigin("*")
@RequestMapping("event")
public class EventController {/*
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
