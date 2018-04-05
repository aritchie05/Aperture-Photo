<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:useBean id="currentOrder" scope="session" class="business.Order"/>
<%--
    Document   : order.jsp
    Created on : Oct 6, 2017, 10:31:57 PM
    Author     : Adam
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML>
<html lang="en">
    <jsp:include page="header.jsp"/>
    <jsp:include page="user-navigation.jsp"/>
    <jsp:include page="site-navigation.jsp"/>
        <main id="mainContent">
            <section id="order">
                <h2>Invoice</h2>
                <hr>
                <p>Date: ${currentOrder.date}</p>
                <p><u>Ship To/Bill To:</u></p>
                <p>
                    <c:out value="${currentOrder.user.firstName}"/> <c:out value="${currentOrder.user.lastName}"/> <br>
                    <c:out value="${currentOrder.user.address1}"/> <br>
                    <c:out value="${currentOrder.user.address2}"/> <br>
                    <c:out value="${currentOrder.user.city}"/>, <c:out value="${currentOrder.user.stateRegion}"/>
                    <c:out value="${currentOrder.user.postCode}"/> <br>
                </p>
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
                        <c:forEach items="${currentOrder.items}" var="orderItem">
                            <tr>
                                <td class="item"><c:out value="${orderItem.product.productName}"/></td>
                                <td class="price">$<c:out value="${orderItem.product.price}"/></td>
                                <td class="quantity"><c:out value="${orderItem.quantity}"/></td>
                                <td class="total">$<c:out value="${orderItem.getTotal()}"/></td>
                            </tr>
                        </c:forEach>
                        <tr>
                            <td></td>
                            <td></td>
                            <td class="total"><b>Subtotal</b></td>
                            <td>$${currentOrder.subtotal}</td>
                        </tr>
                        <tr>
                            <td></td>
                            <td></td>
                            <td class="total"><b>Tax</b></td>
                            <td>$${currentOrder.taxCost}</td>
                        </tr>
                        <tr>
                            <td></td>
                            <td></td>
                            <td class="total"><b>Total</b></td>
                            <td>$${currentOrder.totalCost}</td>
                        </tr>
                    </tbody>
                </table>
                <form method="post" action="payment.jsp">
                    <button name="backToCart" formaction="cart.jsp">Back to Cart</button>
                    <button class="rightButton" name="purchase" type="submit">Purchase</button>
                    <input type="hidden" name="action" value="clearCart">
                </form>
            </section>
        </main>
    </body>
    <jsp:include page="footer.jsp"/>
</html>
