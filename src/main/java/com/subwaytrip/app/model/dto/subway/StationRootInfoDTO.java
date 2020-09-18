package com.subwaytrip.app.model.dto.subway;

import com.subwaytrip.app.utils.StaticHelper;
import lombok.Data;
import org.json.simple.JSONObject;

@Data
public class StationRootInfoDTO {

    private String startName;
    private String endName;
    private int startID;
    private int endSID;
    private int travelTime;

    public static StationRootInfoDTO of(JSONObject stationPathInfoJson) {
        StationRootInfoDTO stationRootInfoDTO = new StationRootInfoDTO();
        stationRootInfoDTO.setStartName(StaticHelper.getJsonValue(stationPathInfoJson, "startName", ""));
        stationRootInfoDTO.setEndName(StaticHelper.getJsonValue(stationPathInfoJson, "endName", ""));
        stationRootInfoDTO.setStartID(StaticHelper.getJsonValue(stationPathInfoJson, "startID"));
        stationRootInfoDTO.setEndSID(StaticHelper.getJsonValue(stationPathInfoJson, "endSID"));
        stationRootInfoDTO.setTravelTime(StaticHelper.getJsonValue(stationPathInfoJson, "travelTime"));
        return stationRootInfoDTO;
    }

}
