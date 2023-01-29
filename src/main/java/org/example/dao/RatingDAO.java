package org.example.dao;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.example.util.CustomConnectionPool;
import org.example.Entities.Rating;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class RatingDAO extends AbstractDAO<Long, Rating> {
    CustomConnectionPool pool = CustomConnectionPool.getConnectionPool();
    private static final Logger logger = Logger.getLogger(RatingDAO.class);

    @Override
    public List<Rating> findAll() {
        String sql = "SELECT * FROM rating";
        List<Rating> ratings = new ArrayList<>();
        try {
            Connection conn = pool.getConnection();
            try (Statement statement = conn.createStatement();
                 ResultSet rs = statement.executeQuery(sql)) {
                while (rs.next()) {
                    Rating rating = new Rating();
                    rating.setRid(rs.getLong("rid"));
                    rating.setPid(rs.getLong("pid"));
                    rating.setEmail(rs.getString("email"));
                    rating.setRating(rs.getInt("rating"));
                    ratings.add(rating);
                }
            } finally {
                pool.close(conn);
            }
        } catch (SQLException e) {
            logger.log(Level.ERROR, e.getMessage());
        }
        return ratings;
    }

    @Override
    public Optional<Rating> findEntityById(Long id) {
        String sql = "SELECT * FROM rating WHERE rid=" + id;
        try {
            Connection conn = pool.getConnection();
            try (Statement statement = conn.createStatement();
                 ResultSet rs = statement.executeQuery(sql)) {
                if (rs.next()) {
                    Rating rating = new Rating();
                    rating.setRid(rs.getLong("rid"));
                    rating.setPid(rs.getLong("pid"));
                    rating.setEmail(rs.getString("email"));
                    rating.setRating(rs.getInt("rating"));
                    return Optional.of(rating);
                }
            } finally {
                pool.close(conn);
            }
        } catch (SQLException e) {
            logger.log(Level.ERROR, e.getMessage());
        }
        return Optional.empty();
    }

    @Override
    public boolean delete(Long id) {
        String sql = "DELETE FROM rating WHERE rid=" + id;
        try {
            Connection conn = pool.getConnection();
            try (Statement statement = conn.createStatement()) {
                int count = statement.executeUpdate(sql);
                return count > 0;
            } finally {
                pool.close(conn);
            }
        } catch (SQLException e) {
            logger.log(Level.ERROR, e.getMessage());
        }
        return false;
    }

    @Override
    public Rating insert(Rating rating) {
        String sql = "insert into rating(pid, email, rating) " +
                "VALUES (?,?,?)";
        try {
            Connection conn = pool.getConnection();
            try (PreparedStatement statement = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
                statement.setLong(1, rating.getPid());
                statement.setString(2, rating.getEmail());
                statement.setInt(3, rating.getRating());
                statement.executeUpdate();
                ResultSet rs = statement.getGeneratedKeys();
                if (rs.next()) {
                    Rating returnedRating = new Rating();
                    returnedRating.setPid(rs.getLong("pid"));
                    returnedRating.setEmail(rs.getString("email"));
                    returnedRating.setRating(rs.getInt("rating"));
                    returnedRating.setRid(rs.getLong("rid"));
                    return returnedRating;
                }
            } finally {
                pool.close(conn);
            }
        } catch (SQLException e) {
            logger.log(Level.ERROR, e.getMessage());
        }
        return null;
    }
    @Override
    public Rating update(Rating rating) {
        String sql = "update rating set pid=?, email=?, rating=? where rid=? " ;
        try {
            Connection conn = pool.getConnection();
            try (PreparedStatement statement = conn.prepareStatement(sql)) {
                statement.setLong(1, rating.getPid());
                statement.setString(2,rating.getEmail());
                statement.setInt(3,rating.getRating());
                statement.setLong(4, rating.getRid());
                statement.executeUpdate();
                return rating;
            }
            finally {
                pool.close(conn);
            }
        } catch (SQLException e) {
            logger.log(Level.ERROR, e.getMessage());
        }
        return null;
    }

}
