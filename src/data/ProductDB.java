/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package data;

import business.Product;
import util.DBUtil;
import util.StoreUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * Author: Adam
 * Handles database connectivity for products.
 */
public class ProductDB {
    public static void addProduct(Product p) {
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        PreparedStatement ps = null;

        String query = "INSERT INTO Product " +
                "(ProductCode, Name, Category, Description, Price, ImageURL) " +
                "VALUES (?,?,?,?,?,?)";

        try {
            ps = connection.prepareStatement(query);
            ps.setString(1, p.getProductCode());
            ps.setString(2, p.getProductName());
            ps.setString(3, p.getCategory());
            ps.setString(4, p.getDescription());
            ps.setDouble(5, StoreUtil.toMoneyFormat(p.getPrice()));
            ps.setString(6, p.getImageURL());
            ps.executeUpdate();
        } catch(SQLException e) {
            System.out.println(e.getLocalizedMessage());
        } finally {
            DBUtil.closePreparedStatement(ps);
            pool.freeConnection(connection);
        }
    }

    public static ArrayList<Product> getAllProducts() {
        ArrayList<Product> products = new ArrayList<>();
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;

        String query = "SELECT * FROM Product";

        try {
            ps = connection.prepareStatement(query);
            rs = ps.executeQuery();
            Product p;
            while(rs.next()) {
                p = new Product();
                p.setProductName(rs.getString("Name"));
                p.setProductCode(rs.getString("ProductCode"));
                p.setCategory(rs.getString("Category"));
                p.setDescription(rs.getString("Description"));
                p.setPrice(rs.getDouble("Price"));
                p.setImageURL(rs.getString("ImageURL"));
                products.add(p);
            }
        } catch(SQLException e) {
            e.printStackTrace();
        } finally {
            DBUtil.closePreparedStatement(ps);
            DBUtil.closeResultSet(rs);
            pool.freeConnection(connection);
        }
        return products;
    }

    public static Product getProduct(String productCode) {
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;

        String query = "SELECT * FROM Product "
                + "WHERE ProductCode = ?";

        try {
            ps = connection.prepareStatement(query);
            ps.setString(1, productCode);
            rs = ps.executeQuery();
            Product p = null;
            if(rs.next()) {
                p = new Product();
                p.setProductName(rs.getString("Name"));
                p.setProductCode(rs.getString("ProductCode"));
                p.setCategory(rs.getString("Category"));
                p.setDescription(rs.getString("Description"));
                p.setPrice(rs.getDouble("Price"));
                p.setImageURL(rs.getString("ImageURL"));
            }
            return p;
        } catch(SQLException e) {
            e.printStackTrace();
            return null;
        } finally {
            DBUtil.closeResultSet(rs);
            DBUtil.closePreparedStatement(ps);
            pool.freeConnection(connection);
        }
    }

    public static void deleteProduct(String productCode) {
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        PreparedStatement ps = null;

        String query = "DELETE FROM Product WHERE ProductCode = ?";

        try {
            ps = connection.prepareStatement(query);
            ps.setString(1, productCode);
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("SQL Exception!");
        } finally {
            DBUtil.closePreparedStatement(ps);
            pool.freeConnection(connection);
        }
    }
}
