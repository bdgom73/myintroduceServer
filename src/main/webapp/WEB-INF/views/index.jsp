<%@ page language="java" contentType="text/html; charset=EUC-KR" pageEncoding="utf-8" %>
<!DOCTYPE html>
<html>
<jsp:include page="\template\headTag.jsp" />
<body>
    <div id="app">
        <jsp:include page="\template\Header.jsp" />
        <h1>Hello World!</h1>
        <h2>Time 시간</h2>
        <p>${time}</p>
        <jsp:include page="\template\Footer.jsp" />
    </div>
</body>
</html>