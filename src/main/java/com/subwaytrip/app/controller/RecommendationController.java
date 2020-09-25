package com.subwaytrip.app.controller;

import com.subwaytrip.app.model.dto.FilterDTO;
import com.subwaytrip.app.service.RecommendationService;
import com.subwaytrip.app.utils.LoggerUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/recommendation")
public class RecommendationController extends LoggerUtils {

    private RecommendationService recommendationService;

    @Autowired
    public RecommendationController(RecommendationService recommendationService) {
        this.recommendationService = recommendationService;
    }

    @GetMapping
    public ResponseEntity<?> getRecommendationList(@RequestParam(defaultValue = "0") int type, @RequestParam(defaultValue = "") String lineName) {
        FilterDTO filterDTO = new FilterDTO();
        filterDTO.setType(type);
        filterDTO.setLineName(lineName);

        if (type == 1) {
            return new ResponseEntity<>(recommendationService.getRecommendationListAboutStarRating(filterDTO), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(recommendationService.getRecommendationListAboutReviewCount(filterDTO), HttpStatus.OK);
        }
    }

}
