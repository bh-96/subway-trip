package com.subwaytrip.app.service;

import com.subwaytrip.app.model.dto.StarRatingDTO;

public interface StarRatingService {

    boolean saveStarRating(StarRatingDTO starRatingDTO, int star);

    double avgStarRating(String lineName, String stationName);

    boolean updateStarRating(StarRatingDTO starRatingDTO, int oldStar, int newStar);

}
