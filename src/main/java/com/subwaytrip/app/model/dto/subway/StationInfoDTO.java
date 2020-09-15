package com.subwaytrip.app.model.dto.subway;

import com.subwaytrip.app.utils.StaticHelper;
import lombok.Data;
import org.json.simple.JSONObject;

@Data
public class StationInfoDTO {

    private int stationID;          // 정류장 ID
    private String stationName;     // 정류장 이름
    private int type;               // 노선 종류
    private String laneName;        // 노선명
    private String laneCity;        // 노선명
    private int CID;                // 정류장 도시코드
    private String cityName;        // 정류장 도시이름

    public static StationInfoDTO of(JSONObject jsonObject) {
        StationInfoDTO stationInfoDTO = new StationInfoDTO();
        stationInfoDTO.setStationID(StaticHelper.getJsonValue(jsonObject, "stationID"));
        stationInfoDTO.setStationName(StaticHelper.getJsonValue(jsonObject, "stationName", ""));
        stationInfoDTO.setType(StaticHelper.getJsonValue(jsonObject, "type"));
        stationInfoDTO.setLaneName(StaticHelper.getJsonValue(jsonObject, "laneName", ""));
        stationInfoDTO.setLaneCity(StaticHelper.getJsonValue(jsonObject, "laneCity", ""));
        stationInfoDTO.setCID(StaticHelper.getJsonValue(jsonObject, "CID"));
        stationInfoDTO.setCityName(StaticHelper.getJsonValue(jsonObject, "cityName", ""));
        return stationInfoDTO;
    }

}
