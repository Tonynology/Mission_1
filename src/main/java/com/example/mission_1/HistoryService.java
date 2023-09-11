package com.example.mission_1;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class HistoryService {
    // save history
    public boolean insertInfo(FreeWifi freeWifi){
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

            String sql = "INSERT into location_history (X_POSITION, Y_POSITION, SEARCH_DATE) VALUES(?,?,now());";

            statement = connection.prepareStatement(sql);
            statement.setString(1, freeWifi.getLAT());
            statement.setString(2, freeWifi.getLNT());

            int affected = statement.executeUpdate();
            if(affected > 0) {
                result = true;
            }else {
                System.out.println(freeWifi.getX_SWIFI_MGR_NO()+"저장 실패");
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

    //delete history
    public boolean deleteHistory(String id){
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

            String sql = "DELETE FROM location_history WHERE ID = ?";

            statement = connection.prepareStatement(sql);
            statement.setString(1, id);

            int affected = statement.executeUpdate();
            if(affected > 0) {
                result = true;
            }else {
                System.out.println(id + "삭제 실패");
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
    //history list
    public List<History> getHistoryList(){

        String url  = "jdbc:mariadb://172.30.1.67:3306/mission1";
        String userId = "root";
        String password = "1111";

        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet rs = null;
        List<History> historyList = new ArrayList<>();
        try {
            Class.forName("org.mariadb.jdbc.Driver");

            connection = DriverManager.getConnection(url, userId, password);

            String sql = "SELECT ID, X_POSITION, Y_POSITION, SEARCH_DATE FROM location_history ORDER BY ID DESC";

            statement = connection.prepareStatement(sql);
            rs = statement.executeQuery();

            while (rs.next()) {
                History history = new History();

                history.setID(rs.getString("ID"));
                history.setxPosition(rs.getString("X_POSITION"));
                history.setyPosition(rs.getString("Y_POSITION"));
                history.setSearchDate(rs.getString("SEARCH_DATE"));

                historyList.add(history);
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
        return historyList;
    }
}
