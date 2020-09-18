package com.subwaytrip.app.service;

import com.subwaytrip.app.constants.SubwayConstValue;
import com.subwaytrip.app.model.dto.subway.StationInfoDTO;
import com.subwaytrip.app.model.dto.subway.StationInfoResponseDTO;
import com.subwaytrip.app.utils.LoggerUtils;
import com.subwaytrip.app.utils.StaticHelper;
import com.subwaytrip.app.utils.SubwayHelper;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import java.util.List;

@Service
public class SubwayStationServiceImpl extends LoggerUtils implements SubwayStationService {

    @Override
    public int getStationID(String lineName, String stationName) {
        int stationID = 0;

        // static json 에서 조회 (1,2,3,4,5,6,7,8,9호선만)
        if (lineName.equals("")) {
            for (String subwayLine : SubwayHelper.getSeoulSubwayLineList()) {
                stationID = findStationIDBySubwayStationJson(subwayLine, stationName);
                if (stationID != 0) {
                    return stationID;
                }
            }
        } else {
            stationID = findStationIDBySubwayStationJson(lineName, stationName);
            if (stationID != 0) {
                return stationID;
            }
        }

        // 그외 노선은 ODSay Lab API 호출
        StationInfoResponseDTO responseDTO = SubwayHelper.findStationInfo(stationName);
        if (responseDTO != null && !ObjectUtils.isEmpty(responseDTO.getStationInfoList())) {
            for (StationInfoDTO infoDTO : responseDTO.getStationInfoList()) {
                return infoDTO.getStationID();
            }
        }

        return stationID;
    }

    // static json 에서 조회
    private int findStationIDBySubwayStationJson(String lineName, String stationName) {
        JSONArray dataArr = StaticHelper.getJsonArray(SubwayConstValue.subwayStations, lineName);

        if (!ObjectUtils.isEmpty(dataArr)) {
            for (Object obj : dataArr) {
                JSONObject data = (JSONObject) obj;
                if (data.containsValue(stationName)) {
                    int stationID = StaticHelper.getJsonValue(data, "stationID");
                    return stationID;
                }
            }
        }

        return 0;
    }

    @Override
    public int randomStationIDByLineName(String lineName, String startStationName) {
        // 노선 선택 안한 경우
        if (lineName.equals("")) {
            lineName = randomLineName();
        }

        JSONArray dataArr = StaticHelper.getJsonArray(SubwayConstValue.subwayStations, lineName);
        if (!ObjectUtils.isEmpty(dataArr)) {
            JSONArray exceptStartStationArr = exceptStartStationArr(startStationName, dataArr);
            int randIdx = randomIdx(exceptStartStationArr.size());
            JSONObject dataJson = (JSONObject) dataArr.get(randIdx);
            return getStationID(lineName, StaticHelper.getJsonValue(dataJson, "stationName", ""));
        }

        return 0;
    }

    // 출발지 역 제외한 JsonArray
    private JSONArray exceptStartStationArr(String startStationName, JSONArray dataArr) {
        JSONArray returnDataArr = new JSONArray();

        for (Object obj : dataArr) {
            JSONObject data = (JSONObject) obj;
            if (!data.containsValue(startStationName)) {
                returnDataArr.add(data);
            }
        }

        return returnDataArr;
    }

    // 랜덤 노선명 조회
    private String randomLineName() {
        List<String> lineList = SubwayHelper.getSubwayLineList();
        int randLine = randomIdx(lineList.size());
        return lineList.get(randLine);
    }

    private int randomIdx(int size) {
        return (int) (Math.random() * size);
    }

}
