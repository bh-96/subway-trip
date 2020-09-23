package com.subwaytrip.app.model.domain;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Data
@Table(name = "STAR_RATING")
public class StarRating {

    @Id
    @Column(name = "REVIEW_ID")
    private int reviewId;

    @Column(name = "RATING_COUNT")
    private int ratingCount;

    @Column(name = "TOTAL_STAR")
    private int totalStar;

}
