package controller;

import business.User;
import data.UserDB;
import util.PasswordUtil;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;

import static util.PasswordUtil.getSalt;

/**
 * Author: Adam
 * Servlet that controls user login and profile functions.
 */
@WebServlet(name = "UserController", urlPatterns = {"/user"})
public class UserController extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        String defaultURL = "/index.jsp";
        String url = defaultURL;
        if(action != null) {
            switch (action) {
                case "signIn":
                    url = signIn(request, response);
                    break;
                case "registerUser":
                    url = registerUser(request, response);
                    break;
                case "editProfile":
                    url = editProfile(request, response);
                    break;
                case "deleteUser":
                    url = deleteUser(request, response);
                    break;
                default:
                    url = defaultURL;
                    break;
            }
        }
        getServletContext().getRequestDispatcher(url).forward(request, response);
    }

    private String deleteUser(HttpServletRequest request, HttpServletResponse response) {
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("theUser");
        UserDB.removeUser(user.getUsername());
        session.setAttribute("theUser", new User());
        return "/index.jsp";
    }

    private String editProfile(HttpServletRequest request, HttpServletResponse response) {
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("theUser");
        user.setFirstName(request.getParameter("firstName"));
        user.setLastName(request.getParameter("lastName"));
        user.setEmailAddress(request.getParameter("email"));
        user.setAddress1(request.getParameter("address1"));
        user.setAddress2(request.getParameter("address2"));
        user.setCity(request.getParameter("city"));
        user.setStateRegion(request.getParameter("state"));
        user.setPostCode(request.getParameter("postalCode"));
        user.setCountry(request.getParameter("country"));
        UserDB.updateUser(user);
        return "/profile.jsp";
    }

    private String resetPassword(HttpServletRequest request, HttpServletResponse response) {
        HttpSession session = request.getSession();
        String email = request.getParameter("email");
        User user = (User) session.getAttribute("theUser");
        String password;
        if(email.equals(user.getEmailAddress())) {
            password = PasswordUtil.generateRandomPassword();
            request.setAttribute("newPassword", password);
            return "/passwordReset.jsp";
        } else {
            return "/invalidEmail.jsp";
        }
    }

    private String signIn(HttpServletRequest request, HttpServletResponse response) {
        HttpSession session = request.getSession();
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        User user = UserDB.getUser(username);
        if(user != null) {
            try {
                password = PasswordUtil.hashAndSaltPassword(password, user.getSalt());
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            request.setAttribute("correctInfo", "false");
            return "/sign_in.jsp";
        }
        boolean correctInfo = UserDB.checkInfo(username, password);
        if(correctInfo) {
            session.setAttribute("theUser", UserDB.getUser(username));
            request.setAttribute("correctInfo", "true");
            return "/profile.jsp";
        } else {
            request.setAttribute("correctInfo", "false");
            return "/sign_in.jsp";
        }
    }

    private String registerUser(HttpServletRequest request, HttpServletResponse response) {
        HttpSession session = request.getSession();
        User user = new User();
        user.setUsername(request.getParameter("username"));
        String salt = getSalt();
        try {
            user.setPassword(PasswordUtil.hashAndSaltPassword(request.getParameter("password"), salt));
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        user.setFirstName(request.getParameter("firstName"));
        user.setLastName(request.getParameter("lastName"));
        user.setEmailAddress(request.getParameter("email"));
        user.setAddress1(request.getParameter("address1"));
        user.setAddress2(request.getParameter("address2"));
        user.setCity(request.getParameter("city"));
        user.setStateRegion(request.getParameter("state"));
        user.setPostCode(request.getParameter("postCode"));
        user.setCountry(request.getParameter("country"));
        user.setSalt(salt);
        if(UserDB.isUnique(user.getUsername())) {
            UserDB.addUser(user);
            session.setAttribute("theUser", user);
            if("true".equals(request.getParameter("checkoutAttempt"))) {
                return "/cart";
            }
            return "/profile.jsp";
        } else {
            request.setAttribute("userInfo", user);
            request.setAttribute("uniqueUsername", "false");
            return "/register.jsp";
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }
}
