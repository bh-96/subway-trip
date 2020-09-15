package com.subwaytrip.app.linker;

import com.subwaytrip.app.model.dto.subway.StationInfoRequestDTO;
import com.subwaytrip.app.model.dto.subway.StationInfoResponseDTO;
import com.subwaytrip.app.model.dto.subway.StationPathRequestDTO;
import com.subwaytrip.app.model.dto.subway.StationPathResponseDTO;
import com.subwaytrip.app.utils.HttpClientUtils;
import com.subwaytrip.app.utils.LoggerUtils;
import org.json.simple.JSONObject;
import org.springframework.stereotype.Component;

/**
 * ODSay LAB API 호출
 */
@Component
public class SubwayLinker extends LoggerUtils {

    // 역 정보 조회
    public StationInfoResponseDTO findStationInfo(String stationName) {
        StationInfoRequestDTO stationInfoRequestDTO = new StationInfoRequestDTO();
        stationInfoRequestDTO.setStationName(stationName);

        String url = stationInfoRequestDTO.requestUrl();
        logger.info("REQUEST : " + url);
        JSONObject responseJson = HttpClientUtils.getRequest(url);
        logger.info("RESPONSE : " + responseJson.toJSONString());

        StationInfoResponseDTO stationInfoResponseDTO = StationInfoResponseDTO.of(responseJson);
        return stationInfoResponseDTO;
    }

    // 지하철 역 경로 조회
    public StationPathResponseDTO findStationPath(int cid, int sid, int eid, int sOpt) {
        StationPathRequestDTO stationPathRequestDTO = new StationPathRequestDTO();
        stationPathRequestDTO.setCID(cid);
        stationPathRequestDTO.setSID(sid);
        stationPathRequestDTO.setEID(eid);
        stationPathRequestDTO.setSopt(sOpt);

        String url = stationPathRequestDTO.requestUrl();
        logger.info("REQUEST : " + url);
        JSONObject responseJson = HttpClientUtils.getRequest(url);
        logger.info("RESPONSE : " + responseJson.toJSONString());

        StationPathResponseDTO stationPathResponseDTO = StationPathResponseDTO.of(responseJson);
        return stationPathResponseDTO;
    }

}
