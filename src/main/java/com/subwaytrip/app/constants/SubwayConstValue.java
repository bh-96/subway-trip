package com.subwaytrip.app.constants;

import java.util.HashMap;
import java.util.Map;

public class SubwayConstValue {

    public final static String API_KEY                  = "9HIPmcFUCBvo64Sg3OCFx4RHGGjzRbH%2BknqfMkZ%2BGmM";

    // 대중교통 정류장 조회 API
    public final static String FIND_STATION_ID_URI      = "https://api.odsay.com/v1/api/searchStation";

    // 지하철 경로검색 조회 API
    public final static String FIND_STATION_PATH_URI    = "https://api.odsay.com/v1/api/subwayPath";

    // Map<stationName, stationID>
    public static Map<String, Integer> stationInfoMap = new HashMap<>();

}
