package com.coco.pibackend.Service;

import com.coco.pibackend.Entity.Rating;

import java.util.List;

public interface RatingService {


    Rating createRate(Integer EventId, int ratingValue);


    List<Rating> getAllRatesForEvent(Integer eventid);

    Double getAverageRatingForEvent(Integer eventId);


    int getRatingByEvent(Integer eventId);
}
