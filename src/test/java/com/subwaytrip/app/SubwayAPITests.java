package com.subwaytrip.app;

import com.subwaytrip.app.model.dto.subway.StationInfoRequestDTO;
import com.subwaytrip.app.model.dto.subway.StationInfoResponseDTO;
import com.subwaytrip.app.model.dto.subway.StationPathRequestDTO;
import com.subwaytrip.app.model.dto.subway.StationPathResponseDTO;
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
        int sid = 148;
        int eid = 409;

        StationPathRequestDTO stationPathRequestDTO = new StationPathRequestDTO();
        stationPathRequestDTO.setCID(cid);
        stationPathRequestDTO.setSID(sid);
        stationPathRequestDTO.setEID(eid);

        String url = stationPathRequestDTO.requestUrl();
        System.out.println("REQUEST : " + url);
        JSONObject responseJson = HttpClientUtils.getRequest(url);
        System.out.println("RESPONSE : " + responseJson.toJSONString());

        StationPathResponseDTO stationPathResponseDTO = StationPathResponseDTO.of(responseJson);
        System.out.println(stationPathResponseDTO.toString());
    }

}
