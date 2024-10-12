package com.example.visitdestinationreview;

import com.example.visitdestinationreview.domain.User;
import com.example.visitdestinationreview.domain.VisitDestinationReview;
import com.example.visitdestinationreview.repository.UserRepository;
import com.example.visitdestinationreview.repository.VisitDestinationReviewRepository;
import com.example.visitdestinationreview.service.TourismService;
import com.example.visitdestinationreview.service.VisitDestinationReviewDto;
import java.time.LocalDateTime;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
    @Slf4j
    class TourismServiceTest {

        @Autowired
        private TourismService tourismService;

        @Autowired
        private UserRepository userRepository;

        @Autowired
        private VisitDestinationReviewRepository reviewRepository;


        @Test
        void compareEagerAndLazyLoading() {
            // EAGER 로딩 테스트
            log.info("--- EAGER Loading Test ---");
            long startTimeEager = System.currentTimeMillis();
            List<VisitDestinationReviewDto> eagerResults = tourismService.searchDestinationsV2("Destination");
            long endTimeEager = System.currentTimeMillis();
            log.info("EAGER Loading Time: {} ms", endTimeEager - startTimeEager);
            log.info("EAGER Results size: {}", eagerResults.size());

//            // LAZY 로딩 테스트
//            log.info("--- LAZY Loading Test ---");
//            long startTimeLazy = System.currentTimeMillis();
//            List<VisitDestinationReviewDto> lazyResults = tourismService.searchDestinationsV2("Destination");
//            long endTimeLazy = System.currentTimeMillis();
//            log.info("LAZY Loading Time: {} ms", endTimeLazy - startTimeLazy);
//            log.info("LAZY Results size: {}", lazyResults.size());
        }
    }