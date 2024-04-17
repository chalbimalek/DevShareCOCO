package com.coco.pibackend.ServiceIMp;

import com.coco.pibackend.Entity.Event;
import com.coco.pibackend.Repo.EventRepository;
import com.coco.pibackend.Service.IEventservice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public  class Eventservice implements IEventservice {
    @Autowired
    private EventRepository eventRepository;

    @Override
    public Event ajouterEvents(Event event) {
        return eventRepository.save(event);
    }

    @Override
    public List<Event> retrieveAllEvent() {
        List<Event> events = (List<Event>) eventRepository.findAll();
        return events;

    }

    @Override
    public void deleteEvent(Long id) {
        eventRepository.deleteById(id);

    }

    @Override
    public Optional<Event> retrievEventByID(Long id) {
        Optional<Event> event = eventRepository.findById(id);
        return event;

    }

    @Override
    public Event updateEvent(Long id, Event updatedvent) {
        Event E = eventRepository.findById(id).orElse(null);
        if (E != null) {
            E.setNumberParticipant(updatedvent.getNumberParticipant());
            E.setStartDate(updatedvent.getStartDate());
            E.setEndDate(updatedvent.getEndDate());
            E.setLocation_event(updatedvent.getLocation_event());
            E.setCreated(updatedvent.getCreated());
            E.setTopic(updatedvent.getTopic());


            return eventRepository.save(E);
        }

        return null;
    }

    @Override
    public List<Event> searchEventByTopic(String topic) {
        return eventRepository.findByTopic(topic);
    }
    @Override
    public List<Event> searchEventAdvanced(Event event) {
        ExampleMatcher matcher = ExampleMatcher.matching()
                .withIgnoreNullValues()
                .withIgnoreCase()
                .withStringMatcher(ExampleMatcher.StringMatcher.CONTAINING); 

        Example<Event> example = Example.of(event, matcher);
        return eventRepository.findAll(example);
    }
}






