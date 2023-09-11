<%@ page import="com.example.mission_1.WifiService" %>
<%@ page import="com.example.mission_1.FreeWifi" %>
<%@ page import="com.example.mission_1.BookMarkProcess" %>
<%@ page import="com.example.mission_1.BookMark" %>
<%@ page import="java.util.List" %>
<%@ page import="java.util.ArrayList" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>와이파이 정보 구하기</title>
    <style>
        table{
            width: 50%;
            text-align: center;
        }
        th{
            background-color: mediumseagreen;
            color: white;
            padding: 10px;
            font-size: 0.7em;
            width: 30%;

        }
        td{
            border: solid gray 1px;
            padding: 10px;
            font-size: 0.7em;
        }
    </style>
</head>
<body>
<%
    String xSwifiMgrNo = request.getParameter("xSwifiMgrNo");
//    String LAT = request.getParameter("LAT").toString();
//    String LNT = request.getParameter("LNT").toString();
    WifiService wifiService = new WifiService();
//    FreeWifi freeWifi = wifiService.getFreeWifiDetail(LAT, LNT, xSwifiMgrNo);
    FreeWifi freeWifi = wifiService.getFreeWifiDetail(xSwifiMgrNo);

    BookMarkProcess process = new BookMarkProcess();
    List<BookMark> list = new ArrayList<>();
    list = process.getBookMarkGrpList();
%>
<h1>와이파이 정보 구하기</h1>
<jsp:include page="/head.jsp"/>
<br>
<select id="bookMarkGrpId">
    <% for(BookMark bookMark: list){%>
    <option value="<%=bookMark.getID()%>"><%=bookMark.getBookmarkGroupNm()%></option>
    <%}%>
</select>
<button id="addBookMark">북마크 추가하기</button>
<br>
<table>
    <input type="hidden" name="wifiNm" id="wifiNm" value="<%=freeWifi.getX_SWIFI_MAIN_NM()%>"/>
    <tr>
        <th>거리(Km)</th>
        <td><%=freeWifi.getDistance()%></td>
    </tr>
    <tr>
        <th>관리번호</th>
        <td><%=freeWifi.getX_SWIFI_MGR_NO()%></td>
    </tr>
    <tr>
        <th>자치구</th>
        <td><%=freeWifi.getX_SWIFI_WRDOFC()%></td>
    </tr>
    <tr>
        <th>와이파이명</th>
        <td><%=freeWifi.getX_SWIFI_MAIN_NM()%></td>
    </tr>
    <tr>
        <th>도로명주소</th>
        <td><%=freeWifi.getX_SWIFI_ADRES1()%>/td>
    </tr>
    <tr>
        <th>상세주소</th>
        <td><%=freeWifi.getX_SWIFI_ADRES2()%></td>
    </tr>
    <tr>
        <th>설치위치(층)</th>
        <td><%=freeWifi.getX_SWIFI_INSTL_FLOOR()%></td>
    </tr>
    <tr>
        <th>설치유형</th>
        <td><%=freeWifi.getX_SWIFI_INSTL_TY()%></td>
    </tr>
    <tr>
        <th>설치기관</th>
        <td><%=freeWifi.getX_SWIFI_INSTL_MBY()%></td>
    </tr>
    <tr>
        <th>서비스구분</th>
        <td><%=freeWifi.getX_SWIFI_SVC_SE()%></td>
    </tr>
    <tr>
        <th>망종류</th>
        <td><%=freeWifi.getX_SWIFI_CMCWR()%></td>
    </tr>
    <tr>
        <th>설치년도</th>
        <td><%=freeWifi.getX_SWIFI_CNSTC_YEAR()%></td>
    </tr>
    <tr>
        <th>실내외구분</th>
        <td><%=freeWifi.getX_SWIFI_INOUT_DOOR()%></td>
    </tr>
    <tr>
        <th>WIFI접속환경</th>
        <td><<%=freeWifi.getX_SWIFI_REMARS3()%>/td>
    </tr>
    <tr>
        <th>X좌표</th>
        <td><%=freeWifi.getLAT()%></td>
    </tr>
    <tr>
        <th>Y좌표</th>
        <td><%=freeWifi.getLNT()%></td>
    </tr>
    <tr>
        <th>작업일자</th>
        <td><%=freeWifi.getWORK_DTTM()%></td>
    </tr>
</table>

</body>
<script src="https://code.jquery.com/jquery-3.2.1.min.js"></script>
<script>
    $(function () {
        $("#addBookMark").click(function(){
            var bookMarkGrpId = $("#bookMarkGrpId option:selected").val();
            var wifiNm = $("#wifiNm").val();
            window.location.href = "/bookmarkAddCompleted.jsp?bookMarkGrpId="+bookMarkGrpId+"&wifiNm="+wifiNm;
        });
    });
</script>
</html>
