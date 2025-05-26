<%@ page import="dev.jcasben.noticeboardclient2.Notice" %>
<%@ page import="java.time.format.DateTimeFormatter" %>
<%@ page import="java.time.LocalDateTime" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <title>JSP - Hello World</title>
</head>
<body>
<h1>NOTICE BOARD CLIENT</h1>

<h2>Create a new notice</h2>
<form method="post" action="noticeBoard">
    Message: <input type="text" name="message"><br>
    Valid for (minutes): <input type="number" name="minutes"><br>
    <input type="submit" name="action" value="create">
</form>

<%
    String noticeCode = (String) request.getAttribute("noticeCode");
    if (noticeCode != null) {
%>
<p style="color: green;">Notice created! Your code: <strong><%= noticeCode %></strong></p>
<%
    }
%>

<h2>Cancel a Notice</h2>
<form action="noticeBoard" method="post">
    Code: <input type="text" name="deleteCode"/><br/>
    <input type="submit" name="action" value="delete"/>
</form>

<%
    Boolean deleteSuccess = (Boolean) request.getAttribute("deleteSuccess");
    if (deleteSuccess != null) {
        String color = deleteSuccess ? "green" : "red";
%>
<p style="color: <%= color %>;">
    Notice deleted: <strong><%= deleteSuccess %></strong>
</p>
<%
    }
%>

<hr/>

<h2>Active notices</h2>

<ul>
    <%
        java.util.List<Notice> notices = (java.util.List<Notice>) request.getAttribute("notices");
        if (notices != null) {
            for (Notice notice : notices) {
    %>
    <li><%= notice.getMessage() %> | Expires: <%= notice.getFormatedExpiration()%></li>
    <%
        }
    } else {
    %>
    <li>No notices no show in the board</li>
    <%
        }
    %>
</ul>

<form method="get">
    <input type="submit" value="refresh">
</form>

</body>
</html>