package com.example.visitdestinationreview;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@SpringBootTest
@ActiveProfiles("test")
public class DataGeneratorTest {
    private static final int USER_COUNT = 100_000;
    private static final int REVIEW_COUNT = 1_000_000;
    private static final Random random = new Random();

    @Autowired
    private JdbcTemplate jdbcTemplate;

    /**
     * 랜덤 문자열 생성 메서드
     */
    private static String generateRandomString(int length) {
        return random.ints(97, 123)
                .limit(length)
                .collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append)
                .toString();
    }

    /**
     * 사용자와 방문 리뷰 데이터를 한꺼번에 생성하는 테스트
     */
    @Test
    public void generateTestData() {
        generateUsers();
        generateVisitDestinationReviews();
    }

    /**
     * 사용자 데이터를 생성하는 메서드
     */
    @Test
    public void generateUsers() {
        String sql = "INSERT INTO users (username, password, email) VALUES (?, ?, ?)";
        List<Object[]> batchArgs = new ArrayList<>();

        for (int i = 0; i < USER_COUNT; i++) {
            System.out.println(i + "번째 사용자 생성 중");
            String username = "user" + i;
            String password = generateRandomString(10);
            String email = username + "@example.com";
            batchArgs.add(new Object[]{username, password, email});

            if (i % 1000 == 0) {
                jdbcTemplate.batchUpdate(sql, batchArgs);
                batchArgs.clear();
            }
        }

        if (!batchArgs.isEmpty()) {
            jdbcTemplate.batchUpdate(sql, batchArgs);
        }

        System.out.println("사용자 데이터 생성 완료");
    }

    /**
     * 방문지 리뷰 데이터를 생성하는 메서드
     */
    @Test
    public void generateVisitDestinationReviews() {
        String sql = "INSERT INTO visit_destination_reviews (user_id, destination_name, destination_description, latitude, longitude, visit_date, review_content, rating, review_created_at) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
        List<Object[]> batchArgs = new ArrayList<>();

        for (int i = 0; i < REVIEW_COUNT; i++) {
            System.out.println(i + "번째 리뷰 생성 중");
            long userId = random.nextInt(USER_COUNT) + 1;
            String destinationName = "Destination" + random.nextInt(1000);
            String description = generateRandomString(50);
            double latitude = random.nextDouble() * 180 - 90;
            double longitude = random.nextDouble() * 360 - 180;
            LocalDateTime visitDate = LocalDateTime.now().minusDays(random.nextInt(365));
            String reviewContent = random.nextBoolean() ? generateRandomString(100) : null;
            Integer rating = random.nextBoolean() ? random.nextInt(5) + 1 : null;
            LocalDateTime reviewCreatedAt = rating != null ? LocalDateTime.now().minusDays(random.nextInt(30)) : null;

            batchArgs.add(new Object[]{userId, destinationName, description, latitude, longitude, visitDate, reviewContent, rating, reviewCreatedAt});

            if (i % 10000 == 0) {
                jdbcTemplate.batchUpdate(sql, batchArgs);
                batchArgs.clear();
            }
        }

        if (!batchArgs.isEmpty()) {
            jdbcTemplate.batchUpdate(sql, batchArgs);
        }

        System.out.println("리뷰 데이터 생성 완료");
    }
}