package com.subwaytrip.app;

import com.subwaytrip.app.model.dto.subway.StationInfoRequestDTO;
import com.subwaytrip.app.model.dto.subway.StationInfoResponseDTO;
import com.subwaytrip.app.model.dto.subway.StationRootRequestDTO;
import com.subwaytrip.app.model.dto.subway.StationRootResponseDTO;
import com.subwaytrip.app.utils.HttpClientUtils;
import org.json.simple.JSONObject;
import org.junit.Ignore;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class SubwayAPITests {

    @Test
    @Ignore
    public void findStationInfoTest() throws Exception {
        String stationName = "부천";
        StationInfoRequestDTO stationInfoRequestDTO = new StationInfoRequestDTO();
        stationInfoRequestDTO.setStationName(stationName);

        String url = stationInfoRequestDTO.requestUrl();
        System.out.println("REQUEST : " + url);
        JSONObject responseJson = HttpClientUtils.getRequest(url);
        System.out.println("RESPONSE : " + responseJson.toJSONString());

        StationInfoResponseDTO stationInfoResponseDTO = StationInfoResponseDTO.of(responseJson);
        System.out.println(stationInfoResponseDTO.toString());
    }

    @Test
    @Ignore
    public void findStationPathTest() throws Exception {
        int cid = 1000;
        int sid = 409;
        int eid = 759;

        StationRootRequestDTO stationRootRequestDTO = new StationRootRequestDTO();
        stationRootRequestDTO.setCID(cid);
        stationRootRequestDTO.setSID(sid);
        stationRootRequestDTO.setEID(eid);

        String url = stationRootRequestDTO.requestUrl();
        System.out.println("REQUEST : " + url);
        JSONObject responseJson = HttpClientUtils.getRequest(url);
        System.out.println("RESPONSE : " + responseJson.toJSONString());

        StationRootResponseDTO stationRootResponseDTO = StationRootResponseDTO.of(responseJson);
        System.out.println(stationRootResponseDTO.toString());
    }

}
