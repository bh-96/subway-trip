package com.subwaytrip.app.service.impl;

import com.subwaytrip.app.model.domain.Review;
import com.subwaytrip.app.model.dto.ReviewDTO;
import com.subwaytrip.app.model.dto.StarRatingDTO;
import com.subwaytrip.app.model.dto.UserDTO;
import com.subwaytrip.app.model.repository.ReviewRepository;
import com.subwaytrip.app.service.FileService;
import com.subwaytrip.app.service.ReviewService;
import com.subwaytrip.app.service.StarRatingService;
import com.subwaytrip.app.service.UserService;
import com.subwaytrip.app.utils.LoggerUtils;
import com.subwaytrip.app.utils.StaticHelper;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class ReviewServiceImpl extends LoggerUtils implements ReviewService {

    private ReviewRepository reviewRepository;
    private UserService userService;
    private FileService fileService;
    private StarRatingService starRatingService;

    @Autowired
    public ReviewServiceImpl(ReviewRepository reviewRepository, UserService userService, FileService fileService, StarRatingService starRatingService) {
        this.reviewRepository = reviewRepository;
        this.userService = userService;
        this.fileService = fileService;
        this.starRatingService = starRatingService;
    }

    @Override
    public ReviewDTO saveReview(int userId, ReviewDTO reviewDTO) {
        UserDTO user = userService.getUser(userId);
        reviewDTO.setUser(user);
        reviewDTO.setRegDt(StaticHelper.getFormatDateTime("yyyyMMdd HHmm", new Date()));
        Review review = Review.of(reviewDTO);

        // 별점 저장
        StarRatingDTO starRating = new StarRatingDTO();
        starRating.setLineName(reviewDTO.getLineName());
        starRating.setStationName(reviewDTO.getStationName());
        starRatingService.saveStarRating(starRating, reviewDTO.getStar());

        return ReviewDTO.of(reviewRepository.save(review));
    }

    @Override
    public ReviewDTO updateReview(int userId, ReviewDTO reviewDTO) {
        ReviewDTO savedReview = getReview(reviewDTO.getId());

        if (savedReview != null) {
            int savedUserId = savedReview.getUser().getId();
            if (savedUserId == userId) {
                if (!StringUtils.isEmpty(reviewDTO.getLineName()) && !savedReview.getLineName().equals(reviewDTO.getLineName())) {
                    savedReview.setLineName(reviewDTO.getLineName());
                }

                if (!StringUtils.isEmpty(reviewDTO.getStationName()) && !savedReview.getStationName().equals(reviewDTO.getStationName())) {
                    savedReview.setStationName(reviewDTO.getStationName());
                }

                if (!StringUtils.isEmpty(reviewDTO.getTitle()) && !savedReview.getTitle().equals(reviewDTO.getTitle())) {
                    savedReview.setTitle(reviewDTO.getTitle());
                }

                if (!StringUtils.isEmpty(reviewDTO.getContent()) && !savedReview.getContent().equals(reviewDTO.getContent())) {
                    savedReview.setContent(reviewDTO.getContent());
                }

                if (!StringUtils.isEmpty(reviewDTO.getReviewImage()) && !savedReview.getReviewImage().equals(reviewDTO.getReviewImage())) {
                    savedReview.setReviewImage(reviewDTO.getReviewImage());
                }

                if (reviewDTO.getStar() != 0 && reviewDTO.getStar() != savedReview.getStar()) {
                    StarRatingDTO starRating = new StarRatingDTO();
                    starRating.setLineName(reviewDTO.getLineName());
                    starRating.setStationName(reviewDTO.getStationName());
                    starRatingService.updateStarRating(starRating, savedReview.getStar(), reviewDTO.getStar());
                    savedReview.setStar(reviewDTO.getStar());
                }

                savedReview.setModDt(StaticHelper.getFormatDateTime("yyyyMMdd HHmm", new Date()));
                return ReviewDTO.of(reviewRepository.save(Review.of(savedReview)));
            } else {
                logger.error("Access Denied.");
            }
        }

        return null;
    }

    @Override
    public boolean deleteReview(int userId, int reviewId) {
        ReviewDTO reviewDTO = getReview(reviewId);

        if (reviewDTO != null) {
            int savedUserId = reviewDTO.getUser().getId();
            if (userId == savedUserId) {
                try {
                    reviewRepository.deleteById(reviewId);
                    return fileService.deleteFile(reviewDTO.getReviewImage());
                } catch (Exception e) {
                    e.printStackTrace();
                    logger.error("deleteReview", e);
                }
            } else {
                logger.error("Access Denied.");
            }
        }

        return false;
    }

    @Override
    public ReviewDTO getReview(int id) {
        return ReviewDTO.of(reviewRepository.findById(id));
    }

    @Override
    public List<ReviewDTO> getReviewListByStationName(String stationName, String sortParam) {
        if (sortParam.equalsIgnoreCase("star")) {
            return ReviewDTO.of(reviewRepository.findAllByStationNameOrderByStarDescIdDesc(stationName));
        }
        return ReviewDTO.of(reviewRepository.findAllByStationNameOrderByIdDesc(stationName));
    }

    @Override
    public List<ReviewDTO> getReviewListByUserId(int userId, String sortParam) {
        if (sortParam.equalsIgnoreCase("star")) {
            return ReviewDTO.of(reviewRepository.findAllByUserIdOrderByStarDescIdDesc(userId));
        }
        return ReviewDTO.of(reviewRepository.findAllByUserIdOrderByIdDesc(userId));
    }

    @Override
    public String getReviewFileInfo(int id) {
        ReviewDTO reviewDTO = getReview(id);
        return reviewDTO.getReviewImage() != null ? reviewDTO.getReviewImage() : "";
    }
}
