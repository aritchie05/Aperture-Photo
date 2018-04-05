<%--
  Created by IntelliJ IDEA.
  User: Adam
  Date: 11/27/2017
  Time: 3:02 PM
  To change this template use File | Settings | File Templates.
--%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML>
<html lang="en">
<jsp:include page="/header.jsp"/>
<jsp:include page="/user-navigation.jsp"/>
<jsp:include page="/site-navigation.jsp"/>
<main id="mainContent">
    <section id="adminLogin">
        <h2>Administrator Login</h2>
        <hr>
        <p>Please enter your username and password to continue.</p>
        <form action="j_security_check" method="get">
            <table>
                <tr>
                    <td align="left">Username: </td>
                    <td align="left">
                        <input name="j_username" type="text" title="username">
                    </td>
                </tr>
                <tr>
                    <td align="left">Password: </td>
                    <td align="left">
                        <input name="j_password" type="password" title="password">
                    </td>
                </tr>
            </table>
            <button type="submit">Submit</button>
        </form>
    </section>
</main>
<jsp:include page="/footer.jsp"/>
</body>
</html>