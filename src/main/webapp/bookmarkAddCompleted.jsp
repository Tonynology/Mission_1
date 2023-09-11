<%@ page import="com.example.mission_1.BookMark" %>
<%@ page import="com.example.mission_1.BookMarkProcess" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>와이파이 정보 구하기</title>
    <style>
        body{
            text-align: center;
        }
    </style>
</head>
<body>
<%
    String bookMarkGrpId = (String) request.getParameter("bookMarkGrpId");
    String wifiNm = (String) request.getParameter("wifiNm");

    BookMark bookMark = new BookMark();
    bookMark.setBookMarkGrpId(bookMarkGrpId);
    bookMark.setWifiNm(wifiNm);

    BookMarkProcess process = new BookMarkProcess();
    boolean result = process.insertBookMark(bookMark);

%>
<br/>
<% if(result){ %>
<h1><%=wifiNm%>의 북마크를 등록했습니다.</h1>
<a href="/index.jsp">홈 으로 가기</a>
<%}else{%>
<h1><%=wifiNm%>의 북마크 등록을 실패했습니다.</h1>
<a href="/index.jsp">홈 으로 가기</a>
<%}%>
</body>
</html>
