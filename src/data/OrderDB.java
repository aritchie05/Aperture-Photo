package data;

import business.Order;
import business.OrderItem;
import util.DBUtil;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Author: Adam
 * Handles database connectivity for orders and order items.
 */
public class OrderDB {

    public static void addOrder(Order o) {
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        PreparedStatement ps = null;

        String query = "INSERT INTO Orders " +
                "(Date, UserID, TaxRate, SubTotal, TotalCost, Paid) " +
                "VALUES(?,?,?,?,?,?)";
        try {
            ps = connection.prepareStatement(query);
            ps.setString(1, o.getDate());
            ps.setInt(2, UserDB.getUser(o.getUser().getUsername()).getUserID());
            ps.setDouble(3, o.getTaxRate());
            ps.setDouble(4, o.getSubtotal());
            ps.setDouble(5, o.getTotalCost());
            ps.setBoolean(6, o.isPaid());
            ps.executeUpdate();
        } catch(SQLException e) {
            e.printStackTrace();
        }

        for(OrderItem orderItem : o.getItems()) {
            query = "INSERT INTO orderitem" +
                    "(OrderNumber, ProductCode, Quantity)" +
                    "VALUES (?,?,?)";

            try {
                ps = connection.prepareStatement(query);
                ps.setInt(1, getMostRecentOrderNumber());
                ps.setString(2, orderItem.getProduct().getProductCode());
                ps.setInt(3, orderItem.getQuantity());
                ps.executeUpdate();
            } catch(SQLException e) {
                e.printStackTrace();
            }
        }
        DBUtil.closePreparedStatement(ps);
        pool.freeConnection(connection);
    }

    private static int getMostRecentOrderNumber() {
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        Statement statement = null;
        ResultSet rs = null;
        int orderNumber = 0;
        String query = "SELECT * FROM orders ORDER BY OrderNumber DESC LIMIT 1";

        try {
            statement = connection.createStatement();
            rs = statement.executeQuery(query);
            rs.next();
            orderNumber = rs.getInt("OrderNumber");
        } catch(SQLException e) {
            e.printStackTrace();
        } finally {
            DBUtil.closeStatement(statement);
            DBUtil.closeResultSet(rs);
            pool.freeConnection(connection);
        }

        return orderNumber;
    }

    public static ArrayList<Order> getAllOrders() {
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;
        ArrayList<Order> orders = new ArrayList<>();

        String query = "SELECT * FROM orders";

        try {
            ps = connection.prepareStatement(query);
            rs = ps.executeQuery();
            Order o;
            while(rs.next()) {
                o = new Order();
                o.setOrderNumber(rs.getInt("OrderNumber"));
                o.setDate(rs.getString("Date"));
                o.setUser(UserDB.getUser(rs.getInt("UserID")));
                o.setTaxRate(rs.getDouble("TaxRate"));
                o.setSubtotal(rs.getDouble("SubTotal"));
                o.setTotalCost(rs.getDouble("TotalCost"));
                o.setPaid(rs.getBoolean("Paid"));
                o.setItems(getOrderItems(o.getOrderNumber()));
                orders.add(o);
            }
        } catch(SQLException e) {
            e.printStackTrace();
        } finally {
            DBUtil.closePreparedStatement(ps);
            DBUtil.closeResultSet(rs);
            pool.freeConnection(connection);
        }
        return orders;
    }

    private static List<OrderItem> getOrderItems(int orderNumber) {
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;
        ArrayList<OrderItem> orderItems = new ArrayList<>();

        String query = "SELECT * FROM orderitem WHERE OrderNumber = ?";

        try {
            ps = connection.prepareStatement(query);
            ps.setInt(1, orderNumber);
            rs = ps.executeQuery();
            while(rs.next()) {
                OrderItem item = new OrderItem();
                item.setProduct(ProductDB.getProduct(rs.getString("ProductCode")));
                item.setQuantity(rs.getInt("Quantity"));
                orderItems.add(item);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBUtil.closePreparedStatement(ps);
            DBUtil.closeResultSet(rs);
            pool.freeConnection(connection);
        }

        return orderItems;
    }

    public static ArrayList<Order> getOrdersForUser(int userID) {
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;
        ArrayList<Order> orders = new ArrayList<>();

        String query = "SELECT * FROM orders WHERE UserID = ?";

        try {
            ps = connection.prepareStatement(query);
            ps.setInt(1, userID);
            rs = ps.executeQuery();
            while(rs.next()) {
                Order o = new Order();
                o.setOrderNumber(rs.getInt("OrderNumber"));
                o.setDate(rs.getString("Date"));
                o.setUser(UserDB.getUser(rs.getInt("UserID")));
                o.setTaxRate(rs.getDouble("TaxRate"));
                o.setSubtotal(rs.getDouble("SubTotal"));
                o.setTotalCost(rs.getDouble("TotalCost"));
                o.setPaid(rs.getBoolean("Paid"));
                o.setItems(getOrderItems(o.getOrderNumber()));
                orders.add(o);
            }

        } catch(SQLException e) {
            e.printStackTrace();
        } finally {
            DBUtil.closePreparedStatement(ps);
            DBUtil.closeResultSet(rs);
            pool.freeConnection(connection);
        }
        return orders;
    }

    public static void removeOrder(int orderNumber) {
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        PreparedStatement ps = null;

        String query = "DELETE FROM orders WHERE OrderNumber = ?";
        String query2 = "DELETE FROM orderitem WHERE OrderNumber = ?";

        try {
            ps = connection.prepareStatement(query);
            ps.setInt(1, orderNumber);
            ps.executeUpdate();
            ps = connection.prepareStatement(query2);
            ps.setInt(1, orderNumber);
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBUtil.closePreparedStatement(ps);
            pool.freeConnection(connection);
        }
    }
}

