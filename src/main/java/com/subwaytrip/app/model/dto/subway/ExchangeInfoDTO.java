package com.subwaytrip.app.model.dto.subway;

import com.subwaytrip.app.utils.StaticHelper;
import lombok.Data;
import org.json.simple.JSONObject;

@Data
public class ExchangeInfoDTO {

    private String laneName;            // 승차노선 명
    private String startName;           // 승차역 명
    private String exName;              // 환승역 명
    private int exSID;                  // 환승역 ID
    private int fastTrain;              // 빠른 환승 객차 번호
    private int fastDoor;               // 빠른 환승 객차 문 번호
    private int exWalkTime;             // 환승소요시간(초)

    public static ExchangeInfoDTO of(JSONObject exchangeInfoJson) {
        ExchangeInfoDTO exchangeInfoDTO = new ExchangeInfoDTO();
        exchangeInfoDTO.setLaneName(StaticHelper.getJsonValue(exchangeInfoJson, "laneName", ""));
        exchangeInfoDTO.setStartName(StaticHelper.getJsonValue(exchangeInfoJson, "startName", ""));
        exchangeInfoDTO.setExName(StaticHelper.getJsonValue(exchangeInfoJson, "exName", ""));
        exchangeInfoDTO.setExSID(StaticHelper.getJsonValue(exchangeInfoJson, "exSID"));
        exchangeInfoDTO.setFastTrain(StaticHelper.getJsonValue(exchangeInfoJson, "fastTrain"));
        exchangeInfoDTO.setFastDoor(StaticHelper.getJsonValue(exchangeInfoJson, "fastDoor"));
        exchangeInfoDTO.setExWalkTime(StaticHelper.getJsonValue(exchangeInfoJson, "exWalkTime"));
        return exchangeInfoDTO;
    }

}
