package com.subwaytrip.app.model.dto.subway;

import com.subwaytrip.app.utils.StaticHelper;
import lombok.Data;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.springframework.util.ObjectUtils;

import java.util.ArrayList;
import java.util.List;

@Data
public class StationInfoResponseDTO {

    private int totalCount;         // 검색 결과 개수
    private int CID;                // 도시코드
    private String cityName;        // 도시명
    private List<StationInfoDTO> stationInfoList;   // 정류장 정보 리스트

    public static StationInfoResponseDTO of(JSONObject responseJson) {
        JSONObject jsonObject = StaticHelper.getJsonObject(responseJson, "result");
        int totalCount = StaticHelper.getJsonValue(jsonObject, "totalCount");

        if (totalCount <= 0) {
            return null;
        }

        StationInfoResponseDTO stationInfoResponseDTO = new StationInfoResponseDTO();
        stationInfoResponseDTO.setTotalCount(totalCount);

        // 도시 정보
        int CID = 0;
        String cityName = "";
        JSONArray cityInfoArr = StaticHelper.getJsonArray(StaticHelper.getJsonObject(jsonObject, "totalCityList"), "includeCity");
        if (!ObjectUtils.isEmpty(cityInfoArr)) {
            JSONObject firstCityInfo = (JSONObject) cityInfoArr.get(0);
            CID = StaticHelper.getJsonValue(firstCityInfo, "CID");
            cityName = StaticHelper.getJsonValue(firstCityInfo, "cityName", "");
        }
        stationInfoResponseDTO.setCID(CID);
        stationInfoResponseDTO.setCityName(cityName);

        // 역 정보
        JSONArray stationInfoArr = StaticHelper.getJsonArray(jsonObject, "station");
        if (!ObjectUtils.isEmpty(stationInfoArr)) {
            List<StationInfoDTO> stationInfoList = new ArrayList<>();
            for (Object obj : stationInfoArr) {
                JSONObject stationInfo = (JSONObject) obj;
                StationInfoDTO stationInfoDTO = StationInfoDTO.of(stationInfo);
                stationInfoList.add(stationInfoDTO);
            }
            stationInfoResponseDTO.setStationInfoList(stationInfoList);
        }

        return stationInfoResponseDTO;
    }

}
