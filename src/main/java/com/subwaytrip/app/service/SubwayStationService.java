package com.subwaytrip.app.service;

public interface SubwayStationService {

    // 지하철역 이름으로 stationID 조회
    int getStationID(String lineName, String stationName);

    // 노선별 랜덤으로 지하철역 선택 (stationID)
    int randomStationIDByLineName(String lineName, String startStationName);

}
