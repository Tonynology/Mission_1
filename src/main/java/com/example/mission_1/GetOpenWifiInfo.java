package com.example.mission_1;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.io.*;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet(name = "getOpenWifiInfo", value = "/getOpenWifiInfo")
public class GetOpenWifiInfo extends HttpServlet {
    WifiService wifiService = new WifiService();
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        System.out.println("Enter");    ////
        String key = "4656567a436d6f6f36304a6e577755";
        JsonObject json = null;
        int start = 1;
        int end = 20;

        //get data
        json = (JsonObject) URLConnection(key, start, end);
        JsonObject data = (JsonObject) json.get("TbPublicWifiInfo");
        int totalCount = Integer.parseInt(data.get("list_total_count").toString());

        //insert data in db
        boolean result = false;
        result = insertWifiInfo(data);

        if (result == true) {
            for (int i = end + 1; i <= totalCount; i++) {
                int j = i + 20;
                if (j > totalCount) {
                    System.out.println("Last data");
                }
                System.out.println("start " + i);
                System.out.println("end " + j);

                json = URLConnection(key, i, j);
                data = (JsonObject) json.get("TbPublicWifiInfo");
                result = insertWifiInfo(data);
                if (result == false) break;

                i = j + 1;
            }
        }
        request.setAttribute("totalCount", totalCount);
        RequestDispatcher rq = request.getRequestDispatcher("/dataSavedCompleted.jsp");
        rq.forward(request, response);

    }
    public JsonObject URLConnection(String key, int start, int end) throws IOException {
        JsonObject json = null;
        StringBuilder urlBuilder = new StringBuilder("http://openapi.seoul.go.kr:8088"); /*URL*/
        urlBuilder.append("/" +  URLEncoder.encode(key,"UTF-8") ); /*인증키 (sample사용시에는 호출시 제한됩니다.)*/
        urlBuilder.append("/" +  URLEncoder.encode("json","UTF-8") ); /*요청파일타입 (xml,xmlf,xls,json) */
        urlBuilder.append("/" + URLEncoder.encode("TbPublicWifiInfo","UTF-8")); /*서비스명 (대소문자 구분 필수입니다.)*/
        urlBuilder.append("/" + URLEncoder.encode(String.valueOf(start),"UTF-8")); /*요청시작위치 (sample인증키 사용시 5이내 숫자)*/
        urlBuilder.append("/" + URLEncoder.encode(String.valueOf(end),"UTF-8")); /*요청종료위치(sample인증키 사용시 5이상 숫자 선택 안 됨)*/

        URL url = new URL(urlBuilder.toString());
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod("GET");
        conn.setRequestProperty("Content-type", "application/xml"); /////////////json
        System.out.println("Response code: " + conn.getResponseCode()); /* 연결 자체에 대한 확인이 필요하므로 추가합니다.*/

        BufferedReader rd;
        // 서비스코드가 정상이면 200~300사이의 숫자가 나옵니다.
        if(conn.getResponseCode() >= 200 && conn.getResponseCode() <= 300) {
            rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));
        } else {
            rd = new BufferedReader(new InputStreamReader(conn.getErrorStream()));
        }
        StringBuilder sb = new StringBuilder();
        String line;
        while ((line = rd.readLine()) != null) {
            sb.append(line);
        }
        rd.close();
        conn.disconnect();
        json = (JsonObject) new JsonParser().parse(sb.toString());

        return json;
    }

    public boolean insertWifiInfo(JsonObject data) {
        boolean result = false;
        JsonArray dataArray = (JsonArray) data.get("row");
        System.out.println(data);

        List<FreeWifi> freeWifiList = new ArrayList<>();
        for (int i = 0; i < dataArray.size(); i++) {
            JsonObject singleData = (JsonObject) dataArray.get(i);
            FreeWifi freeWifi = new FreeWifi();
            freeWifi.setX_SWIFI_MGR_NO(singleData.get("X_SWIFI_MGR_NO").toString().replaceAll("\"", ""));
            freeWifi.setX_SWIFI_WRDOFC(singleData.get("X_SWIFI_WRDOFC").toString().replaceAll("\"", ""));
            freeWifi.setX_SWIFI_MAIN_NM(singleData.get("X_SWIFI_MAIN_NM").toString().replaceAll("\"", ""));
            freeWifi.setX_SWIFI_ADRES1(singleData.get("X_SWIFI_ADRES1").toString().replaceAll("\"", ""));
            freeWifi.setX_SWIFI_ADRES2(singleData.get("X_SWIFI_ADRES2").toString().replaceAll("\"", ""));
            freeWifi.setX_SWIFI_INSTL_FLOOR(singleData.get("X_SWIFI_INSTL_FLOOR").toString().replaceAll("\"", ""));
            freeWifi.setX_SWIFI_INSTL_TY(singleData.get("X_SWIFI_INSTL_TY").toString().replaceAll("\"", ""));
            freeWifi.setX_SWIFI_INSTL_MBY(singleData.get("X_SWIFI_INSTL_MBY").toString().replaceAll("\"", ""));
            freeWifi.setX_SWIFI_SVC_SE(singleData.get("X_SWIFI_SVC_SE").toString().replaceAll("\"", ""));
            freeWifi.setX_SWIFI_CMCWR(singleData.get("X_SWIFI_CMCWR").toString().replaceAll("\"", ""));
            freeWifi.setX_SWIFI_CNSTC_YEAR(singleData.get("X_SWIFI_CNSTC_YEAR").toString().replaceAll("\"", ""));
            freeWifi.setX_SWIFI_INOUT_DOOR(singleData.get("X_SWIFI_INOUT_DOOR").toString().replaceAll("\"", ""));
            freeWifi.setX_SWIFI_REMARS3(singleData.get("X_SWIFI_REMARS3").toString().replaceAll("\"", ""));
            freeWifi.setLAT(singleData.get("LAT").toString().replaceAll("\"", ""));
            freeWifi.setLNT(singleData.get("LNT").toString().replaceAll("\"", ""));
            freeWifi.setWORK_DTTM(singleData.get("WORK_DTTM").toString().replaceAll("\"", ""));

            freeWifiList.add(freeWifi);
        }

        if (!wifiService.insertInfo(freeWifiList)) {
            System.out.println("데이터 저장 실패");
        } else {
            result = true;
        }

        return result;
    }
}
