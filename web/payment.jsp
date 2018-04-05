<jsp:useBean id="currentOrder" scope="session" class="business.Order"/>
<%--
  User: Adam
  Date: 11/27/2017
  Time: 12:52 PM
  File: payment.jsp
--%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML>
<html lang="en">
<jsp:include page="header.jsp"/>
<jsp:include page="user-navigation.jsp"/>
<jsp:include page="site-navigation.jsp"/>
<main id="mainContent">
    <section id="payment">
        <h2>Enter Your Payment Information</h2>
        <hr>
        <form method="post" action="order">
            <table>
                <tr>
                    <td align="left">Credit Card Type</td>
                    <td align="left">
                        <select name="cards" title="cardCompany">
                            <option value="visa">Visa</option>
                            <option value="mastercard">MasterCard</option>
                            <option value="discover">Discover</option>
                            <option value="americanExpress">American Express</option>
                        </select>
                    </td>
                </tr>
                <tr>
                    <td align="left">Card Number</td>
                    <td align="left">
                        <input required name="cardNumber" type="number" title="cardNumber">
                    </td>
                </tr>
                <tr>
                    <td align="left">Expiration Date (MM / YYYY)</td>
                    <td align="left">
                        <select name="month" title="expirationMonth">
                            <option value="jan">January</option>
                            <option value="feb">February</option>
                            <option value="mar">March</option>
                            <option value="apr">April</option>
                            <option value="may">May</option>
                            <option value="jun">June</option>
                            <option value="jul">July</option>
                            <option value="aug">August</option>
                            <option value="sep">September</option>
                            <option value="oct">October</option>
                            <option value="nov">November</option>
                            <option value="dec">December</option>
                        </select>
                        <select name="year" title="expirationYear">
                            <option value="2016">2016</option>
                            <option value="2017">2017</option>
                            <option value="2018">2018</option>
                            <option value="2019">2019</option>
                            <option value="2020">2020</option>
                            <option value="2021">2021</option>
                            <option value="2022">2022</option>
                            <option value="2023">2023</option>
                        </select>
                    </td>
                </tr>
                <tr>
                    <td align="left">CVV Code (3-digit)</td>
                    <td align="left">
                        <input required name="cvv" type="number" title="cvvCode">
                    </td>
                </tr>
                <tr>
                    <td align="left">
                        Your card will be charged a total of: $${currentOrder.totalCost}.
                    </td>
                </tr>

            </table>

            <button name="confirmPayment" type="submit">Confirm Payment</button>
            <input type="hidden" name="action" value="confirmOrder">
        </form>
    </section>
</main>
<jsp:include page="footer.jsp"/>
</body>
</html>