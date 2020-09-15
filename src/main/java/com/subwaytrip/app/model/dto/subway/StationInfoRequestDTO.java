package com.subwaytrip.app.model.dto.subway;

import com.subwaytrip.app.constants.SubwayConstValue;
import lombok.Data;

@Data
public class StationInfoRequestDTO {

    private String stationName;

    public String requestUrl() {
        // stationClass = 2 : 정류장 종류 = 지하철역
        return SubwayConstValue.FIND_STATION_ID_URI + "?apiKey=" + SubwayConstValue.API_KEY + "&stationName=" + stationName + "&stationClass=2";
    }

}
