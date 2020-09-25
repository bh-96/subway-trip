package com.subwaytrip.app;

import com.subwaytrip.app.model.dto.FilterDTO;
import com.subwaytrip.app.model.dto.RecommendationDTO;
import com.subwaytrip.app.service.RecommendationService;
import org.junit.Ignore;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
public class ReviewServiceTests {

    @Autowired
    private RecommendationService recommendationService;

    @Test
    @Ignore
    public void recommendationTest() {
        FilterDTO filterDTO = new FilterDTO();
        filterDTO.setType(0);
        filterDTO.setLineName("");
        List<RecommendationDTO.AboutReviewCount> reviewCounts = recommendationService.getRecommendationListAboutReviewCount(filterDTO);
        for (RecommendationDTO.AboutReviewCount reviewCount : reviewCounts) {
            System.out.println(reviewCount.toString());
        }

        List<RecommendationDTO.AboutStarRating> starRatings = recommendationService.getRecommendationListAboutStarRating(filterDTO);
        for (RecommendationDTO.AboutStarRating starRating : starRatings) {
            System.out.println(starRating.toString());
        }
    }

    @Test
    @Ignore
    public void subStrTest() {
        String stationName = "부천";
//        stationName = "역곡";
        if (!stationName.equals("서울역") && stationName.substring(stationName.length() - 1).contains("역")) {
            System.out.println(stationName.substring(0, stationName.length() - 1));
        }
        System.out.println(stationName);
    }

}
