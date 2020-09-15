package com.subwaytrip.app.model.dto.subway;

import com.subwaytrip.app.utils.StaticHelper;
import lombok.Data;
import org.json.simple.JSONObject;

@Data
public class DriveInfoDTO {

    private String laneID;              // 승차역 ID
    private String laneName;            // 승차역 호선명
    private String startName;           // 승차 역명
    private int stationCount;           // 이동 역 수
    private int wayCode;                // 방면코드 (1: 상행, 2: 하행)
    private String wayName;             // 방면 명

    public static DriveInfoDTO of(JSONObject driveInfoJson) {
        DriveInfoDTO driveInfoDTO = new DriveInfoDTO();
        driveInfoDTO.setLaneID(StaticHelper.getJsonValue(driveInfoJson, "laneID", ""));
        driveInfoDTO.setLaneName(StaticHelper.getJsonValue(driveInfoJson, "laneName", ""));
        driveInfoDTO.setStartName(StaticHelper.getJsonValue(driveInfoJson, "startName", ""));
        driveInfoDTO.setStationCount(StaticHelper.getJsonValue(driveInfoJson, "stationCount"));
        driveInfoDTO.setWayCode(StaticHelper.getJsonValue(driveInfoJson, "wayCode"));
        driveInfoDTO.setWayName(StaticHelper.getJsonValue(driveInfoJson, "wayName", ""));
        return driveInfoDTO;
    }

}
