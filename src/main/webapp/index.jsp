<%@ page import="com.example.mission_1.FreeWifi" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="java.util.List" %>
<%@ page import="com.example.mission_1.LoadWifi" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <title>와이파이 정보 구하기</title>
    <style>
        table{
            width: 100%;
            text-align: center;
        }
        thead th{
            background-color: mediumseagreen;
            color: white;
            padding: 10px;
            font-size: 0.7em;

        }
        tbody td{
            border: solid gray 1px;
            padding: 10px;
            font-size: 0.7em;
        }
    </style>
</head>
<body>
<%
    LoadWifi loadWifi = new LoadWifi();
    List<FreeWifi> freeWifiList = new ArrayList<>();
//        freeWifiList = null;

    if (request.getParameter("LAT") != null && request.getParameter("LNT") != null) {
        String LAT = request.getParameter("LAT").toString();
        String LNT = request.getParameter("LNT").toString();
        freeWifiList = loadWifi.getNearbyWifiList(LAT, LNT);
    }
%>
<h1>와이파이 정보 구하기</h1>
<jsp:include page="/head.jsp"/>

<br/>
LAT: <input type="text" id="LAT" name="LAT" value=""/>
, LNT: <input type="text" id="LNT" name="LNT" value=""/>
<button id="getMyLocation">내 위치 가져오기</button>
<button id="getNearbyWifi">근처 WIFI 정보 보기</button>

<br/>
<table>
    <thead>
        <tr>
            <th>거리(Km)</th>
            <th>관리번호</th>
            <th>자치구</th>
            <th>와이파이명</th>
            <th>도로명주소</th>
            <th>상세주소</th>
            <th>설치위치(층)</th>
            <th>설치유형</th>
            <th>설치기관</th>
            <th>서비스구분</th>
            <th>망종류</th>
            <th>설치년도</th>
            <th>실내외구분</th>
            <th>WIFI접속환경</th>
            <th>X좌표</th>
            <th>Y좌표</th>
            <th>작업일자</th>
        </tr>
    </thead>
    <tbody>
        <%
            if (freeWifiList == null) {
        %>
        <tr>
            <<td>위치 정보를 입력한 후 조회해 주세요.</td>
        </tr>
        <%
            } else {
                for (FreeWifi freeWifi: freeWifiList) {
        %>
            <tr>
                <td><%=freeWifi.getDistance()%></td>
                <td><%=freeWifi.getX_SWIFI_MGR_NO()%></td>
                <td><%=freeWifi.getX_SWIFI_WRDOFC()%></td>
<%--                <td><a id="getFreeWifiDetail" data-x-swifi-mgr-no="<%=freeWifi.getX_SWIFI_MGR_NO()%>"><%=freeWifi.getX_SWIFI_MAIN_NM()%></a></td> --%>
                <td><a href="/details.jsp?xSwifiMgrNo=<%=freeWifi.getX_SWIFI_MGR_NO()%>"><%=freeWifi.getX_SWIFI_MAIN_NM()%></a></td>
                <td><%=freeWifi.getX_SWIFI_ADRES1()%></td>
                <td><%=freeWifi.getX_SWIFI_ADRES2()%></td>
                <td><%=freeWifi.getX_SWIFI_INSTL_FLOOR()%></td>
                <td><%=freeWifi.getX_SWIFI_INSTL_TY()%></td>
                <td><%=freeWifi.getX_SWIFI_INSTL_MBY()%></td>
                <td><%=freeWifi.getX_SWIFI_SVC_SE()%></td>
                <td><%=freeWifi.getX_SWIFI_CMCWR()%></td>
                <td><%=freeWifi.getX_SWIFI_CNSTC_YEAR()%></td>
                <td><%=freeWifi.getX_SWIFI_INOUT_DOOR()%></td>
                <td><%=freeWifi.getX_SWIFI_REMARS3()%></td>
                <td><%=freeWifi.getLAT()%></td>
                <td><%=freeWifi.getLNT()%></td>
                <td><%=freeWifi.getWORK_DTTM()%></td>
            </tr>
        <%      }
        }
        %>
    </tbody>
</table>
<script src="https://code.jquery.com/jquery-3.2.1.min.js"></script>
<script>
    $(function () {
        //내 위치
        $("#getMyLocation").click(function(){
            navigator.geolocation.getCurrentPosition((position) => {
                $("#LAT").val(position.coords.latitude);
                $("#LNT").val(position.coords.longitude);
            });
        });
        //근처 FreeWifi
        $("#getNearbyWifi").click(function(){
            if($("#LAT").val() == '' || $("#LNT").val() == ''){
                alert("먼저 현재 위치를 가져와주세요.");
                return false;
            }else{
                var LAT = $("#LAT").val();
                var LNT = $("#LNT").val()
                window.location.href = "/index.jsp?LAT="+LAT+"&LNT="+LNT;
            }
        });
        //FreeWifi Detail
        $("#getFreeWifiDetail").click(function(){
            var LAT = $("#LAT").val();
            var LNT = $("#LNT").val()
            var xSwifiMgrNo = $(this).data("x-swifi-mgr-no");
            window.location.href = "/details.jsp?LAT="+LAT+"&LNT="+LNT+"&xSwifiMgrNo="+xSwifiMgrNo;
        });
    });
</script>
</html>