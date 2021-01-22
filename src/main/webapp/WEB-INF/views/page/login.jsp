<%@ page language="java" contentType="text/html; charset=EUC-KR" pageEncoding="utf-8" %>
<!DOCTYPE html>
<html lang="en">
<jsp:include page="..\template\headTag.jsp" />
<body>
    <div id="app">
        <jsp:include page="..\template\Header.jsp" />
        <div class="login_wrap">
            <form method="post" action="/login">
                <input type="email" placeholder="email" name="email"/>
                <input type="password" placeholder="password" name="password"/>
                <input type="submit" value="로그인"/>
            </form>
        </div>
        <jsp:include page="..\template\Footer.jsp" />
    </div>
</body>
</html>