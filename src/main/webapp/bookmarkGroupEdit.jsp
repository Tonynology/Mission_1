<%@ page import="com.example.mission_1.BookMark" %>
<%@ page import="com.example.mission_1.BookMarkProcess" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
  <title>북마크 그룹 수정</title>
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
  String id = (String) request.getParameter("id");
  BookMark bookMark = new BookMark();
  BookMarkProcess process = new BookMarkProcess();
  bookMark = process.getBookMarkGrpInfo(id);
%>
<h1>와이파이 정보 구하기</h1>
<jsp:include page="/head.jsp"/>
<br>
<form action="/bookmarkGroupEditCompleted.jsp" method="get">
  <table>
    <input type="hidden" name="id" value="<%=id%>"/>
    <tr>
      <th>북마크 이름</th>
      <td>
        <input name="bookmarkGroupNm" id="bookmarkGroupNm" value="<%=bookMark.getBookmarkGroupNm()%>"/>
      </td>
    </tr>
    <tr>
      <th>순서</th>
      <td>
        <input name="seq" id="seq" value="<%=bookMark.getSeq()%>"/>
      </td>
    </tr>

  </table>
  <a href="/bookmarkGroup.jsp">돌아가기</a>
  <input type="submit" value="수정">
</form>

</body>
<script src="https://code.jquery.com/jquery-3.2.1.min.js"></script>
<script>

</script>
</html>
