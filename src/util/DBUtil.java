package util;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * Author: Joel Murach and Michael Urban
 * Source: Murach's Java Servlets and JSP 3rd Edition
 * Publisher: Shroff Publishers & Distributors
 * Utility class for the mySQL database connection.
 */
public class DBUtil {
    public static void closeStatement(Statement s) {
        try {
            if(s != null) {
                s.close();
            }
        } catch(SQLException e) {
            System.out.println(e);
        }
    }

    public static void closePreparedStatement(Statement ps) {
        try {
            if(ps != null) {
                ps.close();
            }
        } catch(SQLException e) {
            System.out.println(e);
        }
    }

    public static void closeResultSet(ResultSet rs) {
        try {
            if(rs != null) {
                rs.close();
            }
        } catch(SQLException e) {
            System.out.println(e);
        }
    }
}
