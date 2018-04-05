<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:useBean id="theOrders" scope="session" type="java.util.List"/>
<jsp:useBean id="admin" scope="request" class="java.lang.String"/>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML>
<html lang="en">
<jsp:include page="header.jsp"/>
<jsp:include page="user-navigation.jsp"/>
<jsp:include page="site-navigation.jsp"/>
<main id="mainContent">
    <section id="orderList">
        <h2>Orders List</h2>
        <hr>
        <c:if test="${theOrders.size() == 0}">
            <p>You have no order history! <a href="catalog">Click here</a> to browse our catalog.</p>
        </c:if>
        <c:if test="${theOrders.size() > 0}">
            <table id="ordersList">
                <tr>
                    <th>Order Number</th>
                    <th>Customer</th>
                    <th>Order Date</th>
                    <th>Total</th>
                    <th></th>
                </tr>

                <c:forEach items="${theOrders}" var="order">
                <tr>
                        <td align="center">${order.orderNumber}  </td>
                        <td><c:out value="${order.user.firstName}"/> <c:out value="${order.user.lastName}"/>  </td>
                        <td>${order.date}  </td>
                        <td>$${order.totalCost}</td>
                        <td>
                            <c:if test="${admin.equals('true')}">
                                <form action="admin" method="post">
                                    <input type="hidden" name="action" value="removeOrder">
                                    <input type="hidden" name="orderNumber" value="${order.orderNumber}">
                                    <button type="submit">Remove Order</button>
                                </form>
                            </c:if>
                        </td>
                </tr>
                </c:forEach>

            </table>
        </c:if>
    </section>
</main>
<jsp:include page="footer.jsp"/>
</body>
</html>
