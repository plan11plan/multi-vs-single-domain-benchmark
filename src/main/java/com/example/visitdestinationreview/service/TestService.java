package com.example.visitdestinationreview.service;

import com.example.visitdestinationreview.domain.VisitDestinationReview;
import com.example.visitdestinationreview.repository.UserRepository;
import com.example.visitdestinationreview.repository.VisitDestinationReviewRepository;
import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class TestService {
    private final UserRepository userRepository;
    private final VisitDestinationReviewRepository visitDestinationReviewRepository;


    @Transactional(readOnly = true)
    public List<VisitDestinationReviewDto> findByDestinationName(String name) {
//        validateDestinationName(name);
        List<VisitDestinationReview>  entities = visitDestinationReviewRepository.findByDestinationName(name);
        List<VisitDestinationReviewDto> dtos =  entities.stream().map(VisitDestinationReviewDto::new).collect(
                Collectors.toList());
        return dtos;
    }

    @Transactional(readOnly = true)
    public List<VisitDestinationReviewDto> findAllById(Long id) {
//        validateDestinationName(name);
        List<VisitDestinationReview>  entities = visitDestinationReviewRepository.findAllByUserId(id);
        List<VisitDestinationReviewDto> dtos =  entities.stream().map(VisitDestinationReviewDto::new).collect(
                Collectors.toList());
        return dtos;
    }

}
