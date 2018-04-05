package controller;

import business.Order;
import business.Product;
import business.User;
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

/**
 * Author: Adam
 * Servlet that controls administrator functions for the web application.
 */
@WebServlet(name = "AdminController", urlPatterns = {"/admin"})
public class AdminController extends HttpServlet {
    /**
     * Default URL that this servlet will dispatch to if no other URL is specified.
     */
    private static final String defaultURL = "/admin.jsp";

    /**
     * Checks the HttpRequest object for an action parameter, and gets the correct URL from the method that corresponds
     * with the action parameter. Dispatches to default URL if no action parameter is found.
     * @param request the HttpRequest object
     * @param response the HttpResponse object
     * @throws ServletException if a Servlet Exception occurs
     * @throws IOException if an Input-Output Exception occurs
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        String url;
        if(action != null) {
            switch (action) {
                case "editOrders":
                    url = editOrders(request, response);
                    break;
                case "editUsers":
                    url = editUsers(request, response);
                    break;
                case "editCatalog":
                    url = editCatalog(request, response);
                    break;
                case "editProduct":
                    url = editProduct(request, response);
                    break;
                case "submitProductChanges":
                    url = submitProductChanges(request, response);
                    break;
                case "createProduct":
                    url = createProduct(request, response);
                    break;
                case "deleteProduct":
                    url = deleteProduct(request, response);
                    break;
                case "editUser":
                    url = editUser(request, response);
                    break;
                case "removeOrder":
                    url = removeOrder(request, response);
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

    private String deleteProduct(HttpServletRequest request, HttpServletResponse response) {
        String code = request.getParameter("deleteCode");
        ProductDB.deleteProduct(code);
        return "/catalog";
    }

    private String createProduct(HttpServletRequest request, HttpServletResponse response) {
        request.setAttribute("product", new Product());
        return "/edit_product.jsp";
    }

    private String submitProductChanges(HttpServletRequest request, HttpServletResponse response) {
        Product product = new Product();
        String prevCode = request.getParameter("oldCode");
        String newCode = request.getParameter("productCode");
        String codeError = "";
        Product otherProduct = ProductDB.getProduct(newCode);
        Product oldProduct = ProductDB.getProduct(prevCode);
        product.setProductCode(newCode);
        product.setProductName(request.getParameter("productName"));
        product.setCategory(request.getParameter("category"));
        product.setDescription(request.getParameter("description"));
        product.setImageURL(request.getParameter("imageURL"));
        try {
            product.setPrice(Double.parseDouble(request.getParameter("price")));
        } catch (NumberFormatException e) {
            e.printStackTrace();
            if (oldProduct != null) {
                product.setPrice(oldProduct.getPrice());
            }
        }
        if (StoreUtil.isValidProductCode(newCode)) {
            if (!newCode.equals(prevCode) && otherProduct != null) {
                codeError = "That code is already taken by " + otherProduct.getProductName();
            }
        } else {
            codeError = "Invalid product code format! Must be 4 digits and start with 00.";
        }
        request.setAttribute("codeError", codeError);
        if(codeError.equals("")) {
            ProductDB.deleteProduct(prevCode);
            ProductDB.addProduct(product);
            return "/catalog";
        } else {
            request.setAttribute("product", oldProduct);
            return "/edit_product.jsp";
        }
    }

    private String editProduct(HttpServletRequest request, HttpServletResponse response) {
        String code = request.getParameter("productCode");
        Product product = ProductDB.getProduct(code);
        request.setAttribute("product", product);
        return "/edit_product.jsp";
    }

    private String removeOrder(HttpServletRequest request, HttpServletResponse response) {
        OrderDB.removeOrder(Integer.parseInt(request.getParameter("orderNumber")));
        return "/admin?action=editOrders";
    }

    private String editUser(HttpServletRequest request, HttpServletResponse response) {
        String username = request.getParameter("username");
        User user = UserDB.getUser(username);
        HttpSession session = request.getSession();
        session.setAttribute("theUser", user);
        return "/edit_profile.jsp";
    }

    private String editCatalog(HttpServletRequest request, HttpServletResponse response) {
        request.setAttribute("edit", "true");
        return "/catalog";
    }

    private String editUsers(HttpServletRequest request, HttpServletResponse response) {
        HttpSession session = request.getSession();
        session.setAttribute("theUsers", UserDB.getAllUsers());
        return "/userlist.jsp";
    }

    /**
     * Displays a list of all orders within the database, as well as unlocking functionality to remove them.
     ** @param request the HttpRequest object
     * @param response the HttpResponse object
     * @return the URL that will be dispatched to
     */
    private String editOrders(HttpServletRequest request, HttpServletResponse response) {
        ArrayList<Order> orders = OrderDB.getAllOrders();
        HttpSession session = request.getSession();
        session.setAttribute("theOrders", orders);
        request.setAttribute("admin", "true");
        return "/orderlist.jsp";
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }
}
