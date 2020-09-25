package com.subwaytrip.app.model.dto;

import lombok.Data;

@Data
public class FilterDTO {

    private int type;           // 필터 타입 (0 : 후기 많은 순, 1 : 별점 순)
    private String lineName;    // 노선명

}
