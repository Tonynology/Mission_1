package com.example.mission_1;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class WifiService {
    public boolean insertInfo(List<FreeWifi> freeWifiList) {
        boolean result = false;

        String url  = "jdbc:mariadb://172.30.1.67:3306/mission1";
        String userId = "root";
        String password = "1111";

        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet rs = null;

        try {
            Class.forName("org.mariadb.jdbc.Driver");

            connection = DriverManager.getConnection(url, userId, password);

            String sql = "INSERT into public_wifi_info values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, DATE_FORMAT(?,'%Y-%m-%d %h:%i:%s')); ";

            for (FreeWifi freeWifi : freeWifiList) {
                statement = connection.prepareStatement(sql);
                statement.setString(1, freeWifi.getX_SWIFI_MGR_NO());
                statement.setString(2, freeWifi.getX_SWIFI_WRDOFC());
                statement.setString(3, freeWifi.getX_SWIFI_MAIN_NM());
                statement.setString(4, freeWifi.getX_SWIFI_ADRES1());
                statement.setString(5, freeWifi.getX_SWIFI_ADRES2());
                statement.setString(6, freeWifi.getX_SWIFI_INSTL_FLOOR());
                statement.setString(7, freeWifi.getX_SWIFI_INSTL_TY());
                statement.setString(8, freeWifi.getX_SWIFI_INSTL_MBY());
                statement.setString(9, freeWifi.getX_SWIFI_SVC_SE());
                statement.setString(10, freeWifi.getX_SWIFI_CMCWR());
                statement.setString(11, freeWifi.getX_SWIFI_CNSTC_YEAR());
                statement.setString(12, freeWifi.getX_SWIFI_INOUT_DOOR());
                statement.setString(13, freeWifi.getX_SWIFI_REMARS3());
                statement.setString(14, freeWifi.getLAT());
                statement.setString(15, freeWifi.getLNT());
                statement.setString(16, freeWifi.getWORK_DTTM());

                int affected = statement.executeUpdate();

                if (affected > 0) {
                    result = true;
                } else {
                    System.out.println(freeWifi.getX_SWIFI_MGR_NO() + "저장 실패");  ///////////////
                    result = false;
                }
            }


        } catch (Exception e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
        } finally {
            try {
                if(rs != null && !rs.isClosed()){
                    rs.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }

            try {
                if(statement != null && !statement.isClosed()){
                    statement.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }

            try {
                if(connection != null && !connection.isClosed()){
                    connection.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return result;
    }

    public boolean deleteInfo(FreeWifi freeWifi){
        boolean result = false;

        return result;
    }

    public List<FreeWifi> getFreeWifi(String LAT, String LNT) {

        String url  = "jdbc:mariadb://172.30.1.67:3306/mission1";
        String userId = "root";
        String password = "1111";

        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet rs = null;
        List<FreeWifi> freeWifiList = new ArrayList<>();

        try {
            Class.forName("org.mariadb.jdbc.Driver");

            connection = DriverManager.getConnection(url, userId, password);

            String sql = "SELECT X_SWIFI_MGR_NO, X_SWIFI_WRDOFC, X_SWIFI_MAIN_NM, X_SWIFI_ADRES1, X_SWIFI_ADRES2, X_SWIFI_INSTL_FLOOR,"
                    +" X_SWIFI_INSTL_TY, X_SWIFI_INSTL_MBY, X_SWIFI_SVC_SE, X_SWIFI_CMCWR, X_SWIFI_CNSTC_YEAR, X_SWIFI_INOUT_DOOR,"
                    +" X_SWIFI_REMARS3,LAT, LNT, WORK_DTTM,"
                    +" (6371*acos(cos(radians(?))*cos(radians(LAT))*cos(radians(LNT)-radians(?))+sin(radians(?))*sin(radians(LAT)))) AS distance"
                    +" FROM public_wifi_info "
                    +" ORDER BY distance ASC"
                    +" LIMIT 20 ";

            statement = connection.prepareStatement(sql);
            // 나의 위도와 경도를 double로 파싱
            double userLat = Double.parseDouble(LAT);
            double userLnt = Double.parseDouble(LNT);

            statement.setDouble(1, userLnt); // 나의 위도
            statement.setDouble(2, userLat); // 나의 경도
            statement.setDouble(3, userLnt); // 나의 위도
            rs = statement.executeQuery();


            while (rs.next()) {
                FreeWifi freeWifi = new FreeWifi();

                freeWifi.setDistance(rs.getDouble("distance"));
                freeWifi.setX_SWIFI_MGR_NO(rs.getString("X_SWIFI_MGR_NO"));
                freeWifi.setX_SWIFI_WRDOFC(rs.getString("X_SWIFI_WRDOFC"));
                freeWifi.setX_SWIFI_MAIN_NM(rs.getString("X_SWIFI_MAIN_NM"));
                freeWifi.setX_SWIFI_ADRES1(rs.getString("X_SWIFI_ADRES1"));
                freeWifi.setX_SWIFI_ADRES2(rs.getString("X_SWIFI_ADRES2"));
                freeWifi.setX_SWIFI_INSTL_FLOOR(rs.getString("X_SWIFI_INSTL_FLOOR"));
                freeWifi.setX_SWIFI_INSTL_TY(rs.getString("X_SWIFI_INSTL_TY"));
                freeWifi.setX_SWIFI_INSTL_MBY(rs.getString("X_SWIFI_INSTL_MBY"));
                freeWifi.setX_SWIFI_SVC_SE(rs.getString("X_SWIFI_SVC_SE"));
                freeWifi.setX_SWIFI_CMCWR(rs.getString("X_SWIFI_CMCWR"));
                freeWifi.setX_SWIFI_CNSTC_YEAR(rs.getString("X_SWIFI_CNSTC_YEAR"));
                freeWifi.setX_SWIFI_INOUT_DOOR(rs.getString("X_SWIFI_INOUT_DOOR"));
                freeWifi.setX_SWIFI_REMARS3(rs.getString("X_SWIFI_REMARS3"));
                freeWifi.setLAT(rs.getString("LAT"));
                freeWifi.setLNT(rs.getString("LNT"));
                freeWifi.setWORK_DTTM(rs.getString("WORK_DTTM"));

                freeWifiList.add(freeWifi);
            }

        } catch (Exception e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
        } finally {
            try {
                if(rs != null && !rs.isClosed()){
                    rs.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }

            try {
                if(statement != null && !statement.isClosed()){
                    statement.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }

            try {
                if(connection != null && !connection.isClosed()){
                    connection.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return freeWifiList;
    }

    public FreeWifi getFreeWifiDetail(String xSwifiMgrNo) {

        String url  = "jdbc:mariadb://172.30.1.67:3306/mission1";
        String userId = "root";
        String password = "1111";

        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet rs = null;
        FreeWifi freeWifi = new FreeWifi();
        try {
            Class.forName("org.mariadb.jdbc.Driver");

            connection = DriverManager.getConnection(url, userId, password);
            String sql = "SELECT X_SWIFI_MGR_NO, X_SWIFI_WRDOFC, X_SWIFI_MAIN_NM, X_SWIFI_ADRES1, X_SWIFI_ADRES2, X_SWIFI_INSTL_FLOOR,"
                    +" X_SWIFI_INSTL_TY, X_SWIFI_INSTL_MBY, X_SWIFI_SVC_SE, X_SWIFI_CMCWR, X_SWIFI_CNSTC_YEAR, X_SWIFI_INOUT_DOOR,"
                    +" X_SWIFI_REMARS3,LAT, LNT, WORK_DTTM"
                    +" FROM public_wifi_info"
                    +" WHERE X_SWIFI_MGR_NO = ? ";

            statement = connection.prepareStatement(sql);
            statement.setString(1, xSwifiMgrNo);
            // 디테일 페이지에 거리를 나타내려 했으나 실패
//            String sql = "SELECT X_SWIFI_MGR_NO, X_SWIFI_WRDOFC, X_SWIFI_MAIN_NM, X_SWIFI_ADRES1, X_SWIFI_ADRES2, X_SWIFI_INSTL_FLOOR,"
//                    +" X_SWIFI_INSTL_TY, X_SWIFI_INSTL_MBY, X_SWIFI_SVC_SE, X_SWIFI_CMCWR, X_SWIFI_CNSTC_YEAR, X_SWIFI_INOUT_DOOR,"
//                    +" X_SWIFI_REMARS3,LAT, LNT, WORK_DTTM,"
//                    +" (6371*acos(cos(radians(?))*cos(radians(LAT))*cos(radians(LNT)-radians(?))+sin(radians(?))*sin(radians(LAT)))) AS distance"
//                    +" FROM public_wifi_info"
//                    +" WHERE X_SWIFI_MGR_NO = ? ";

//            // 나의 위도와 경도를 double로 파싱
//            double userLat = Double.parseDouble(LAT);
//            double userLnt = Double.parseDouble(LNT);
//
//            statement.setDouble(1, userLnt); // 나의 위도
//            statement.setDouble(2, userLat); // 나의 경도
//            statement.setDouble(3, userLnt); // 나의 위도
//            statement.setString(4, xSwifiMgrNo);

            rs = statement.executeQuery();

            while(rs.next()){
                freeWifi.setX_SWIFI_MGR_NO(rs.getString("X_SWIFI_MGR_NO"));
                freeWifi.setX_SWIFI_WRDOFC(rs.getString("X_SWIFI_WRDOFC"));
                freeWifi.setX_SWIFI_MAIN_NM(rs.getString("X_SWIFI_MAIN_NM"));
                freeWifi.setX_SWIFI_ADRES1(rs.getString("X_SWIFI_ADRES1"));
                freeWifi.setX_SWIFI_ADRES2(rs.getString("X_SWIFI_ADRES2"));
                freeWifi.setX_SWIFI_INSTL_FLOOR(rs.getString("X_SWIFI_INSTL_FLOOR"));
                freeWifi.setX_SWIFI_INSTL_TY(rs.getString("X_SWIFI_INSTL_TY"));
                freeWifi.setX_SWIFI_INSTL_MBY(rs.getString("X_SWIFI_INSTL_MBY"));
                freeWifi.setX_SWIFI_SVC_SE(rs.getString("X_SWIFI_SVC_SE"));
                freeWifi.setX_SWIFI_CMCWR(rs.getString("X_SWIFI_CMCWR"));
                freeWifi.setX_SWIFI_CNSTC_YEAR(rs.getString("X_SWIFI_CNSTC_YEAR"));
                freeWifi.setX_SWIFI_INOUT_DOOR(rs.getString("X_SWIFI_INOUT_DOOR"));
                freeWifi.setX_SWIFI_REMARS3(rs.getString("X_SWIFI_REMARS3"));
                freeWifi.setLAT(rs.getString("LAT"));
                freeWifi.setLNT(rs.getString("LNT"));
                freeWifi.setWORK_DTTM(rs.getString("WORK_DTTM"));
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
        } finally {
            try {
                if(rs != null && !rs.isClosed()){
                    rs.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }

            try {
                if(statement != null && !statement.isClosed()){
                    statement.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }

            try {
                if(connection != null && !connection.isClosed()){
                    connection.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return freeWifi;
    }
}
