<jsp:useBean id="theShoppingCart" scope="session" class="business.Cart"/>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
    Document   : cart
    Created on : Oct 6, 2017, 9:48:01 PM
    Author     : Adam
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML>
<html lang="en">
    <jsp:include page="header.jsp"/>
    <jsp:include page="user-navigation.jsp"/>
    <jsp:include page="site-navigation.jsp"/>
        <main id="mainContent">
            <section id="cart">
                <h2>Your Cart</h2>
                <c:if test="${theShoppingCart.isEmpty()}">
                    <p>Your cart is empty!</p> <br>
                    <p><a href="catalog">Browse our catalog.</a></p>
                </c:if>
                <c:if test="${!theShoppingCart.isEmpty()}">
                <p>To remove an item, change the quantity to zero.</p>
                <hr>
                <table id="cartTable">
                    <thead>
                        <tr>
                            <th>Item</th>
                            <th>Price</th>
                            <th>Quantity</th>
                            <th>Total</th>
                        </tr>
                    </thead>
                    <tbody>

                        <c:forEach items="${theShoppingCart.items}" var="orderItem">
                                <tr>
                                    <td class="item"><c:out value="${orderItem.product.productName}"/></td>
                                    <td class="price">$<c:out value="${orderItem.product.price}"/></td>
                                    <td>
                                        <form action="<c:url value="/cart"/>" method="post">
                                            <input type="hidden" name="productList" value="${orderItem.product.productCode}">

                                            <input type="number" name="${orderItem.product.productCode}"
                                                    value="${orderItem.quantity}" title="Item Quantity">

                                            <input type="hidden" name="action" value="updateCart">
                                            <input type="submit" value="Update">
                                        </form>
                                    </td>
                                    <td class="total">$${orderItem.getTotal()}</td>
                                </tr>
                            </c:forEach>
                        <tr>
                            <td></td>
                            <td></td>
                            <td class="total"><b>Subtotal</b></td>
                            <td class="total">$${theShoppingCart.getTotal()}</td>
                        </tr>
                        <tr>
                            <td></td>
                            <td></td>
                            <td></td>
                            <td>
                                <form action="<c:url value="/order"/>" method="post">
                                <input type="hidden" name="action" value="checkout">
                                <button class="cartButton" type="submit">Checkout</button>
                                </form>
                            </td>
                        </tr>
                    </tbody>
                </table>

                </c:if>
            </section>
        </main>
        
    <jsp:include page="footer.jsp"/>
    </body>
</html>
