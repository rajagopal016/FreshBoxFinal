package com.freshbox.freshbox.dao;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
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
public class ProductDAO {
	@Autowired
	JdbcTemplate template;
	@Autowired
	NamedParameterJdbcTemplate namedTemplate;
    private String jdbcURL = "jdbc:mysql://localhost:3306/freshbox";
    private String jdbcUsername = "root";
    private String jdbcPassword = "1234";

    private static final String INSERT_PRODUCTS_SQL = "INSERT INTO table_product" + "  (name, email, country) VALUES " +
        " (?, ?, ?);";

    private static final String SELECT_PRODUCTS_BY_ID = "select * from table_product where table_user_id =?";
    private static final String SELECT_ALL_PRODUCTS = "select * from table_product";
    private static final String DELETE_PRODUCTS_SQL = "delete from table_product where id = ?;";
    private static final String UPDATE_PRODUCTS_SQL = "update table_product set name = ?,email= ?, country =? where id = ?;";

    public ProductDAO() {}

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
    
	public List<Product> getProducts(){
			
			return template.query("SELECT * FROM `table_product`" , new RowMapper<Product>() {
	
				@Override
				public Product mapRow(ResultSet rs, int rowNum) throws SQLException {
					Product e = new Product();
					e.setProduct_Id(rs.getInt(1));
					e.setProduct_Name(rs.getString(2));
					e.setProduct_Price(rs.getInt(3));
					e.setProduct_Category(rs.getString(4));
					e.setProduct_Type(rs.getString(5));
					e.setProduct_Availability(rs.getString(6));
					e.setProduct_Cuisine(rs.getString(7));
					e.setProduct_Description(rs.getString(8));
					e.setProduct_Offers(rs.getInt(9));
					return e;
				}
				
			});
			
		}
	
	
	public List<Product> listProductByCuisine(String cuisine) {  
	       return query
	          ("SELECT * FROM Table_Product WHERE Table_Product_Cuisine = ?",
	          new Object[] { cuisine });         
	}

	private List<Product> query(String sql, Object[] parameters) {  
	@SuppressWarnings("deprecation")
	List<Product> products = template.query(sql, parameters, new RowMapper<Product>() {
			
			@Override
			public Product mapRow(ResultSet rs, int rowNum) throws SQLException {
				Product e = new Product();
				e.setProduct_Id(rs.getInt(1));
				e.setProduct_Name(rs.getString(2));
				e.setProduct_Price(rs.getInt(3));
				e.setProduct_Category(rs.getString(4));
				e.setProduct_Type(rs.getString(5));
				e.setProduct_Availability(rs.getString(6));
				e.setProduct_Cuisine(rs.getString(7));
				e.setProduct_Description(rs.getString(8));
				e.setProduct_Offers(rs.getInt(9));
				return e;
			}
			
		});
	   return products;
	}
	
	private List<Product> query(String string, MapSqlParameterSource mapSqlParameterSource) {
		// TODO Auto-generated method stub
		return null;
	}

	public List<Cuisine> getCuisines(){
		
		return template.query("SELECT * FROM `table_cuisines`" , new RowMapper<Cuisine>() {

			@Override
			public Cuisine mapRow(ResultSet rs, int rowNum) throws SQLException {
				Cuisine e = new Cuisine();
				e.setCuisine_id(rs.getInt(1));
				e.setCuisine_name(rs.getString(2));
				return e;
			}
			
		});
		
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
