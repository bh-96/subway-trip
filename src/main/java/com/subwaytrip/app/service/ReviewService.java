package com.subwaytrip.app.service;

import com.subwaytrip.app.model.dto.ReviewDTO;

import java.util.List;

public interface ReviewService {

    // 리뷰 저장
    ReviewDTO saveReview(int userId, ReviewDTO reviewDTO);

    // 리뷰 수정
    ReviewDTO updateReview(int userId, ReviewDTO reviewDTO);

    // 리뷰 삭제
    boolean deleteReview(int userId, int reviewId);

    // 리뷰 조회 (단건)
    ReviewDTO getReview(int id);

    // 지하철역명으로 리뷰 리스트 조회
    List<ReviewDTO> getReviewListByStationName(String stationName, String sortParam);

    // 사용자별 리뷰 리스트 조회
    List<ReviewDTO> getReviewListByUserId(int userId, String sortParam);

    // 리뷰 이미지 파일명 조회
    String getReviewFileInfo(int id);

}
