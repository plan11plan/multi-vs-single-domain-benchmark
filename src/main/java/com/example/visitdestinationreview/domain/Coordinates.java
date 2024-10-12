package com.example.visitdestinationreview.domain;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import jakarta.persistence.Embeddable;
import lombok.Data;

@Embeddable
@JsonSerialize
@JsonDeserialize
@Data
public class Coordinates {
    private Double latitude;
    private Double longitude;
}

