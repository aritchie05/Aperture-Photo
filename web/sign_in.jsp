<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
    Document   : about
    Created on : Oct 6, 2017, 10:11:53 PM
    Author     : Adam
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML>
<html lang="en">
<jsp:include page="header.jsp"/>
<jsp:include page="user-navigation.jsp"/>
<jsp:include page="site-navigation.jsp"/>
<main id="mainContent">
    <section id="signIn">
        <h2>Sign In</h2>
        <hr>
        <jsp:useBean id="correctInfo" scope="request" class="java.lang.String"/>
        <c:if test='${correctInfo.equals("false")}'>
            <b>Incorrect username or password!</b> <br>
        </c:if>
        <form action="user" method="post">
            <table>
                <tr>
                    <td align="left">Username: </td>
                    <td align="left">
                        <input name="username" type="text" title="username">
                    </td>
                </tr>
                <tr>
                    <td align="left">Password: </td>
                    <td align="left">
                        <input name="password" type="password" title="password">
                    </td>
                </tr>
            </table>
            <input type="hidden" name="action" value="signIn">
            <button type="submit">Sign In</button>
        </form> <br>
        <a href="register.jsp">New to the shop? Register here!</a>
    </section>
</main>
<jsp:include page="footer.jsp"/>
</body>
</html>