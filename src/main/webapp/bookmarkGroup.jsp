<%@ page import="com.example.mission_1.BookMarkProcess" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="com.example.mission_1.BookMark" %>
<%@ page import="java.util.List" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>북마크 그룹</title>
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
    BookMarkProcess process = new BookMarkProcess();

    List<BookMark> list = new ArrayList<>();
    list = process.getBookMarkGrpList();

%>
<h1>와이파이 정보 구하기</h1>
<jsp:include page="/head.jsp"/>
<br>
<button id="moveBookMarkAddPage">북마크 그룹 이름 추가</button>

<br><br>
<table>
    <thead>
    <tr>
        <th>ID</th>
        <th>북마크이름</th>
        <th>순서</th>
        <th>등록일자</th>
        <th>수정일자</th>
        <th>비고</th>
    </tr>
    </thead>
    <tbody>
    <%
        if(list.size() == 0){
    %>
    <tr>
        <td colspan="6">북마크 그룹을 등록해주세요.</td>
    </tr>
    <%}else{
        for(BookMark bookMark : list){
    %>
    <tr>
        <td><%=bookMark.getID()%></td>
        <td><%=bookMark.getBookmarkGroupNm()%></td>
        <td><%=bookMark.getSeq()%></td>
        <td><%=bookMark.getRegDate()%></td>
        <%--<td><a href="/detail.jsp?xSwifiMgrNo=<%=freeWifi.getxSwifiMgrNo()%>"><%=freeWifi.getxSwifiMainNm()%></a></td>--%>
        <td><%=bookMark.getUpdateDate()%></td>
        <td>
            <a href = "/bookmarkGroupEdit.jsp?id=<%=bookMark.getID()%>">수정</a>
            <a href = "/bookmarkGroupDelete.jsp?id=<%=bookMark.getID()%>">삭제</a>
        </td>
    </tr>
    <%      }
    }
    %>
    </tbody>
</table>

</body>
<script src="https://code.jquery.com/jquery-3.2.1.min.js"></script>
<script>
    $(function () {
        $("#moveBookMarkAddPage").click(function(){
            window.location.href = "/bookmarkGroupAdd.jsp";
        });

    });
</script>
</html>
