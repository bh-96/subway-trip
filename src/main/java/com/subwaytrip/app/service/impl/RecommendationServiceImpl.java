package com.subwaytrip.app.service.impl;

import com.subwaytrip.app.model.dto.FilterDTO;
import com.subwaytrip.app.model.dto.RecommendationDTO;
import com.subwaytrip.app.model.repository.RecommendationRepository;
import com.subwaytrip.app.service.RecommendationService;
import com.subwaytrip.app.utils.LoggerUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RecommendationServiceImpl extends LoggerUtils implements RecommendationService {

    private RecommendationRepository recommendationRepository;

    @Autowired
    public RecommendationServiceImpl(RecommendationRepository recommendationRepository) {
        this.recommendationRepository = recommendationRepository;
    }

    @Override
    public List<RecommendationDTO.AboutReviewCount> getRecommendationListAboutReviewCount(FilterDTO filterDTO) {
        if (filterDTO.getLineName().equals("")) {
            return RecommendationDTO.of(recommendationRepository.getRecommendationListAboutReviewCount());
        } else {
            return RecommendationDTO.of(recommendationRepository.getRecommendationListAboutReviewCountByLineName(filterDTO.getLineName()));
        }
    }

    @Override
    public List<RecommendationDTO.AboutStarRating> getRecommendationListAboutStarRating(FilterDTO filterDTO) {
        if (filterDTO.getLineName().equals("")) {
            return RecommendationDTO.convertOf(recommendationRepository.getRecommendationListAboutStarRating());
        } else {
            return RecommendationDTO.convertOf(recommendationRepository.getRecommendationListAboutStarRatingByLineName(filterDTO.getLineName()));
        }
    }
}
