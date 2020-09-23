package com.subwaytrip.app.model.repository;

import com.subwaytrip.app.model.domain.Review;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReviewRepository extends JpaRepository<Review, Integer> {
}
