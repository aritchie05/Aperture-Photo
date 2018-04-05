<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
    Document   : usernavigation
    Created on : Oct 6, 2017, 8:16:50 PM
    Author     : Adam
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<nav id="userNav">
    <ul id="userNavList" class="horizNav">
        <jsp:useBean id="theUser" scope="session" type="business.User"/>
        <c:if test="${theUser.firstName != ''}">
            <li><a href="profile.jsp">Profile</a></li>
        </c:if>
        <c:if test="${theUser.firstName == ''}">
            <li><a href="sign_in.jsp">Sign In</a></li>
        </c:if>
        <li><a href="cart">Cart</a></li>
        <li><a href="order?action=viewOrders">My Orders</a></li>
        <li><a href="admin">Administration</a></li>
    </ul>
</nav>
</header>