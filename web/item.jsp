<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:useBean id="product" scope="request" class="business.Product"/>
<%--
    Document   : item
    Created on : Oct 6, 2017, 9:52:54 PM
    Author     : Adam
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML>
<html lang="en">
    <jsp:include page="header.jsp"/>
    <jsp:include page="user-navigation.jsp"/>
    <jsp:include page="site-navigation.jsp"/>
        <main id="mainContent">
            <section id="item">
                <br>
                <a href="catalog">&#8592Back to Catalog</a>
                <h2>Item Detail</h2>
                <hr>
                <img id="productImage" src=${product.getImageURL()} alt="${product.productName}" height="150">
                <h3 id="productName"><c:out value="${product.productName}"/></h3>
                <p id="productCategory"><c:out value="${product.category}"/></p>
                <p>$<c:out value="${product.price}"/></p>
                <form method="post" action="<c:url value="/cart"/>">
                    <input type="hidden" name="productList" value="${product.productCode}">
                    <input type="hidden" name="${product.productCode}" value="1">
                    <input type="hidden" name="action" value="updateCart">
                    <button name="addCart" type="submit">Add To Cart</button>
                </form>
                <p id="productDescription"><c:out value="${product.description}"/></p>
            </section>
        </main>
        
    <jsp:include page="footer.jsp"/>
    </body>
</html>
