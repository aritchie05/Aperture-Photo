<%--
  Created by IntelliJ IDEA.
  User: Adam
  Date: 11/27/2017
  Time: 1:43 PM
  To change this template use File | Settings | File Templates.
--%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML>
<html lang="en">
<jsp:include page="/header.jsp"/>
<jsp:include page="/user-navigation.jsp"/>
<jsp:include page="/site-navigation.jsp"/>
<main id="mainContent">
    <section id="admin">
        <h2>Administrator Menu</h2>
        <hr>
        <form action="admin" method="get">
            <input type="hidden" name="action" value="editOrders">
            <button type="submit" name="editOrders">Edit Orders</button>
        </form> <br>
        <form action="admin" method="get">
            <input type="hidden" name="action" value="editUsers">
            <button type="submit" name="editUsers">Edit Users</button>
        </form> <br>
        <form action="admin" method="get">
            <input type="hidden" name="action" value="editCatalog">
            <button type="submit" name="editCatalog">Edit Catalog</button>
        </form> <br>
    </section>
</main>
<jsp:include page="/footer.jsp"/>
</body>
</html>