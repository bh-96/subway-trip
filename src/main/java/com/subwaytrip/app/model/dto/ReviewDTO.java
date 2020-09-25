package com.subwaytrip.app.model.dto;

import com.subwaytrip.app.model.domain.Review;
import com.subwaytrip.app.utils.MapperUtils;
import lombok.Data;
import org.modelmapper.TypeToken;

import javax.validation.constraints.Max;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.List;

@Data
public class ReviewDTO {

    private int id;
    private UserDTO user;

    @NotNull
    @NotEmpty
    private String lineName;

    @NotNull
    @NotEmpty
    private String stationName;

    @NotNull
    @NotEmpty
    private String title;

    @NotNull
    @NotEmpty
    private String content;

    private String reviewImage;

    @Max(5)
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
