package com.subwaytrip.app.model.dto;

import com.subwaytrip.app.model.domain.Recommendation;
import com.subwaytrip.app.utils.MapperUtils;
import lombok.Data;
import org.modelmapper.TypeToken;

import java.util.List;

public class RecommendationDTO {

    @Data
    public static class AboutReviewCount {
        private String lineName;
        private String stationName;
        private int count;
    }

    @Data
    public static class AboutStarRating {
        private String lineName;
        private String stationName;
        private double avgStar;
    }

    public static List<AboutReviewCount> of(List<Recommendation> recommendation) {
        return MapperUtils.convert(recommendation, new TypeToken<List<AboutReviewCount>>(){}.getType());
    }

    public static List<AboutStarRating> convertOf(List<Recommendation> recommendation) {
        return MapperUtils.convert(recommendation, new TypeToken<List<AboutStarRating>>(){}.getType());
    }

}
