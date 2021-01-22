<%@ page language="java" contentType="text/html; charset=EUC-KR" pageEncoding="utf-8" %>
<!DOCTYPE html>
<html lang="en">
<jsp:include page="..\..\template\headTag.jsp" />
<body>
    <div id="app">
        <jsp:include page="..\..\template\Header.jsp" />
        <h1>상품 게시</h1>
        <form method="post" action="/product/posting">
            <select>
                <option value="">--상품 게시 종류--</option>
                <option value="doll">인형</option>
                <option value="card">카드</option>
            </select>
            <input type="text" name="productName" placeholder="상품명"/>
            <textarea placeholder="상품설명" name="explanation"></textarea>
            <input type="number" name="quantity" placeholder="수량"/>
            <input type="number" name="price" placeholder="가격"/>
            <input type="submit" value="상품게시"/>
        </form>
        <jsp:include page="..\..\template\Footer.jsp" />
    </div>
</body>
</html>