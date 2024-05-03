package com.coco.pibackend.Controller;

import com.coco.pibackend.Entity.Rating;

import com.coco.pibackend.ServiceIMp.RatingServiceImpl;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.util.List;
@CrossOrigin("*")
@AllArgsConstructor
@RestController

@RequestMapping("/rating")
public class RatingController {
    private RatingServiceImpl ratingService;

    @PostMapping("/add/{eventId}/{value}")
    public Rating createRate(@PathVariable("eventId") Integer eventId , @PathVariable("value") int ratingvalue) {
        return ratingService.createRate(eventId,ratingvalue);
    }

    @GetMapping("/findByEvent/{eventId}")
    public ResponseEntity<List<Rating>> getAllRatesEvent(@PathVariable("eventId") Integer eventId) {
        List<Rating> ratings = ratingService.getAllRatesForEvent(eventId);
        return ResponseEntity.ok(ratings);
    }


    @GetMapping("/EventAverage/{eventId}")
    public Double getAverageRatingForEvent(@PathVariable("eventId") Integer eventId) {
        return ratingService.getAverageRatingForEvent(eventId);
    }

    @GetMapping("/getRating/{eventId}")
    public int getRatingbyEvent(@PathVariable("eventId") Integer eventId){
        return ratingService.getRatingByEvent(eventId);
    }
}
