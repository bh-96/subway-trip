package com.subwaytrip.app.utils;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

public class StaticHelper {

    public static JSONObject getJsonObject(String payload) {
        try {
            JSONParser parser = new JSONParser();
            return (JSONObject) parser.parse(payload);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public static JSONObject getJsonObject(JSONObject jsonObject, String key) {
        try {
            return (JSONObject) jsonObject.get(key);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public static String getJsonValue(JSONObject jsonObject, String key, String defaultValue) {
        try {
            return jsonObject.get(key).toString();
        } catch (Exception e) {
            return defaultValue;
        }
    }

    public static JSONArray getJsonArray(JSONObject jsonObject, String key) {
        try {
            return (JSONArray) jsonObject.get(key);
        } catch (Exception e) {
            return null;
        }
    }

    public static int getJsonValue(JSONObject jsonObject, String key) {
        try {
            return Integer.parseInt(jsonObject.get(key).toString());
        } catch (Exception e) {
            return 0;
        }
    }

}
