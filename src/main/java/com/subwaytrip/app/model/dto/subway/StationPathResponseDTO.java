package com.subwaytrip.app.model.dto.subway;

import com.subwaytrip.app.utils.StaticHelper;
import lombok.Data;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.springframework.util.ObjectUtils;

import java.util.ArrayList;
import java.util.List;

@Data
public class StationPathResponseDTO {

    private String globalStartName;                 // 출발역 명
    private String globalEndName;                   // 도착역 명
    private int globalTravelTime;                   // 전체 운행소요시간(분)
    private int globalDistance;                     // 전체 운행거리(km)
    private int globalStationCount;                 // 전체 정차역 수
    private int fare;                               // 카드요금(성인 기준)
    private int cashFare;                           // 현금요금(성인 기준)
    private List<DriveInfoDTO> driveInfoList;       // 경로 정보 리스트
    private List<ExchangeInfoDTO> exchangeInfoList; // 환승 정보 리스트
    private List<StationPathInfoDTO> stationInfoList;   // 역 정보 리스트

    public static StationPathResponseDTO of(JSONObject responseJson) {
        JSONObject jsonObject = StaticHelper.getJsonObject(responseJson, "result");
        StationPathResponseDTO stationPathResponseDTO = new StationPathResponseDTO();
        stationPathResponseDTO.setGlobalStartName(StaticHelper.getJsonValue(jsonObject, "globalStartName", ""));
        stationPathResponseDTO.setGlobalEndName(StaticHelper.getJsonValue(jsonObject, "globalEndName", ""));
        stationPathResponseDTO.setGlobalTravelTime(StaticHelper.getJsonValue(jsonObject, "globalTravelTime"));
        stationPathResponseDTO.setGlobalStationCount(StaticHelper.getJsonValue(jsonObject, "globalStationCount"));
        stationPathResponseDTO.setFare(StaticHelper.getJsonValue(jsonObject, "fare"));
        stationPathResponseDTO.setCashFare(StaticHelper.getJsonValue(jsonObject, "cashFare"));

        JSONArray driveInfoList = StaticHelper.getJsonArray(StaticHelper.getJsonObject(jsonObject, "driveInfoSet"), "driveInfo");
        List<DriveInfoDTO> driveInfoDataList = new ArrayList<>();
        if (!ObjectUtils.isEmpty(driveInfoList)) {
            for (Object obj : driveInfoList) {
                JSONObject data = (JSONObject) obj;
                driveInfoDataList.add(DriveInfoDTO.of(data));
            }
        }
        stationPathResponseDTO.setDriveInfoList(driveInfoDataList);

        JSONArray exchangeInfoList = StaticHelper.getJsonArray(StaticHelper.getJsonObject(jsonObject, "exChangeInfoSet"), "exChangeInfo");
        List<ExchangeInfoDTO> exchangeInfoDataList = new ArrayList<>();
        if (!ObjectUtils.isEmpty(exchangeInfoList)) {
            for (Object obj : exchangeInfoList) {
                JSONObject data = (JSONObject) obj;
                exchangeInfoDataList.add(ExchangeInfoDTO.of(data));
            }
        }
        stationPathResponseDTO.setExchangeInfoList(exchangeInfoDataList);

        JSONArray stationInfoList = StaticHelper.getJsonArray(StaticHelper.getJsonObject(jsonObject, "stationSet"), "stations");
        List<StationPathInfoDTO> stationInfoDataList = new ArrayList<>();
        if (!ObjectUtils.isEmpty(stationInfoList)) {
            for (Object obj : stationInfoList) {
                JSONObject data = (JSONObject) obj;
                stationInfoDataList.add(StationPathInfoDTO.of(data));
            }
        }
        stationPathResponseDTO.setStationInfoList(stationInfoDataList);

        return stationPathResponseDTO;
    }
}
