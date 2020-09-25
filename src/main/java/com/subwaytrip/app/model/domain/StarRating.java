package com.subwaytrip.app.model.domain;

import com.subwaytrip.app.model.dto.StarRatingDTO;
import com.subwaytrip.app.utils.MapperUtils;
import lombok.Data;

import javax.persistence.*;

@Entity
@Data
@Table(name = "STAR_RATING")
@IdClass(StarRatingPK.class)
public class StarRating {

    @Id
    @Column(name = "LINE_NAME")
    private String lineName;

    @Id
    @Column(name = "STATION_NAME")
    private String stationName;

    @Column(name = "RATING_COUNT")
    private int ratingCount;

    @Column(name = "TOTAL_STAR")
    private int totalStar;

    public static StarRating of(StarRatingDTO starRatingDTO) {
        return MapperUtils.convert(starRatingDTO, StarRating.class);
    }

}
