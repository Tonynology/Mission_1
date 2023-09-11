package com.example.mission_1;

import java.util.ArrayList;
import java.util.List;

public class LoadWifi {
    // search near Wifi
    public List<FreeWifi> getNearbyWifiList(String LAT, String LNT) {
        WifiService wifiService = new WifiService();
        HistoryService historyService = new HistoryService();

        FreeWifi freeWifi = new FreeWifi();
        freeWifi.setLAT(LAT);
        freeWifi.setLNT(LNT);

        if (!historyService.insertInfo(freeWifi)) {
            System.out.println("검색 히스토리 저장 실패");
        }
        List<FreeWifi> freeWifiList;
        freeWifiList = wifiService.getFreeWifi(LAT, LNT);

        return freeWifiList;
    }

    public boolean deleteHistory(String id) {
        HistoryService historyService = new HistoryService();
        return historyService.deleteHistory(id);
    }

    public List<History> getHistoryList() {
        List<History> historyList = new ArrayList<>();
        HistoryService historyService = new HistoryService();
        historyList = historyService.getHistoryList();

        return historyList;
    }
}
