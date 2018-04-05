<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:useBean id="codeError" scope="request" class="java.lang.String"/>
<jsp:useBean id="product" scope="request" class="business.Product"/>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML>
<html lang="en">
<jsp:include page="header.jsp"/>
<jsp:include page="user-navigation.jsp"/>
<jsp:include page="site-navigation.jsp"/>
<main id="mainContent">
    <section id="editItem">
        <h2>Edit Product: ${product.productName}</h2>
        <hr>
        <b>${codeError.toString()}</b>
        <form action="admin" method="post">
            <input type="hidden" name="action" value="submitProductChanges">
            <input type="hidden" name="oldCode" value="${product.productCode}">
            <table>
                <tr>
                    <td align="left">Product Code: </td>
                    <td align="left"><input required type="text" name="productCode" value="${product.productCode}" title="productCode"></td>
                </tr>
                <tr>
                    <td align="left">Product Name: </td>
                    <td align="left"><input required type="text" name="productName" value="${product.productName}" title="productName"></td>
                </tr>
                <tr>
                    <td align="left">Category: </td>
                    <td align="left">
                        <select name="category" title="category" required>
                            <c:if test='${product.category.equals("Cameras")}'>
                                <option value="Cameras" selected>Cameras</option>
                                <option value="Lenses">Lenses</option>
                            </c:if>
                            <c:if test='${!product.category.equals("Cameras")}'>
                                <option value="Cameras">Cameras</option>
                                <option value="Lenses" selected>Lenses</option>
                            </c:if>
                        </select>
                    </td>
                </tr>
                <tr>
                    <td align="left">Description: </td>
                    <td align="left"><input required type="text" name="description" value="${product.description}" title="description"></td>
                </tr>
                <tr>
                    <td align="left">Image URL: </td>
                    <td align="left"><input required type="text" name="imageURL" value="${product.imageURL}" title="imageURL"></td>
                </tr>
                <tr>
                    <td align="left">Price: </td>
                    <td align="left"><input required type="text" name="price" value="${product.price}" title="price"></td>
                </tr>
            </table>
            <button type="submit">Submit Changes</button>
        </form> <br>
        <p><b>WARNING: This action is irreversible!</b></p>
        <form action="admin" method="post">
            <input type="hidden" name="action" value="deleteProduct">
            <input type="hidden" name="deleteCode" value="${product.productCode}">
            <button type="submit">Delete Product</button>
        </form>
    </section>
</main>
<jsp:include page="footer.jsp"/>
</body>
</html>