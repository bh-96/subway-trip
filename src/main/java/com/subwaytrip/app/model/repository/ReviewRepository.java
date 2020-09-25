package com.subwaytrip.app.model.repository;

import com.subwaytrip.app.model.domain.Review;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ReviewRepository extends JpaRepository<Review, Integer> {

    Review findById(int id);

    List<Review> findAllByStationNameOrderByStarDescIdDesc(String stationName);

    List<Review> findAllByStationNameOrderByIdDesc(String stationName);

    List<Review> findAllByUserIdOrderByStarDescIdDesc(int userId);

    List<Review> findAllByUserIdOrderByIdDesc(int userId);

}
