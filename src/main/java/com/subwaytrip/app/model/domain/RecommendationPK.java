package com.subwaytrip.app.model.domain;

import lombok.Data;

import javax.persistence.Column;
import java.io.Serializable;

@Data
public class RecommendationPK implements Serializable {

    @Column(name = "LINE_NAME")
    private String lineName;

    @Column(name = "STATION_NAME")
    private String stationName;

}
