package com.subwaytrip.app.controller;

import com.subwaytrip.app.constants.SubwayConstValue;
import com.subwaytrip.app.service.SubwayStationService;
import com.subwaytrip.app.utils.LoggerUtils;
import com.subwaytrip.app.utils.StaticHelper;
import com.subwaytrip.app.utils.SubwayHelper;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/subway")
public class SubwayStationController extends LoggerUtils {

    private SubwayStationService subwayStationService;

    @Autowired
    public SubwayStationController(SubwayStationService subwayStationService) {
        this.subwayStationService = subwayStationService;
    }

    /**
     * 랜덤 지하철 여행 경로
     */
    @GetMapping(value = "/random")
    public ResponseEntity<?> randomSubwayRoot(@RequestParam String startStationName, @RequestParam(defaultValue = "") String lineName) {
        // 출발지 역 ID
        int startStationID = subwayStationService.getStationID(lineName, startStationName);
        // 도착지 역 ID
        int endStationID = subwayStationService.randomStationIDByLineName(lineName, startStationName);

        logger.info("startStationID = " + startStationID + ", endStationID = " + endStationID);
        return new ResponseEntity<>(SubwayHelper.findStationRoot(1000, startStationID, endStationID, 1), HttpStatus.OK);
    }

    /**
     * 노선별 지하철역 정보
     */
    @GetMapping(value = "/station")
    public ResponseEntity<?> subwayStationList(@RequestParam(defaultValue = "") String lineName) {
        if (lineName.equals("")) {
            return new ResponseEntity<>(SubwayConstValue.subwayStations.toJSONString(), HttpStatus.OK);
        } else {
            JSONObject subwayStations = new JSONObject();
            JSONArray subwayStationData = StaticHelper.getJsonArray(SubwayConstValue.subwayStations, lineName);
            subwayStations.put(lineName, subwayStationData);
            return new ResponseEntity<>(subwayStations.toJSONString(), HttpStatus.OK);
        }
    }

    /**
     * 노선명 리스트
     */
    @GetMapping(value = "/line")
    public ResponseEntity<?> lineNameList() {
        return new ResponseEntity<>(SubwayHelper.getSubwayLineList(), HttpStatus.OK);
    }

}
