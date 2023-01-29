package org.example.dao;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.example.Entities.Product;
import org.example.util.CustomConnectionPool;
import org.example.Entities.Order;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class OrderDAO {
    CustomConnectionPool pool = CustomConnectionPool.getConnectionPool();
    private static final Logger logger = Logger.getLogger(OrderDAO.class);

    public List<Order> findAll() {
        String sql = "SELECT * FROM ORDERS";
        List<Order> orders = new ArrayList<>();
        try {
            Connection conn = pool.getConnection();
            try (Statement statement = conn.createStatement()) {
                try (ResultSet rs = statement.executeQuery(sql)) {
                    while (rs.next()) {
                        Order order = new Order();
                        order.setOid(rs.getLong("oid"));
                        order.setEmail(rs.getString("email"));
                        order.setShippedDate(rs.getDate("shippeddate"));
                        order.setReceivedDate(rs.getDate("receiveddate"));
                        order.setOrderDate(rs.getDate("orderdate"));
                        order.setPhoneNumber(rs.getString("phonenumber"));
                        order.setAddress(rs.getString("address"));
                        order.setStatus(rs.getString("status"));
                        order.setTotalSum(rs.getDouble("totalsum"));
                        orders.add(order);
                    }
                }
            }
            finally {
                pool.close(conn);
            }
        } catch (SQLException e) {
            logger.log(Level.ERROR, e.getMessage());
        }
        return orders;
    }


    public Optional<Order> findOrderById(Long id) {
        String sql = "SELECT * FROM ORDERS WHERE oid=?";
        try {
            Connection conn = pool.getConnection();
            try (PreparedStatement statement = conn.prepareStatement(sql)) {
                statement.setLong(1, id);
                try (ResultSet rs = statement.executeQuery()) {
                    if (rs.next()) {
                        Order order = new Order();
                        order.setOid(rs.getLong("oid"));
                        order.setEmail(rs.getString("email"));
                        order.setShippedDate(rs.getDate("shippeddate"));
                        order.setReceivedDate(rs.getDate("receiveddate"));
                        order.setPhoneNumber(rs.getString("phonenumber"));
                        order.setOrderDate(rs.getDate("orderdate"));
                        order.setAddress(rs.getString("address"));
                        order.setStatus(rs.getString("status"));
                        order.setTotalSum(rs.getDouble("totalsum"));
                        return Optional.of(order);
                    }
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
    public List<Product> findProductsByOid(Long oid) {
        String sql = "SELECT * FROM order_details natural join products natural join categories where oid="+oid;
        List<Product> products = new ArrayList<>();
        try {
            Connection conn = pool.getConnection();
            try (Statement statement = conn.createStatement()) {
                try (ResultSet rs = statement.executeQuery(sql)) {
                    while (rs.next()) {
                        Product product = new Product();
                        product.setPid(rs.getLong("pid"));
                        product.setName(rs.getString("name"));
                        product.setDescription(rs.getString("description"));
                        product.setImage(rs.getBytes("image"));
                        product.setPrice(rs.getDouble("price"));
                        product.setCid(rs.getLong("cid"));
                        product.setCname(rs.getString("cname"));
                        product.setQuantity(rs.getLong("quantity"));
                        products.add(product);
                    }
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
    public boolean delete(Long id) {
        String sql = "DELETE FROM ORDERS WHERE oid=?";
        try {
            Connection conn = pool.getConnection();
            try (PreparedStatement statement = conn.prepareStatement(sql)) {
                statement.setLong(1, id);
                int count = statement.executeUpdate();
                return count > 0;
            }
            finally {
                pool.close(conn);
            }
        } catch (SQLException e) {
            logger.log(Level.ERROR, e.getMessage());
        }
        return false;
    }

    public Order update(Order order) {
        String sql = "UPDATE ORDERS SET email=?, orderdate=?, shippeddate=?,receiveddate=?, address=?, phonenumber=?, totalsum=?, status=? WHERE oid=?";
        try {
            Connection conn = pool.getConnection();
            try (PreparedStatement statement = conn.prepareStatement(sql)) {
                statement.setString(1, order.getEmail());
                statement.setDate(2, order.getOrderDate());
                statement.setDate(3, order.getShippedDate());
                statement.setDate(4, order.getReceivedDate());
                statement.setString(5, order.getAddress());
                statement.setString(6, order.getPhoneNumber());
                statement.setDouble(7, order.getTotalSum());
                statement.setString(8,order.getStatus());
                statement.setLong(9, order.getOid());
                int count = statement.executeUpdate();
                if (count > 0) {
                    return order;
                }
            }
            finally {
                pool.close(conn);
            }
        } catch (SQLException e) {
            logger.log(Level.ERROR, e.getMessage());
        }
        return null;
    }

    public Optional<Order> insert(Order order) {
        String sql = "INSERT INTO ORDERS (email, orderdate, shippeddate,receiveddate, address, phonenumber, totalsum,status) " +
                "VALUES (?,?,?,?,?,?,?,?)";
        try {
            Connection conn = pool.getConnection();
            try (PreparedStatement statement = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
                statement.setString(1,order.getEmail());
                statement.setDate(2, order.getOrderDate());
                statement.setDate(3, order.getShippedDate());
                statement.setDate(4, order.getReceivedDate());
                statement.setString(5, order.getAddress());
                statement.setString(6,order.getPhoneNumber());
                statement.setDouble(7, order.getTotalSum());
                statement.setString(8,order.getStatus());
                statement.executeUpdate();
                ResultSet rs = statement.getGeneratedKeys();
                if (rs.next()) {
                    order.setOid(rs.getLong(1));
                    return Optional.of(order);
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

    public boolean insertDetails(Order order) {
        String sql = "INSERT INTO order_details (oid, pid, quantity) VALUES (?,?,?)";
        try {
            Connection conn = pool.getConnection();
            try (PreparedStatement statement = conn.prepareStatement(sql)) {
                statement.setLong(1,order.getOid());
                statement.setLong(2,order.getPid());
                statement.setLong(3,order.getQuantity());
                int count = statement.executeUpdate();
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

    public List<Order> findOrdersByEmail(String email) {
        String sql = "SELECT * FROM ORDERS where email='"+email+"'";
        List<Order> orders = new ArrayList<>();
        try {
            Connection conn = pool.getConnection();
            try (Statement statement = conn.createStatement()) {
                try (ResultSet rs = statement.executeQuery(sql)) {
                    while (rs.next()) {
                        Order order = new Order();
                        order.setOid(rs.getLong("oid"));
                        order.setEmail(rs.getString("email"));
                        order.setShippedDate(rs.getDate("shippeddate"));
                        order.setReceivedDate(rs.getDate("receiveddate"));
                        order.setOrderDate(rs.getDate("orderdate"));
                        order.setPhoneNumber(rs.getString("phonenumber"));
                        order.setAddress(rs.getString("address"));
                        order.setStatus(rs.getString("status"));
                        order.setTotalSum(rs.getDouble("totalsum"));
                        orders.add(order);
                    }
                }
            }
            finally {
                pool.close(conn);
            }
        } catch (SQLException e) {
            logger.log(Level.ERROR, e.getMessage());
        }
        return orders;
    }
}

