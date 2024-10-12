package com.example.visitdestinationreview.controller;

import com.example.visitdestinationreview.domain.VisitDestinationReview;
import com.example.visitdestinationreview.service.TourismService;
import com.example.visitdestinationreview.service.VisitDestinationReviewDto;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/tourism")
@RequiredArgsConstructor
public class TourismController {
    
    private final TourismService tourismService;


    @GetMapping("/destinations/v2")
    public List<VisitDestinationReviewDto> searchDestinationsV2(@RequestParam String name) {
        List<VisitDestinationReviewDto> visitDestinationReviewDtos = tourismService.searchDestinationsV2(name);
        return visitDestinationReviewDtos;
    }


    @GetMapping("/destinations")
    public List<VisitDestinationReview> searchDestinations(@RequestParam String name) {
        List<VisitDestinationReview> destinations = tourismService.searchDestinations(name);
        return destinations;
    }

    @GetMapping("/visits")
    public ResponseEntity<List<VisitDestinationReview>> getUserVisits(@RequestParam String username, 
                                                                      @RequestParam String destinationName) {
        List<VisitDestinationReview> visits = tourismService.getUserVisits(username, destinationName);
        return ResponseEntity.ok(visits);
    }

    @GetMapping("/reviews")
    public ResponseEntity<List<VisitDestinationReview>> getDetailedReviews(@RequestParam String username, 
                                                                           @RequestParam String destinationName, 
                                                                           @RequestParam(defaultValue = "0") int minRating) {
        List<VisitDestinationReview> reviews = tourismService.getDetailedReviews(username, destinationName, minRating);
        return ResponseEntity.ok(reviews);
    }
}