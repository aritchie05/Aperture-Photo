<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<jsp:useBean id="userInfo" scope="request" class="business.User"/>
<jsp:useBean id="checkoutAttempt" scope="request" class="java.lang.String"/>
<jsp:useBean id="uniqueUsername" scope="request" class="java.lang.String"/>
<!DOCTYPE HTML>
<html lang="en">
<jsp:include page="header.jsp"/>
<jsp:include page="user-navigation.jsp"/>
<jsp:include page="site-navigation.jsp"/>
<main id="mainContent">
    <section id="register">
        <h2>New User Registration</h2>
        <hr>
        <c:if test='${uniqueUsername.equals("false")}'>
            Username is already taken, please choose another!
        </c:if>
        <c:if test='${checkoutAttempt.equals("true")}'>
            <p><b>Please register to continue to checkout!</b></p>
            <input type="hidden" name="checkoutAttempt" value="true">
        </c:if>
        <form method="post" action="user">
            <input type="hidden" name="action" value="registerUser">
            <table>
                <tr>
                    <td align="left">Username: </td>
                    <td align="left">
                        <input required name="username" type="text" title="username">
                    </td>
                </tr>
                <tr>
                    <td align="left">Password: </td>
                    <td align="left">
                        <input required name="password" type="password" title="password">
                    </td>
                </tr>
                <tr>
                    <td align="left">First Name: </td>
                    <td align="left">
                        <input required name="firstName" type="text" title="firstName" value="${userInfo.firstName}">
                    </td>
                </tr>
                <tr>
                    <td align="left">Last Name: </td>
                    <td align="left">
                        <input required name="lastName" type="text" title="lastName" value="${userInfo.lastName}">
                    </td>
                </tr>
                <tr>
                    <td align="left">Email Address: </td>
                    <td align="left">
                        <input required name="email" type="text" title="email" value="${userInfo.emailAddress}">
                    </td>
                </tr>
                <tr>
                    <td align="left">Address Line 1: </td>
                    <td align="left">
                        <input required name="address1" type="text" title="address1" value="${userInfo.address1}">
                    </td>
                </tr>
                <tr>
                    <td align="left">Address Line 2: </td>
                    <td align="left">
                        <input name="address2" type="text" title="address2" value="${userInfo.address2}">
                    </td>
                </tr>
                <tr>
                    <td align="left">City: </td>
                    <td align="left">
                        <input required name="city" type="text" title="city" value="${userInfo.city}">
                    </td>
                </tr>
                <tr>
                    <td align="left">State/Region: </td>
                    <td align="left">
                        <input required name="state" type="text" title="state" value="${userInfo.stateRegion}">
                    </td>
                </tr>
                <tr>
                    <td align="left">Postal Code: </td>
                    <td align="left">
                        <input required name="postCode" type="text" title="postCode" value="${userInfo.postCode}">
                    </td>
                </tr>
                <tr>
                    <td align="left">Country: </td>
                    <td align="left">
                        <input required name="country" type="text" title="country" value="${userInfo.country}">
                    </td>
                </tr>
            </table>
            <c:if test='${checkoutAttempt.equals("true")}'>
                <input type="hidden" name="checkoutAttempt" value="true">
            </c:if>
            <button name="confirmRegister" type="submit">Confirm Register</button>
        </form>
    </section>
</main>

<jsp:include page="footer.jsp"/>
</body>
</html>