/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package data;

import business.User;
import util.DBUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * Author: Adam
 * Handles database connectivity for users.
 */
public class UserDB {

    public static void addUser(String firstName, String lastName, String email, String address1, String address2,
                               String city, String state, String zip, String country) {
        User user = new User();
        user.setFirstName(firstName);
        user.setLastName(lastName);
        user.setEmailAddress(email);
        user.setAddress1(address1);
        user.setAddress2(address2);
        user.setCity(city);
        user.setStateRegion(state);
        user.setPostCode(zip);
        user.setCountry(country);
        addUser(user);
    }

    public static void addUser(User u) {
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        PreparedStatement ps = null;

        String query = "INSERT INTO user " +
                "(LastName, FirstName, Email, Address1, Address2, City, State, PostalCode, Country, Username, Password, Salt)"
                + "VALUES (?,?,?,?,?,?,?,?,?,?,?,?)";

        try {
            ps = connection.prepareStatement(query);
            ps.setString(1, u.getLastName());
            ps.setString(2, u.getFirstName());
            ps.setString(3, u.getEmailAddress());
            ps.setString(4, u.getAddress1());
            ps.setString(5, u.getAddress2());
            ps.setString(6, u.getCity());
            ps.setString(7, u.getStateRegion());
            ps.setString(8, u.getPostCode());
            ps.setString(9, u.getCountry());
            ps.setString(10, u.getUsername());
            ps.setString(11, u.getPassword());
            ps.setString(12, u.getSalt());
            ps.executeUpdate();
        } catch(SQLException e) {
            e.printStackTrace();
        } finally {
            DBUtil.closePreparedStatement(ps);
            pool.freeConnection(connection);
        }
    }
    
    public static ArrayList<User> getAllUsers() {
        ArrayList<User> users = new ArrayList<>();
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;

        String query = "SELECT * FROM User";

        try {
            ps = connection.prepareStatement(query);
            rs = ps.executeQuery();
            User u;
            while(rs.next()) {
                u = new User();
                u.setUserID(rs.getInt("UserID"));
                u.setLastName(rs.getString("LastName"));
                u.setFirstName(rs.getString("FirstName"));
                u.setEmailAddress(rs.getString("Email"));
                u.setAddress1(rs.getString("Address1"));
                u.setAddress2(rs.getString("Address2"));
                u.setCity(rs.getString("City"));
                u.setStateRegion(rs.getString("State"));
                u.setPostCode(rs.getString("PostalCode"));
                u.setCountry(rs.getString("Country"));
                u.setUsername(rs.getString("Username"));
                u.setPassword(rs.getString("Password"));
                u.setSalt(rs.getString("Salt"));
                users.add(u);
            }
        } catch(SQLException e) {
            e.printStackTrace();
        } finally {
            DBUtil.closePreparedStatement(ps);
            DBUtil.closeResultSet(rs);
            pool.freeConnection(connection);
        }
        return users;
    }

    public static User getUser(String username) {
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;

        String query = "SELECT * FROM User WHERE Username = ?";

        try {
            ps = connection.prepareStatement(query);
            ps.setString(1, username);
            rs = ps.executeQuery();
            if(rs.next()) {
                return getUser(rs.getInt("UserID"));
            }
        } catch(SQLException e) {
            e.printStackTrace();
            return null;
        } finally {
            DBUtil.closePreparedStatement(ps);
            DBUtil.closeResultSet(rs);
            pool.freeConnection(connection);
        }
        return null;
    }

    public static User getUser(int userID) {
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;

        String query = "SELECT * FROM User WHERE UserID = ?";

        try {
            ps = connection.prepareStatement(query);
            ps.setInt(1, userID);
            rs = ps.executeQuery();
            User u = null;
            while(rs.next()) {
                u = new User();
                u.setUserID(userID);
                u.setLastName(rs.getString("LastName"));
                u.setFirstName(rs.getString("FirstName"));
                u.setEmailAddress(rs.getString("Email"));
                u.setAddress1(rs.getString("Address1"));
                u.setAddress2(rs.getString("Address2"));
                u.setCity(rs.getString("City"));
                u.setStateRegion(rs.getString("State"));
                u.setPostCode(rs.getString("PostalCode"));
                u.setCountry(rs.getString("Country"));
                u.setUsername(rs.getString("Username"));
                u.setPassword(rs.getString("Password"));
                u.setSalt(rs.getString("Salt"));
            }
            return u;
        } catch(SQLException e) {
            System.out.println(e.getMessage());
            return null;
        } finally {
            DBUtil.closePreparedStatement(ps);
            DBUtil.closeResultSet(rs);
            pool.freeConnection(connection);
        }
    }

    public static boolean isUnique(String username) {
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;

        String query = "SELECT Username FROM User WHERE Username = ?";

        try {
            ps = connection.prepareStatement(query);
            ps.setString(1, username);
            rs = ps.executeQuery();
            return !rs.next();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBUtil.closePreparedStatement(ps);
            DBUtil.closeResultSet(rs);
            pool.freeConnection(connection);
        }

        return false;
    }

    public static boolean checkInfo(String username, String password) {
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;

        String query = "SELECT Password FROM User WHERE Username = ?";

        try {
            ps = connection.prepareStatement(query);
            ps.setString(1, username);
            rs = ps.executeQuery();
            if(rs.next()) {
                return password.equals(rs.getString("Password"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBUtil.closePreparedStatement(ps);
            DBUtil.closeResultSet(rs);
            pool.freeConnection(connection);
        }

        return false;
    }

    public static void removeUser(String username) {
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        PreparedStatement ps = null;

        String query = "DELETE FROM User WHERE Username = ?";

        try {
            ps = connection.prepareStatement(query);
            ps.setString(1, username);
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBUtil.closePreparedStatement(ps);
            pool.freeConnection(connection);
        }
    }

    public static void updateUser(User user) {
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        PreparedStatement ps = null;

        String query = "UPDATE user SET LastName = ?, FirstName = ?, Email = ?, Address1 = ?, Address2 = ?, " +
                "City = ?, State = ?, PostalCode = ?, Country = ? WHERE Username = ?";

        try {
            ps = connection.prepareStatement(query);
            ps.setString(1, user.getLastName());
            ps.setString(2, user.getFirstName());
            ps.setString(3, user.getEmailAddress());
            ps.setString(4, user.getAddress1());
            ps.setString(5, user.getAddress2());
            ps.setString(6, user.getCity());
            ps.setString(7, user.getStateRegion());
            ps.setString(8, user.getPostCode());
            ps.setString(9, user.getCountry());
            ps.setString(10, user.getUsername());
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBUtil.closePreparedStatement(ps);
            pool.freeConnection(connection);
        }
    }
}
