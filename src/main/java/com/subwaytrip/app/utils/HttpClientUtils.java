package com.subwaytrip.app.utils;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.json.simple.JSONObject;

public class HttpClientUtils {

    public static JSONObject getRequest(String url) {
        try {
            HttpClient httpClient = new DefaultHttpClient();
            HttpGet getRequest = new HttpGet(url);
            HttpResponse response = httpClient.execute(getRequest);
            JSONObject responseJson = StaticHelper.getJsonObject(EntityUtils.toString(response.getEntity()));
            return responseJson;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

}
