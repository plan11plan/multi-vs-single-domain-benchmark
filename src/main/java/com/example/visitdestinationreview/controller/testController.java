package com.example.visitdestinationreview.controller;

import com.example.visitdestinationreview.service.TestService;
import com.example.visitdestinationreview.service.TourismService;
import com.example.visitdestinationreview.service.VisitDestinationReviewDto;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/tourism")
@RequiredArgsConstructor
public class testController {
    private final TestService testService;


    @GetMapping("/destinations/name")
    public List<VisitDestinationReviewDto> searchDestinationsV2(@RequestParam String name) {
        List<VisitDestinationReviewDto> visitDestinationReviewDtos = testService.findByDestinationName(name);
        return visitDestinationReviewDtos;
    }
    @GetMapping("/destinations/id")
    public List<VisitDestinationReviewDto> searchDestinationsV2(@RequestParam Long id) {
        List<VisitDestinationReviewDto> visitDestinationReviewDtos = testService.findAllById( id);
            return visitDestinationReviewDtos;
        }
    }

