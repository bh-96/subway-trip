package com.subwaytrip.app.model.dto.subway;

import com.subwaytrip.app.constants.SubwayConstValue;
import lombok.Data;

@Data
public class StationRootRequestDTO {

    private int CID;        // 도시코드
    private int SID;        // 출발역 코드
    private int EID;        // 도착역 코드
    private int Sopt;       // 경로검색 조건 (default = 1, 1: 최단거리, 2: 최소환승)

    public String requestUrl() {
        if (Sopt == 0) {
            return SubwayConstValue.FIND_STATION_PATH_URI + "?apiKey=" + SubwayConstValue.API_KEY + "&CID=" + CID + "&SID=" + SID + "&EID=" + EID;
        } else {
            return SubwayConstValue.FIND_STATION_PATH_URI + "?apiKey=" + SubwayConstValue.API_KEY + "&CID=" + CID + "&SID=" + SID + "&EID=" + EID + "Sopt=" + Sopt;
        }
    }

}
