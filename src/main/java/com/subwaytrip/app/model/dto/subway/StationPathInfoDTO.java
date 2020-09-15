package com.subwaytrip.app.model.dto.subway;

import com.subwaytrip.app.utils.StaticHelper;
import lombok.Data;
import org.json.simple.JSONObject;

@Data
public class StationPathInfoDTO {

    private String startName;
    private String endName;
    private int startID;
    private int endSID;
    private int travelTime;

    public static StationPathInfoDTO of(JSONObject stationPathInfoJson) {
        StationPathInfoDTO stationPathInfoDTO = new StationPathInfoDTO();
        stationPathInfoDTO.setStartName(StaticHelper.getJsonValue(stationPathInfoJson, "startName", ""));
        stationPathInfoDTO.setEndName(StaticHelper.getJsonValue(stationPathInfoJson, "endName", ""));
        stationPathInfoDTO.setStartID(StaticHelper.getJsonValue(stationPathInfoJson, "startID"));
        stationPathInfoDTO.setEndSID(StaticHelper.getJsonValue(stationPathInfoJson, "endSID"));
        stationPathInfoDTO.setTravelTime(StaticHelper.getJsonValue(stationPathInfoJson, "travelTime"));
        return stationPathInfoDTO;
    }

}
