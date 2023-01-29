package org.example.dao;

import org.apache.log4j.Level;
import org.example.util.CustomConnectionPool;
import org.example.Entities.User;
import org.apache.log4j.Logger;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class UserDAO extends AbstractDAO<String, User>{
    CustomConnectionPool pool = CustomConnectionPool.getConnectionPool();
    private static final Logger logger = Logger.getLogger(UserDAO.class);

    @Override
    public List<User> findAll() {
        String sql = "SELECT * FROM USERS";
        List<User> users = new ArrayList<>();
        try {
            Connection conn = pool.getConnection();
            try (   Statement statement = conn.createStatement();
                    ResultSet rs = statement.executeQuery(sql)) {
                while (rs.next()) {
                    User user = new User();
                    user.setAddress(rs.getString("address"));
                    user.setEmail(rs.getString("email"));
                    user.setPassword(rs.getString("password"));
                    user.setFirstname(rs.getString("firstname"));
                    user.setMobile(rs.getString("mobile"));
                    user.setLastname(rs.getString("lastname"));
                    user.setRole(rs.getString("role"));
                    users.add(user);
                }
            }
            finally {
                pool.close(conn);
            }
        } catch (SQLException e) {
            UserDAO.logger.log(Level.ERROR, e.getMessage());
        }
        return users;

    }

    @Override
    public Optional<User> findEntityById(String email) {
        String sql = "SELECT * FROM USERS WHERE email='"+email+"'";
        try {
            Connection conn = pool.getConnection();

            try (Statement statement = conn.createStatement();
                 ResultSet rs = statement.executeQuery(sql)) {
                if (rs.next()) {
                    User user = new User();
                    user.setEmail(rs.getString("email"));
                    user.setPassword(rs.getString("password"));
                    user.setFirstname(rs.getString("firstname"));
                    user.setLastname(rs.getString("lastname"));
                    user.setMobile(rs.getString("mobile"));
                    user.setAddress(rs.getString("address"));
                    user.setRole(rs.getString("role"));
                    return Optional.of(user);
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
    public boolean delete(String email) {
        String sql = "DELETE FROM USERS WHERE email='"+email+"'";
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
            UserDAO.logger.log(Level.ERROR, e.getMessage());
        }
        return false;
    }

    @Override
    public User insert(User user) {
        String sql = "insert into users(email, password,firstname, lastname, address, role, mobile) " +
                "VALUES (?,?,?,?,?,?,?)";
        try {
            Connection conn = pool.getConnection();
            try (PreparedStatement statement = conn.prepareStatement(sql)) {
                statement.setString(1,user.getEmail());
                statement.setString(2,user.getPassword());
                statement.setString(3,user.getFirstname());
                statement.setString(4,user.getLastname());
                statement.setString(5,user.getAddress());
                statement.setString(6,user.getRole());
                statement.setString(7,user.getMobile());
                int count = statement.executeUpdate();
                if (count>0) UserDAO.logger.log(Level.INFO, "User: "+ user.getRole() +" has successfully registered");
                return findEntityById(user.getEmail()).get();
            }
            finally {
                pool.close(conn);
            }
        } catch (SQLException e) {
            UserDAO.logger.log(Level.ERROR, e.getMessage());
        }
        return null;
    }

    @Override
    public User update(User user) {
        String sql = "update users  set password=?, firstname=?, lastname=?, address=?, role=?, mobile=? where email=? ";
        try {
            Connection conn = pool.getConnection();
            try (PreparedStatement statement = conn.prepareStatement(sql)) {
                statement.setString(1,user.getPassword());
                statement.setString(2,user.getFirstname());
                statement.setString(3,user.getLastname());
                statement.setString(4,user.getAddress());
                statement.setString(5,user.getRole());
                statement.setString(6, user.getMobile());
                statement.setString(7,user.getEmail());
                statement.executeUpdate();
                return findEntityById(user.getEmail()).get();
            }
            finally {
                pool.close(conn);
            }
        } catch (SQLException e) {
            UserDAO.logger.log(Level.ERROR, e.getMessage());
        }
        return user;
    }

    public Optional<User> findEntityByEmailAndPassword(String email, String password) {
        String sql = "SELECT * FROM USERS WHERE email='"+email+"' AND password='"+password+"'";
        try {
            Connection conn = pool.getConnection();
            try (Statement statement = conn.createStatement();
                 ResultSet rs = statement.executeQuery(sql)) {
                if (rs.next()) {
                    User user = new User();
                    user.setEmail(rs.getString("email"));
                    user.setPassword(rs.getString("password"));
                    user.setFirstname(rs.getString("firstname"));
                    user.setLastname(rs.getString("lastname"));
                    user.setMobile(rs.getString("mobile"));
                    user.setAddress(rs.getString("address"));
                    user.setRole(rs.getString("role"));
                    return Optional.of(user);
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
}
