package com.subwaytrip.app.service.impl;

import com.subwaytrip.app.model.domain.StarRating;
import com.subwaytrip.app.model.dto.StarRatingDTO;
import com.subwaytrip.app.model.repository.StarRatingRepository;
import com.subwaytrip.app.service.StarRatingService;
import com.subwaytrip.app.utils.LoggerUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class StarRatingServiceImpl extends LoggerUtils implements StarRatingService {

    private StarRatingRepository starRatingRepository;

    @Autowired
    public StarRatingServiceImpl(StarRatingRepository starRatingRepository) {
        this.starRatingRepository = starRatingRepository;
    }

    private StarRatingDTO getStarRating(String lineName, String stationName) {
        return StarRatingDTO.of(starRatingRepository.findByLineNameAndStationName(lineName, stationName));
    }

    @Override
    public boolean saveStarRating(StarRatingDTO starRatingDTO, int star) {
        StarRatingDTO savedStarRating = getStarRating(starRatingDTO.getLineName(), starRatingDTO.getStationName());

        if (savedStarRating == null) {
            starRatingDTO.setRatingCount(1);
            starRatingDTO.setTotalStar(star);
            starRatingDTO.setAvgStar(getAvgStarRating(starRatingDTO.getRatingCount(), star));

            try {
                starRatingRepository.save(StarRating.of(starRatingDTO));
                return true;
            } catch (Exception e) {
                logger.error("saveStarRating", e);
            }
        } else {
            try {
                starRatingRepository.updateStarRating(savedStarRating.getLineName(), savedStarRating.getStationName(), savedStarRating.getRatingCount() + 1,
                        savedStarRating.getTotalStar() + star, getAvgStarRating(savedStarRating.getRatingCount() + 1, savedStarRating.getTotalStar() + star));
                return true;
            } catch (Exception e) {
                logger.error("updateStarRating", e);
            }
        }
        return false;
    }

    @Override
    public double avgStarRating(String lineName, String stationName) {
        StarRatingDTO starRating = getStarRating(lineName, stationName);
        if (starRating != null) {
            return getAvgStarRating(starRating.getRatingCount(), starRating.getTotalStar());
        }
        return 0;
    }

    private double getAvgStarRating(int ratingCount, int totalStar) {
        int total = ratingCount * 5;
        double avg = (double) totalStar / (double) total;
        return Math.round(avg * 10) / 10.0 * 5;
    }

    @Override
    public boolean updateStarRating(StarRatingDTO starRatingDTO, int oldStar, int newStar) {
        StarRatingDTO savedStarRating = getStarRating(starRatingDTO.getLineName(), starRatingDTO.getStationName());

        if (savedStarRating == null) {
            return false;
        } else {
            try {
                starRatingRepository.updateStarRating(savedStarRating.getLineName(), savedStarRating.getStationName(), savedStarRating.getRatingCount(), savedStarRating.getTotalStar() - oldStar + newStar, getAvgStarRating(savedStarRating.getRatingCount(), savedStarRating.getTotalStar() - oldStar + newStar));
                return true;
            } catch (Exception e) {
                logger.error("updateStarRating", e);
            }
        }
        return false;
    }
}
