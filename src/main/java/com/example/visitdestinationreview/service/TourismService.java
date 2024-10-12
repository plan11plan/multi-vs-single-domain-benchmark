package com.example.visitdestinationreview.service;

import com.example.visitdestinationreview.domain.User;
import com.example.visitdestinationreview.domain.VisitDestinationReview;
import com.example.visitdestinationreview.repository.UserRepository;
import com.example.visitdestinationreview.repository.VisitDestinationReviewRepository;
import com.example.visitdestinationreview.exception.UserNotFoundException;
import com.example.visitdestinationreview.exception.InvalidParameterException;
import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

@Service
@RequiredArgsConstructor
public class TourismService {

    private final UserRepository userRepository;
    private final VisitDestinationReviewRepository visitDestinationReviewRepository;


    @Async
    @Transactional(readOnly = true)
    public List<VisitDestinationReviewDto> searchDestinationsV2(String name) {
//        validateDestinationName(name);
        List<VisitDestinationReview>  entities = visitDestinationReviewRepository.findByDestinationNameContainingIgnoreCase(name);
        List<VisitDestinationReviewDto> dtos =  entities.stream().map(VisitDestinationReviewDto::new).collect(Collectors.toList());
        return dtos;
    }


    @Transactional(readOnly = true)
    public List<VisitDestinationReview> searchDestinations(String name) {
        validateDestinationName(name);
        return visitDestinationReviewRepository.findByDestinationNameContainingIgnoreCase(name);
    }

    public List<VisitDestinationReview> getUserVisits(String username, String destinationName) {
        validateUsername(username);
        validateDestinationName(destinationName);
        ensureUserExists(username);

        return visitDestinationReviewRepository.findByUserAndDestination(username, destinationName);
    }

    public List<VisitDestinationReview> getDetailedReviews(String username, String destinationName, int minRating) {
        validateUsername(username);
        validateDestinationName(destinationName);
        validateRating(minRating);
        ensureUserExists(username);

        return visitDestinationReviewRepository.findDetailedReviews(username, destinationName, minRating);
    }

    private void validateUsername(String username) {
        if (!StringUtils.hasText(username)) {
            throw new InvalidParameterException("Username must not be empty");
        }
    }

    private void validateDestinationName(String destinationName) {
        if (!StringUtils.hasText(destinationName)) {
            throw new InvalidParameterException("Destination name must not be empty");
        }
    }

    private void validateRating(int rating) {
        if (rating < 1 || rating > 5) {
            throw new InvalidParameterException("Rating must be between 1 and 5");
        }
    }

    private void ensureUserExists(String username) {
        userRepository.findByUsername(username)
                .orElseThrow(() -> new UserNotFoundException("User not found: " + username));
    }
}