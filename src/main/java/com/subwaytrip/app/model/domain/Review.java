package com.subwaytrip.app.model.domain;

import com.subwaytrip.app.model.dto.ReviewDTO;
import com.subwaytrip.app.utils.MapperUtils;
import lombok.Data;

import javax.persistence.*;

@Entity
@Data
@Table(name = "REVIEW")
public class Review {

    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @OneToOne
    @JoinColumn(name = "USER_ID")
    private User user;

    @Column(name = "LINE_NAME")
    private String lineName;

    @Column(name = "STATION_NAME")
    private String stationName;

    @Column(name = "TITLE")
    private String title;

    @Column(name = "CONTENT")
    private String content;

    @Column(name = "REVIEW_IMAGE")
    private String reviewImage;

    @Column(name = "STAR")
    private int star;

    @Column(name = "REG_DT")
    private String regDt;

    @Column(name = "MOD_DT")
    private String modDt;

    public static Review of(ReviewDTO reviewDTO) {
        return MapperUtils.convert(reviewDTO, Review.class);
    }

}
