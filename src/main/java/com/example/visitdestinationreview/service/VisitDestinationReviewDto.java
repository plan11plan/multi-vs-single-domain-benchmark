package com.example.visitdestinationreview.service;

import com.example.visitdestinationreview.domain.Coordinates;
import com.example.visitdestinationreview.domain.User;
import com.example.visitdestinationreview.domain.VisitDestinationReview;
import jakarta.persistence.Column;
import jakarta.persistence.Embedded;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import java.time.LocalDateTime;
import javax.print.attribute.standard.Destination;
import lombok.Data;

@Data
public class VisitDestinationReviewDto {

    private Long id;
    //
    private Long userId;
    private String userName;
    //

    private String destinationName;

    private String destinationDescription;

    private Coordinates coordinates;

    private LocalDateTime visitDate;

    private String reviewContent;

    private Integer rating;

    private LocalDateTime reviewCreatedAt;

    public VisitDestinationReviewDto(VisitDestinationReview visitDestinationReview) {
        this.id = visitDestinationReview.getId();
        this.userId =visitDestinationReview.getUser().getId(); // Lazy 초기화
        this.userName =visitDestinationReview.getUser().getUsername();
        this.destinationName = visitDestinationReview.getDestinationName();
        this.destinationDescription = visitDestinationReview.getDestinationDescription();
        this.coordinates = visitDestinationReview.getCoordinates();
        this.visitDate = visitDestinationReview.getVisitDate();
        this.reviewContent = visitDestinationReview.getReviewContent();
        this.rating = visitDestinationReview.getRating();
        this.reviewCreatedAt = visitDestinationReview.getReviewCreatedAt();
    }
}
