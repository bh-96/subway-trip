package com.subwaytrip.app.utils;

import com.subwaytrip.app.model.dto.subway.StationInfoRequestDTO;
import com.subwaytrip.app.model.dto.subway.StationInfoResponseDTO;
import com.subwaytrip.app.model.dto.subway.StationRootRequestDTO;
import com.subwaytrip.app.model.dto.subway.StationRootResponseDTO;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.springframework.util.ObjectUtils;

import java.util.ArrayList;
import java.util.List;

public class SubwayHelper {

    /**
     * ODSay LAB API 호출
     */
    // 역 정보 조회
    public static StationInfoResponseDTO findStationInfo(String stationName) {
        StationInfoRequestDTO stationInfoRequestDTO = new StationInfoRequestDTO();
        stationInfoRequestDTO.setStationName(stationName);
        String url = stationInfoRequestDTO.requestUrl();
        JSONObject responseJson = HttpClientUtils.getRequest(url);
        StationInfoResponseDTO stationInfoResponseDTO = StationInfoResponseDTO.of(responseJson);
        return stationInfoResponseDTO;
    }

    // 지하철 역 경로 조회
    public static StationRootResponseDTO findStationRoot(int cid, int sid, int eid, int sOpt) {
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
        return stationRootResponseDTO;
    }

    /**
     * 노선별 지하철역 세팅
     */
    public static JSONObject getSubwayStationInfo(String filePath) {
        JSONObject resultJson = new JSONObject();

        JSONObject infos = StaticHelper.fileParseToJson(filePath);
        JSONArray data = StaticHelper.getJsonArray(infos, "DATA");
        if (!ObjectUtils.isEmpty(data)) {
            for (String subwayLine : getSubwayLineList()) {
                JSONArray stationInfo = new JSONArray();

                for (Object obj : data) {
                    JSONObject station = (JSONObject) obj;
                    String lineName = StaticHelper.getJsonValue(station, "line_num", "");
                    String stationName = StaticHelper.getJsonValue(station, "station_nm", "");
                    String stationID = StaticHelper.getJsonValue(station, "fr_code", "");

                    if (subwayLine.equals(lineName)) {
                        JSONObject resultDataJson = new JSONObject();
                        resultDataJson.put("lineName", lineName);
                        resultDataJson.put("stationName", stationName);
                        resultDataJson.put("stationID", stationID);
                        stationInfo.add(resultDataJson);
                    }
                }

                resultJson.put(subwayLine, stationInfo);
            }
        }

        return resultJson;
    }

    public static List<String> getSubwayLineList() {
        List<String> lineList = new ArrayList<>();
        lineList.add("01호선");
        lineList.add("02호선");
        lineList.add("03호선");
        lineList.add("04호선");
        lineList.add("05호선");
        lineList.add("06호선");
        lineList.add("07호선");
        lineList.add("08호선");
        lineList.add("09호선");
        lineList.add("인천2호선");
        lineList.add("경의선");
        lineList.add("분당선");
        lineList.add("경춘선");
        lineList.add("경강선");
        lineList.add("수인선");
        lineList.add("인천선");
        lineList.add("공항철도");
        lineList.add("신분당선");
        lineList.add("의정부경전철");
        lineList.add("용인경전철");
        lineList.add("우이신설경전철");
        lineList.add("서해선");
        lineList.add("김포도시철도");
        return lineList;
    }

    public static List<String> getSeoulSubwayLineList() {
        List<String> lineList = new ArrayList<>();
        lineList.add("01호선");
        lineList.add("02호선");
        lineList.add("03호선");
        lineList.add("04호선");
        lineList.add("05호선");
        lineList.add("06호선");
        lineList.add("07호선");
        lineList.add("08호선");
        lineList.add("09호선");
        return lineList;
    }

}
