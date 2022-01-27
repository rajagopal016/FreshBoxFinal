package com.freshbox.freshbox.dao;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import com.freshbox.freshbox.model.*;

/**
 * AbstractDAO.java This DAO class provides CRUD database operations for the
 * table users in the database.
 * 
 * @author sourcecodeexamples
 *
 */
@Component
public class UserDAO1 {
    private String jdbcURL = "jdbc:mysql://localhost:3306/freshbox";
    private String jdbcUsername = "root";
    private String jdbcPassword = "1234";

    private static final String INSERT_USERS_SQL = "INSERT INTO table_user VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
        

    private static final String SELECT_USER_BY_ID = "select * from table_user where username =?";
    private static final String SELECT_USER_FOR_LOGIN = "select * from table_user where username =? and password=?";
    private static final String SELECT_ALL_USERS = "select * from users";
    private static final String DELETE_USERS_SQL = "delete from users where id = ?;";
    private static final String UPDATE_USERS_SQL = "update table_user set password = ? where username = ?";
    private static final String UPDATE_USERS_CART_SQL = "update table_user set table_user_cart = ?, table_user_qty = ? where username = ?";

    public UserDAO1() {}

    protected Connection getConnection() {
        Connection connection = null;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            connection = DriverManager.getConnection(jdbcURL, jdbcUsername, jdbcPassword);
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return connection;
    }
    
    public User getUser(String id) {
        User user = null;
        // Step 1: Establishing a Connection
        try (Connection connection = getConnection();
            // Step 2:Create a statement using connection object
            PreparedStatement preparedStatement = connection.prepareStatement(SELECT_USER_BY_ID);) {
            preparedStatement.setString(1, id);
            System.out.println(preparedStatement);
            // Step 3: Execute the query or update query
            ResultSet rs = preparedStatement.executeQuery();

            // Step 4: Process the ResultSet object.
            while (rs.next()) {
                String user_id = rs.getString("username");
                String name = rs.getString("table_user_name");
                String mobile = rs.getString("table_user_mobile");
                String address = rs.getString("table_user_address");
                int age = rs.getInt("table_user_age");
                String password = rs.getString("password");
                String email = rs.getString("table_user_email");
                String cart = rs.getString("table_user_cart");
                String qty = rs.getString("table_user_qty");
                user = new User(id, name, mobile, address, age, password, email, cart, qty);
            }
        } catch (SQLException e) {
            printSQLException(e);
        }
        return user;
    }
    
    public User SELECT_USER_FOR_LOGIN (String id, String pwd) {
        User user = null;
        // Step 1: Establishing a Connection
        try (Connection connection = getConnection();
            // Step 2:Create a statement using connection object
            PreparedStatement preparedStatement = connection.prepareStatement(SELECT_USER_FOR_LOGIN);) {
            preparedStatement.setString(1, id);
            preparedStatement.setString(2, pwd);
            // Step 3: Execute the query or update query
            ResultSet rs = preparedStatement.executeQuery();

            // Step 4: Process the ResultSet object.
            while (rs.next()) {
                String user_id = rs.getString("username");
                String name = rs.getString("table_user_name");
                String mobile = rs.getString("table_user_mobile");
                String address = rs.getString("table_user_address");
                int age = rs.getInt("table_user_age");
                String password = rs.getString("password");
                String email = rs.getString("table_user_email");
                user = new User(id, name, mobile, address, age, password, email);
            }
        } catch (SQLException e) {
            printSQLException(e);
        }
        return user;
    }
    
    public Exception INSERT_USER (String id, String pwd, String name, String age, String mobile, String address, String email) {
        User user = null;
        // Step 1: Establishing a Connection
        try (Connection connection = getConnection();
            // Step 2:Create a statement using connection object
            PreparedStatement preparedStatement = connection.prepareStatement(INSERT_USERS_SQL);) {
            preparedStatement.setString(1, id);
            preparedStatement.setString(6, pwd);
            preparedStatement.setString(2, name);
            preparedStatement.setString(5, age);
            preparedStatement.setString(3, mobile);
            preparedStatement.setString(4, address);
            preparedStatement.setString(7, email);
            preparedStatement.setString(8, "");
            
            // Step 3: Execute the query or update query
            preparedStatement.executeUpdate();

            // Step 4: Process the ResultSet object.
            
        } catch (SQLException e) {
            printSQLException(e);
            return e;
        }
        return null;
    }
    
    public Exception UPDATE_PASSWORD (String id, String pwd) {
        User user = null;
        // Step 1: Establishing a Connection
        try (Connection connection = getConnection();
            // Step 2:Create a statement using connection object
            PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_USERS_SQL);) {
            preparedStatement.setString(1, id);
            preparedStatement.setString(2, pwd);
            System.out.println("Hello");
            // Step 3: Execute the query or update query
            preparedStatement.executeUpdate();

            // Step 4: Process the ResultSet object.
            
        } catch (SQLException e) {
            printSQLException(e);
            return e;
        }
        return null;
    }
    
    public Exception UPDATE_CART (String userid, String cart, String qty) {
        User user = null;
        // Step 1: Establishing a Connection
        try (Connection connection = getConnection();
            // Step 2:Create a statement using connection object
            PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_USERS_CART_SQL);) {
            preparedStatement.setString(1, cart);
            preparedStatement.setString(2, qty);
            preparedStatement.setString(3, userid);
            // Step 3: Execute the query or update query
            preparedStatement.executeUpdate();

            // Step 4: Process the ResultSet object.
            
        } catch (SQLException e) {
            printSQLException(e);
            return e;
        }
        return null;
    }
    
    private void printSQLException(SQLException ex) {
        for (Throwable e: ex) {
            if (e instanceof SQLException) {
                e.printStackTrace(System.err);
                System.err.println("SQLState: " + ((SQLException) e).getSQLState());
                System.err.println("Error Code: " + ((SQLException) e).getErrorCode());
                System.err.println("Message: " + e.getMessage());
                Throwable t = ex.getCause();
                while (t != null) {
                    System.out.println("Cause: " + t);
                    t = t.getCause();
                }
            }
        }
    }
}
