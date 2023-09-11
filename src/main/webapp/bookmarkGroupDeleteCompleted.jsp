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
    String bookmarkGroupNm = (String) request.getParameter("bookmarkGroupNm");
    String id = (String) request.getParameter("id");

    BookMarkProcess process = new BookMarkProcess();
    boolean result = process.deleteBookMarkGrp(id);

%>
<br/>
<% if(result){ %>
<h1><%=bookmarkGroupNm%>의 북마크 그룹을 삭제했습니다.</h1>
<a href="/index.jsp">홈 으로 가기</a>
<%}else{%>
<h1><%=bookmarkGroupNm%>의 북마크 그룹 삭제를 실패했습니다.</h1>
<a href="/index.jsp">홈 으로 가기</a>
<%}%>
</body>
</html>
