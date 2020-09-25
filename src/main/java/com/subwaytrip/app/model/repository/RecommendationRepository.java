package com.subwaytrip.app.model.repository;

import com.subwaytrip.app.model.domain.Recommendation;
import com.subwaytrip.app.model.domain.RecommendationPK;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface RecommendationRepository extends JpaRepository<Recommendation, RecommendationPK> {

    @Transactional
    @Query(value = "SELECT r.LINE_NAME, r.STATION_NAME, COUNT(*) AS COUNT, 0 AS AVG_STAR FROM REVIEW r GROUP BY r.LINE_NAME, r.STATION_NAME ORDER BY COUNT DESC ", nativeQuery = true)
    List<Recommendation> getRecommendationListAboutReviewCount();

    @Transactional
    @Query(value = "SELECT s.LINE_NAME, s.STATION_NAME, s.AVG_STAR, 0 AS COUNT FROM STAR_RATING s ORDER BY s.AVG_STAR DESC ", nativeQuery = true)
    List<Recommendation> getRecommendationListAboutStarRating();

    @Transactional
    @Query(value = "SELECT r.LINE_NAME, r.STATION_NAME, COUNT(*) AS COUNT, 0 AS AVG_STAR, r.LINE_NAME = :lineName FROM REVIEW r GROUP BY r.LINE_NAME, r.STATION_NAME ORDER BY COUNT DESC ", nativeQuery = true)
    List<Recommendation> getRecommendationListAboutReviewCountByLineName(@Param("lineName") String lineName);

    @Transactional
    @Query(value = "SELECT s.LINE_NAME, s.STATION_NAME, s.AVG_STAR, 0 AS COUNT, r.LINE_NAME = :lineName FROM STAR_RATING s ORDER BY s.AVG_STAR DESC ", nativeQuery = true)
    List<Recommendation> getRecommendationListAboutStarRatingByLineName(@Param("lineName") String lineName);

}
