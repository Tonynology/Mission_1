<%@ page import="com.example.mission_1.LoadWifi" %>
<%@ page import="com.example.mission_1.HistoryService" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>와이파이 정보 구하기</title>
</head>
<body>
<%
    String id = request.getParameter("id").toString();
    boolean result = false;
    LoadWifi loadWifi = new LoadWifi();
    result = loadWifi.deleteHistory(id);
%>
<% if (result) { %>
    <h1><%=id%>가 삭제되었습니다.</h1>
<% } else { %>
    <h1><%=id%>가 삭제에 실패했습니다.</h1>
<% } %>
<a href="/index.jsp">홈 으로 가기</a>
</body>
</html>
