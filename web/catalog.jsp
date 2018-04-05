<%-- 
    Document   : catalog
    Created on : Oct 6, 2017, 9:51:40 PM
    Author     : Adam
--%>
<jsp:useBean id="allProducts" scope="request" type="java.util.List"/>
<jsp:useBean id="edit" scope="request" class="java.lang.String"/>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML>
<html lang="en">
    <jsp:include page="header.jsp"/>
    <jsp:include page="user-navigation.jsp"/>
    <jsp:include page="site-navigation.jsp"/>
        <main id="mainContent">
            <section id="catalog">
                <h2>Product Catalog</h2>
                <hr>
                <c:if test='${edit.equals("true")}'>
                    <form action="admin" method="post">
                        <input type="hidden" name="action" value="createProduct">
                        <button type="submit">Add New Product</button>
                    </form>
                </c:if>
                <h3>Cameras</h3>
                <ul>
                    <c:forEach items="${allProducts}" var="product">
                        <c:if test="${product.category == 'Cameras'}">
                        <li>
                            <a href="item?productCode=${product.productCode}">${product.productName}</a>
                            <c:if test='${edit.equals("true")}'>
                                <form action="admin" method="post">
                                    <input type="hidden" name="action" value="editProduct">
                                    <input type="hidden" name="productCode" value="${product.productCode}">
                                    <button type="submit">Edit Product</button>
                                </form>
                                <br>
                            </c:if>
                        </li>
                        </c:if>
                    </c:forEach>
                </ul>
                <h3>Lenses</h3>
                <ul>
                    <c:forEach items="${allProducts}" var="product">
                        <c:if test="${product.category == 'Lenses'}">
                            <li>
                                <a href="item?productCode=${product.productCode}">${product.productName}</a>
                                <c:if test='${edit.equals("true")}'>
                                    <form action="admin" method="post">
                                        <input type="hidden" name="action" value="editProduct">
                                        <input type="hidden" name="productCode" value="${product.productCode}">
                                        <button type="submit">Edit Product</button>
                                    </form>
                                    <br>
                                </c:if>
                            </li>
                        </c:if>
                    </c:forEach>
                </ul>
            </section>
        </main>
        
    <jsp:include page="footer.jsp"/>
    </body>
</html>
