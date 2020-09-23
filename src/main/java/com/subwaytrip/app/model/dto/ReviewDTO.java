package com.subwaytrip.app.model.dto;

import com.subwaytrip.app.model.domain.Review;
import com.subwaytrip.app.utils.MapperUtils;
import lombok.Data;
import org.modelmapper.TypeToken;

import java.util.List;

@Data
public class ReviewDTO {

    private int id;
    private UserDTO user;
    private String lineName;
    private String stationName;
    private String title;
    private String content;
    private String reviewImage;
    private int star;
    private String regDt;
    private String modDt;

    public static ReviewDTO of(Review review) {
        return MapperUtils.convert(review, ReviewDTO.class);
    }

    public static List<ReviewDTO> of(List<Review> reviews) {
        return MapperUtils.convert(reviews, new TypeToken<List<ReviewDTO>>(){}.getType());
    }

}
