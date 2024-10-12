package com.example.visitdestinationreview.domain;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import java.time.LocalDateTime;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@NoArgsConstructor
@Data
@Entity
@JsonSerialize
@JsonDeserialize
@Table(name = "visit_destination_reviews")
public class VisitDestinationReview {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;
    
    @Column(nullable = false)
    private String destinationName;
    
    @Column(nullable = false)
    private String destinationDescription;

    @Embedded
    private Coordinates coordinates;
    
    @Column(nullable = false)
    private LocalDateTime visitDate;
    
    @Column
    private String reviewContent;
    
    @Column
    private Integer rating;
    
    @Column
    private LocalDateTime reviewCreatedAt;
}

