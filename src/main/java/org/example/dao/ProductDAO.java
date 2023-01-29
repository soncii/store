package org.example.dao;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.example.util.CustomConnectionPool;
import org.example.Entities.Product;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class ProductDAO extends AbstractDAO<Long, Product> {
    CustomConnectionPool pool = CustomConnectionPool.getConnectionPool();
    static Logger logger = Logger.getLogger(ProductDAO.class);
    @Override
    public List<Product> findAll() {
        String sql = "SELECT * FROM products natural join categories";
        List<Product> products = new ArrayList<>();
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
                    product.setCname(rs.getString("cname"));
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

    public List<Product> findAllByCategory(String cname) {
        String sql = "SELECT * FROM products natural join categories where cname='"+cname+"'";
        List<Product> products = new ArrayList<>();
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
                    product.setCname(rs.getString("cname"));
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

    @Override
    public Optional<Product> findEntityById(Long id) {
        String sql = "SELECT * FROM products natural join categories WHERE pid="+id;
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
                    product.setCname(rs.getString("cname"));
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

    @Override
    public boolean delete(Long id) {
        String sql = "DELETE FROM products WHERE pid="+id;
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

    @Override
    public Product insert(Product product) {
        String sql = "insert into products(name, description, image, price, cid) " +
                "VALUES (?,?,?,?,?)";
        try {
            Connection conn = pool.getConnection();
            try (PreparedStatement statement = conn.prepareStatement(sql,Statement.RETURN_GENERATED_KEYS)) {
                statement.setString(1,product.getName());
                statement.setString(2, product.getDescription());
                statement.setBytes(3,product.getImage());
                statement.setDouble(4,product.getPrice());
                statement.setLong(5, product.getCid());
                int count = statement.executeUpdate();
                ResultSet rs = statement.getGeneratedKeys();
                return product;
//                if (rs.next()) {
//                    long pid = rs.getLong("pid");
//                    return findEntityById(pid).get();
//                }
            }
            finally {
                pool.close(conn);
            }
        } catch (SQLException e) {
            logger.log(Level.ERROR, e.getMessage());
        }
        return null;
    }

    @Override
    public Product update(Product product) {
        String sql = "update products set name=?, description=?, price=?, cid=? where pid=? " ;
        try {
            Connection conn = pool.getConnection();
            try (PreparedStatement statement = conn.prepareStatement(sql)) {
                statement.setString(1, product.getName());
                statement.setString(2, product.getDescription());
                statement.setDouble(3,product.getPrice());
                statement.setLong(4,product.getCid());
                statement.setLong(5,product.getPid());
                statement.executeUpdate();
                return findEntityById(product.getPid()).get();
            }
            finally {
                pool.close(conn);
            }
        } catch (SQLException e) {
            logger.log(Level.ERROR, e.getMessage());
        }
        return product;
    }

    public List<Product> findAllByCategory(Long cid) {
        String sql = "SELECT * FROM products natural join categories where cid="+cid;
        List<Product> products = new ArrayList<>();
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
                    product.setCname(rs.getString("cname"));
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
}
