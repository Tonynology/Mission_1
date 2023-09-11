<%@ page import="com.example.mission_1.WifiService" %>
<%@ page import="com.example.mission_1.FreeWifi" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>북마크 그룹 추가</title>
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
//    System.out.println(xSwifiMgrNo);
    WifiService WService = new WifiService();
    FreeWifi freeWifi = WService.getFreeWifiDetail(xSwifiMgrNo);


%>
<h1>와이파이 정보 구하기</h1>
<jsp:include page="/head.jsp"/>
<br>
<form action="/bookmarkGroupAddCompleted.jsp" method="get">
    <table>
        <tr>
            <th>북마크 이름</th>
            <td>
                <input name="bookmarkGroupNm" id="bookmarkGroupNm" />
            </td>
        </tr>
        <tr>
            <th>순서</th>
            <td>
                <input name="seq" id="seq"/>
            </td>
        </tr>

    </table>
    <input type="submit" value="추가">
</form>


</body>
<script src="https://code.jquery.com/jquery-3.2.1.min.js"></script>
<script>

</script>
</html>
