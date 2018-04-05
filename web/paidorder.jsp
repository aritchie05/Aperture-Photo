<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:useBean id="currentOrder" scope="session" class="business.Order"/>
<%--
    Document   : paidorder.jsp
    Created on : Nov 27, 2017
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
            ${currentOrder.user.firstName} ${currentOrder.user.lastName} <br>
            ${currentOrder.user.address1} <br>
            ${currentOrder.user.address2}<br>
            ${currentOrder.user.city}, ${currentOrder.user.stateRegion} ${currentOrder.user.postCode} <br>
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
                    <td class="item">${orderItem.product.productName}</td>
                    <td class="price">$${orderItem.product.price}</td>
                    <td class="quantity">${orderItem.quantity}</td>
                    <td class="total">$${orderItem.getTotal()}</td>
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
        <h3>Paid in Full</h3>
    </section>
</main>
</body>
<jsp:include page="footer.jsp"/>
</html>
