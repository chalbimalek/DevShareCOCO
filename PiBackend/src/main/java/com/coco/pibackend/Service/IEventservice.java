package com.coco.pibackend.Service;

import com.coco.pibackend.Entity.Event;

import java.util.List;
import java.util.Optional;

public interface IEventservice {


    Event ajouterEvents(Event event);


    List<Event> retrieveAllEvent();

    void deleteEvent(Long id);

    Optional<Event> retrievEventByID(Long id);

    Event updateEvent(Long id, Event updatedvent);


    List<Event> searchEventByTopic(String topic);


    List<Event> searchEventAdvanced(Event event);
}
