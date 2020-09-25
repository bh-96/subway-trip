package com.subwaytrip.app.model.domain;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;

@Entity
@Data
@IdClass(RecommendationPK.class)
public class Recommendation {

    @Id
    @Column(name = "LINE_NAME")
    private String lineName;

    @Id
    @Column(name = "STATION_NAME")
    private String stationName;

    @Column(name = "COUNT")
    private int count;

    @Column(name = "AVG_STAR")
    private double avgStar;

}
