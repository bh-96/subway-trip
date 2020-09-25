package com.subwaytrip.app.model.dto;

import com.subwaytrip.app.model.domain.StarRating;
import com.subwaytrip.app.utils.MapperUtils;
import lombok.Data;

@Data
public class StarRatingDTO {

    private String lineName;
    private String stationName;
    private int ratingCount;
    private int totalStar;

    public static StarRatingDTO of(StarRating starRating) {
        return MapperUtils.convert(starRating, StarRatingDTO.class);
    }

}
