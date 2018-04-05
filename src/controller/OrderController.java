package controller;

import business.*;
import data.OrderDB;
import data.ProductDB;
import data.UserDB;
import util.StoreUtil;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;

/**
 * Author: Adam
 * Servlet that controls the pipeline for ordering items in the cart.
 */
@WebServlet(name="OrderController", urlPatterns ={"/order", "/cart"})
public class OrderController extends HttpServlet {
    /**
     * Default URL that this servlet will dispatch to if no other URL is specified.
     */
    private static final String defaultURL = "/cart.jsp";

    /**
     * Checks the HttpRequest object for an action parameter, and gets the correct URL from the method that corresponds
     * with the action parameter. Dispatches to default URL if no action parameter is found.
     * @param request the HttpRequest object
     * @param response the HttpResponse object
     * @throws ServletException if a Servlet Exception occurs
     * @throws IOException if an Input-Output Exception occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        HttpSession session = request.getSession();
        Cart cart = (Cart) session.getAttribute("theShoppingCart");
        if(cart == null) {
            cart = new Cart();
            session.setAttribute("theShoppingCart", cart);
        }
        String url = "";
        if(action != null) {
            switch (action) {
                case "updateCart":
                    url = updateCart(request, response);
                    break;
                case "checkout":
                    url = checkout(request, response);
                    break;
                case "clearCart":
                    url = clearCart(request, response);
                    break;
                case "viewOrders":
                    url = viewOrders(request, response);
                    break;
                case "confirmOrder":
                    url = confirmOrder(request, response);
                    break;
                default:
                    url = defaultURL;
                    break;
            }
        } else {
            url = defaultURL;
        }
        getServletContext().getRequestDispatcher(url).forward(request, response);
    }

    /**
     * Sets the order within the current session to paid, and adds the order to the database. Also clears the cart in
     * the current session.
     * @param request the HttpRequest object
     * @param response the HttpResponse object
     * @return the URL that will be dispatched to
     */
    private String confirmOrder(HttpServletRequest request, HttpServletResponse response) {
        HttpSession session = request.getSession();
        Order order = (Order) session.getAttribute("currentOrder");
        order.setPaid(true);
        OrderDB.addOrder(order);
        clearCart(request, response);
        return "/paidorder.jsp";
    }

    /**
     * Displays all orders for the current user.
     * @param request the HttpRequest object
     * @param response the HttpResponse object
     * @return the URL that will be dispatched to
     */
    private String viewOrders(HttpServletRequest request, HttpServletResponse response) {
        HttpSession session = request.getSession();
        User sessionUser = (User) session.getAttribute("theUser");
        User user;
        if(sessionUser != null) {
            user = UserDB.getUser(sessionUser.getUsername());
        } else {
            user = new User();
        }
        ArrayList<Order> orders = new ArrayList<>();
        if(user != null) {
            orders = OrderDB.getOrdersForUser(user.getUserID());
        }
        session.setAttribute("theOrders", orders);
        return "/orderlist.jsp";
    }

    /**
     * Clears the cart for the current session.
     * @param request the HttpRequest object
     * @param response the HttpResponse object
     * @return the URL that will be dispatched to
     */
    private String clearCart(HttpServletRequest request, HttpServletResponse response) {
        HttpSession session = request.getSession();
        Cart cart = (Cart) session.getAttribute("theShoppingCart");
        if(cart != null) {
            cart.emptyCart();
        }
        session.setAttribute("theShoppingCart", cart);
        return "/index.jsp";
    }

    /**
     * Updates the cart with the productList parameter contained in the HTTP request.
     * @param request the HttpRequest object
     * @param response the HttpResponse object
     * @return the URL that will be dispatched to
     */
    private String updateCart(HttpServletRequest request, HttpServletResponse response) {
        HttpSession session = request.getSession();
        Cart cart = (Cart) session.getAttribute("theShoppingCart");
        if(cart == null) {
            cart = new Cart();
        }

        String[] pList = request.getParameterValues("productList");



        for(String productCode : pList) {
            if(StoreUtil.isValidProductCode(productCode)) {
                boolean inCart = cart.contains(productCode);
                int quantity;
                Product product = ProductDB.getProduct(productCode);
                try {
                    quantity = Integer.parseInt(request.getParameter(productCode));
                } catch (NumberFormatException e) {
                    continue;
                }
                if(quantity == 0) {
                    cart.removeItem(productCode);
                } else if(quantity > 0 && inCart) {
                    if(quantity != cart.getOrderItem(product).getQuantity()) {
                        cart.addItem(product, quantity);
                    }
                } else if(quantity > 0 && !inCart) {
                    cart.addItem(product, quantity);
                }
            }
        }

        session.setAttribute("theShoppingCart", cart);
        return defaultURL;
    }

    /**
     * Performs checkout for the current cart and user within the session, creating an order.
     * @param request the HttpRequest object
     * @param response the HttpResponse object
     * @return the URL that will be dispatched to
     */
    private String checkout(HttpServletRequest request, HttpServletResponse response) {
        HttpSession session = request.getSession();
        User sessionUser = (User) session.getAttribute("theUser");
        User user = UserDB.getUser(sessionUser.getUsername());
        if(user != null && user.getFirstName().equals("")) {
            request.setAttribute("checkoutAttempt", "true");
            return "/register.jsp";
        }
        Order order = new Order();
        Cart cart = (Cart) session.getAttribute("theShoppingCart");
        order.setItems(cart.getItems());
        order.setUser(user);
        order.setDate(new Date().toString());
        order.setPaid(false);
        order.setTaxRate(.11);
        order.setSubtotal(cart.getTotal());
        order.setTotalCost(StoreUtil.toMoneyFormat(order.getTaxRate() * cart.getTotal() + cart.getTotal()));
        session.setAttribute("currentOrder", order);
        return "/order.jsp";
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }
}
