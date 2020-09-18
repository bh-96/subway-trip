package com.subwaytrip.app;

import com.subwaytrip.app.constants.SubwayConstValue;
import com.subwaytrip.app.service.SubwayStationService;
import com.subwaytrip.app.utils.StaticHelper;
import com.subwaytrip.app.utils.SubwayHelper;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.junit.Ignore;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.util.ObjectUtils;

/**
 * 노선별 지하철 정보 파일 파싱 테스트
 */
@SpringBootTest
public class SubwayStationServiceTests {

    @Autowired
    private SubwayStationService subwayStationService;

    @Test
    @Ignore
    public void parseTest() {
        String filePath = "/Users/bh/Documents/subway-trip/src/main/resources/subway-station.json";
        JSONObject subwayStationJson = SubwayHelper.getSubwayStationInfo(filePath);
        System.out.println(subwayStationJson.toJSONString());
    }

    @Test
    @Ignore
    public void startUpTest() {
        System.out.println(SubwayConstValue.subwayStations.get("01호선"));
    }

    @Test
    @Ignore
    public void containsValueTest() {
        for (String lineName : SubwayHelper.getSeoulSubwayLineList()) {
            JSONArray dataArr = StaticHelper.getJsonArray(SubwayConstValue.subwayStations, lineName);

            if (!ObjectUtils.isEmpty(dataArr)) {
                for (Object obj : dataArr) {
                    JSONObject data = (JSONObject) obj;
                    if (data.containsValue("서울역")) {
                        System.out.println(data.toJSONString());
                    }
                }
            }
        }
    }

    @Test
    @Ignore
    public void getStationIDTest() {
        String lineName = "";
        String stationName = "이촌";
        System.out.println("lineName = " + lineName + ", stationName = " + stationName + ", stationID = " + subwayStationService.getStationID(lineName, stationName));
    }

    @Test
    @Ignore
    public void getRandomNumTest() {
        int randNum = randomIdx(2, 3);
        System.out.println(randNum);
    }

    private int randomIdx(int exceptNum, int size) {
        int randIdx = (int) (Math.random() * size) + 1;
        if (randIdx == exceptNum) {
            return randomIdx(exceptNum, size);
        } else {
            return randIdx;
        }
    }

    @Test
    @Ignore
    public void getRandomEndStationIDTest() {
        String startStationName = "당고개";
        System.out.println("startStationName = " + startStationName + ", endStationID = " + subwayStationService.randomStationIDByLineName(startStationName, ""));
    }
}
