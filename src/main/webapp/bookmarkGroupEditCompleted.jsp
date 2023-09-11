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
    String bookmarkGroupNm = (String) request.getParameter("bookmarkGroupNm");
    String seq = (String) request.getParameter("seq");
    String id = (String) request.getParameter("id");

    BookMark bookMark = new BookMark();
    bookMark.setBookmarkGroupNm(bookmarkGroupNm);
    bookMark.setSeq(Integer.parseInt(seq));
    bookMark.setID(id);

    BookMarkProcess process = new BookMarkProcess();
    boolean result = process.updateBookMarkGrp(bookMark);

%>
<br/>
<% if(result){ %>
<h1><%=bookmarkGroupNm%>의 북마크 그룹을 수정했습니다.</h1>
<a href="/index.jsp">홈 으로 가기</a>
<%}else{%>
<h1><%=bookmarkGroupNm%>의 북마크 그룹 수정을 실패했습니다.</h1>
<a href="/index.jsp">홈 으로 가기</a>
<%}%>
</body>
</html>
