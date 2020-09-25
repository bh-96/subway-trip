package com.subwaytrip.app.service;

import com.subwaytrip.app.model.dto.FilterDTO;
import com.subwaytrip.app.model.dto.RecommendationDTO;

import java.util.List;

/**
 * 지하철역 추천
 */
public interface RecommendationService {

    // 추천 지하철역 리스트 조회
    List<RecommendationDTO.AboutReviewCount> getRecommendationListAboutReviewCount(FilterDTO filterDTO);

    List<RecommendationDTO.AboutStarRating> getRecommendationListAboutStarRating(FilterDTO filterDTO);

}
