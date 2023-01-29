package org.example.dao;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.example.Entities.Product;
import org.example.util.CustomConnectionPool;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class CartDAO {
    CustomConnectionPool pool = CustomConnectionPool.getConnectionPool();
    Logger logger = Logger.getLogger(CartDAO.class);

    public Optional<Product> findById(String email, Long pid) {
        String sql = "SELECT * FROM cart natural join products WHERE email='"+email+"' AND" +
                " pid="+pid;
        try {
            Connection conn = pool.getConnection();
            try (Statement statement = conn.createStatement();
                 ResultSet rs = statement.executeQuery(sql)) {
                if (rs.next()) {
                    Product product = new Product();
                    product.setPid(rs.getLong("pid"));
                    product.setName(rs.getString("name"));
                    product.setDescription(rs.getString("description"));
                    product.setImage(rs.getBytes("image"));
                    product.setPrice(rs.getDouble("price"));
                    product.setCid(rs.getLong("cid"));
                    product.setQuantity(rs.getLong("quantity"));
                    return Optional.of(product);
                }
            }
            finally {
                pool.close(conn);
            }
        } catch (SQLException e) {
            logger.log(Level.ERROR, e.getMessage());
        }
        return Optional.empty();

    }

    public List<Product> findCartByEmail(String email) {
        String sql = "SELECT * FROM cart natural join products WHERE email='"+email+"'";
        List<Product> products = new ArrayList<Product>();
        try {
            Connection conn = pool.getConnection();
            try (Statement statement = conn.createStatement();
                 ResultSet rs = statement.executeQuery(sql)) {
                while (rs.next()) {
                    Product product = new Product();
                    product.setPid(rs.getLong("pid"));
                    product.setName(rs.getString("name"));
                    product.setDescription(rs.getString("description"));
                    product.setImage(rs.getBytes("image"));
                    product.setPrice(rs.getDouble("price"));
                    product.setCid(rs.getLong("cid"));
                    product.setQuantity(rs.getLong("quantity"));
                    products.add(product);
                }
            }
            finally {
                pool.close(conn);
            }
        } catch (SQLException e) {
            logger.log(Level.ERROR, e.getMessage());
        }
        return products;
    }


    public boolean deleteFromCart(String email, Long pid) {
        String sql = "DELETE FROM cart WHERE pid="+pid+" AND email='"+email+"'";
        try {
            Connection conn = pool.getConnection();
            try (Statement statement = conn.createStatement()) {
                int count = statement.executeUpdate(sql);
                return count>0;
            }
            finally {
                pool.close(conn);
            }
        } catch (SQLException e) {
            logger.log(Level.ERROR, e.getMessage());
        }
        return false;
    }
    public boolean deleteUserCart(String email) {
        String sql = "DELETE FROM cart WHERE email='"+email+"'";
        try {
            Connection conn = pool.getConnection();
            try (Statement statement = conn.createStatement()) {
                int count = statement.executeUpdate(sql);
                return count>0;
            }
            finally {
                pool.close(conn);
            }
        } catch (SQLException e) {
            logger.log(Level.ERROR, e.getMessage());
        }
        return false;
    }


    public boolean insert(String email, Long pid) {
        String sql = "insert into cart (email, pid, quantity) " +
                "VALUES (?,?,1)";
        try {
            Connection conn = pool.getConnection();
            try (PreparedStatement statement = conn.prepareStatement(sql,Statement.RETURN_GENERATED_KEYS)) {
                statement.setString(1,email);
                statement.setLong(2, pid);
                int count = statement.executeUpdate();
                return count>1;
            }
            finally {
                pool.close(conn);
            }
        } catch (SQLException e) {
            logger.log(Level.ERROR, e.getMessage());
        }
        return false;
    }


    public boolean update(String email, Long pid, Long quantity) {
        String sql = "update cart set quantity=? where pid=? AND email=?" ;
        try {
            Connection conn = pool.getConnection();
            try (PreparedStatement statement = conn.prepareStatement(sql)) {
                statement.setLong(1,quantity);
                statement.setLong(2,pid);
                statement.setString(3,email);
                statement.executeUpdate();
                return true;
            }
            finally {
                pool.close(conn);
            }
        } catch (SQLException e) {
            logger.log(Level.ERROR, e.getMessage());
        }
        return false;
    }
}
