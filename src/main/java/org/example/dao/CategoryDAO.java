package org.example.dao;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.example.util.CustomConnectionPool;
import org.example.Entities.Category;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class CategoryDAO extends AbstractDAO<Long, Category>{
    CustomConnectionPool pool = CustomConnectionPool.getConnectionPool();
    private static final Logger logger = Logger.getLogger(CategoryDAO.class);
    @Override
    public List<Category> findAll() {
        String sql = "SELECT * FROM categories";
        List<Category> categories = new ArrayList<>();
        try {
            Connection conn = pool.getConnection();
            try (Statement statement = conn.createStatement();
                 ResultSet rs = statement.executeQuery(sql)) {
                while (rs.next()) {
                    Category category = new Category();
                    category.setCid(rs.getLong("cid"));
                    category.setCname(rs.getString("cname"));
                    categories.add(category);
                }
            }
            finally {
                pool.close(conn);
            }
        } catch (SQLException e) {
            logger.log(Level.ERROR, e.getMessage());
        }
        return categories;
    }

    @Override
    public Optional<Category> findEntityById(Long id) {
        String sql = "SELECT * FROM categories WHERE cid="+id;
        try {
            Connection conn = pool.getConnection();

            try (Statement statement = conn.createStatement();
                 ResultSet rs = statement.executeQuery(sql)) {
                if (rs.next()) {
                    Category category = new Category();
                    category.setCid(rs.getLong("cid"));
                    category.setCname(rs.getString("cname"));
                    return Optional.of(category);
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
        String sql = "DELETE FROM categories WHERE cid="+id;
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
    public Category insert(Category category) {
        String sql = "insert into categories(cname) " +
                "VALUES (?)";
        try {
            Connection conn = pool.getConnection();
            try (PreparedStatement statement = conn.prepareStatement(sql,Statement.RETURN_GENERATED_KEYS)) {

                statement.setString(1,category.getCname());
                statement.executeUpdate();
                ResultSet rs = statement.getGeneratedKeys();
                if (rs.next()) {
                    Category returnedCategory = new Category();
                    returnedCategory.setCname(rs.getString("cname"));
                    returnedCategory.setCid(rs.getLong("cid"));
                    return returnedCategory;
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

    @Override
    public Category update(Category category) {
        String sql = "update categories set cname=? where cid=? " ;
        try {
            Connection conn = pool.getConnection();
            try (PreparedStatement statement = conn.prepareStatement(sql)) {
                statement.setString(1,category.getCname());
                statement.setLong(2, category.getCid());
                statement.executeUpdate();
                return findEntityById(category.getCid()).get();
            }
            finally {
                pool.close(conn);
            }
        } catch (SQLException e) {
            logger.log(Level.ERROR, e.getMessage());
        }
        return category;
    }
}
