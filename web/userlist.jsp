<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  User: Adam
  Date: 11/27/2017
  Time: 1:27 PM
--%>
<jsp:useBean id="theUsers" scope="session" type="java.util.List"/>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML>
<html lang="en">
<jsp:include page="header.jsp"/>
<jsp:include page="user-navigation.jsp"/>
<jsp:include page="site-navigation.jsp"/>
<main id="mainContent">
    <section id="orderList">
        <h2>Users List</h2>
        <hr>
        <c:if test="${theUsers.size() == 0}">
            <p>No users found!</p>
        </c:if>
        <c:if test="${theUsers.size() > 0}">
            <table id="usersList">
                <tr>
                    <th>Username</th>
                    <th>Name</th>
                    <th>Email</th>
                    <th></th>
                </tr>

                <c:forEach items="${theUsers}" var="user">
                    <tr>
                        <td align="center"><c:out value='${user.username}'/></td>
                        <td><c:out value="${user.firstName}"/> <c:out value="${user.lastName}"/>  </td>
                        <td><c:out value='${user.emailAddress}'/></td>
                        <td>
                            <form action="admin" method="post">
                                <input type="hidden" value="<c:out value='${user.username}'/>" name="username"/>
                                <input type="hidden" value="editUser" name="action"/>
                                <button type="submit">Edit User</button>
                            </form>
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
