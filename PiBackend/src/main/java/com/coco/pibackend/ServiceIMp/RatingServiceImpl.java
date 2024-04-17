package com.coco.pibackend.ServiceIMp;


import com.coco.pibackend.Entity.Event;
import com.coco.pibackend.Entity.Rating;
import com.coco.pibackend.Repo.EventRepository;
import com.coco.pibackend.Repo.RateRepository;
import com.coco.pibackend.Service.RatingService;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@AllArgsConstructor

public class RatingServiceImpl implements RatingService {

    private RateRepository rateRepository;
    private EventRepository eventRepository;

@Override
    public Rating createRate(Integer EventId, int ratingValue) {




        Rating existingRating = rateRepository.findByEvent( eventRepository.findById(EventId));
        if (existingRating != null) {

            existingRating.setValue(ratingValue);
            existingRating.setRatedAt(LocalDateTime.now());
            return rateRepository.save(existingRating);
        }
        Rating ra = new Rating();

        ra.setRatedAt(LocalDateTime.now());
        ra.setEvent(eventRepository.findById(EventId));
        ra.setValue(ratingValue);

        rateRepository.save(ra);
        return ra;
    }
@Override
    public List<Rating> getAllRatesForEvent(Integer eventid) {
        return rateRepository.findByEvent_Id(eventid);
    }

@Override
    public Double getAverageRatingForEvent(Integer eventId) {
        return rateRepository.getAverageRatingForEvent(eventId);
    }
    @Override
    public int getRatingByEvent(Integer eventId){

        Event event= eventRepository.findById(eventId);
        return rateRepository.findByEvent(event).getValue();
    }
}