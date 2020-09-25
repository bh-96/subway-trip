package com.subwaytrip.app.model.repository;

import com.subwaytrip.app.model.domain.StarRating;
import com.subwaytrip.app.model.domain.StarRatingPK;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

public interface StarRatingRepository extends JpaRepository<StarRating, StarRatingPK> {

    StarRating findByLineNameAndStationName(String lineName, String stationName);

    @Transactional
    @Modifying
    @Query(value = "UPDATE STAR_RATING s SET s.RATING_COUNT = :reviewCount, s.TOTAL_STAR = :totalStar WHERE s.LINE_NAME = :lineName AND s.STATION_NAME = :stationName ", nativeQuery = true)
    void updateStarRating(@Param("lineName") String lineName, @Param("stationName") String stationName, @Param("reviewCount") int reviewCount, @Param("totalStar") int totalStar);

}
