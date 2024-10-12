package com.example.visitdestinationreview.repository;

import com.example.visitdestinationreview.domain.VisitDestinationReview;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
public interface VisitDestinationReviewRepository extends JpaRepository<VisitDestinationReview, Long> {

    @Query("SELECT v FROM VisitDestinationReview v JOIN FETCH v.user WHERE LOWER(v.destinationName) LIKE LOWER(CONCAT('%', :name, '%'))")

    List<VisitDestinationReview> findByDestinationNameContainingIgnoreCase(String name);

//    @Query("SELECT vdr FROM VisitDestinationReview vdr JOIN FETCH vdr.user WHERE UPPER(vdr.destinationName) LIKE UPPER(:name)")
//    List<VisitDestinationReview> findByDestinationNameContainingIgnoreCaseWithUser(@Param("name") String name);


    List<VisitDestinationReview> findByDestinationName(String name);
    List<VisitDestinationReview> findAllByUserId(Long id);



    @Query("SELECT vdr FROM VisitDestinationReview vdr " +
           "JOIN FETCH vdr.user u " +
           "WHERE u.username = :username " +
           "AND vdr.destinationName LIKE %:destinationName%")
    List<VisitDestinationReview> findByUserAndDestination(@Param("username") String username,
                                                          @Param("destinationName") String destinationName);

    @Query("SELECT vdr FROM VisitDestinationReview vdr " +
           "JOIN FETCH vdr.user u " +
           "WHERE u.username = :username " +
           "AND vdr.destinationName LIKE %:destinationName% " +
           "AND vdr.rating >= :minRating")
    List<VisitDestinationReview> findDetailedReviews(@Param("username") String username, 
                                                     @Param("destinationName") String destinationName, 
                                                     @Param("minRating") int minRating);
}

