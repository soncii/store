package org.example.util;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class CustomConnectionPool {
    private String dbUrl ;
    private String dbUsername ;
    private String dbPassword ;
    private int minConnection;
    private int maxConnection;
    private int connectionNumber = 0;
    private List<Connection> freePool = new ArrayList<>();
    private List<Connection> occupiedPool = new ArrayList<>();
    private static CustomConnectionPool pool = new CustomConnectionPool();
    public synchronized static CustomConnectionPool getConnectionPool() {
        return pool;
    }
    private CustomConnectionPool() {
        try (InputStream input = this.getClass().getClassLoader().getResourceAsStream("database.properties");) {
            Properties p = new Properties();
            p.load(input);
            Class.forName(p.getProperty("db.driver"));
            dbUrl = p.getProperty("db.url");
            dbUsername = p.getProperty("db.username");
            dbPassword = p.getProperty("db.password");
            minConnection = Integer.parseInt(p.getProperty("db.minconnection"));
            maxConnection = Integer.parseInt(p.getProperty("db.maxconnection"));
            for (int i = 0; i < minConnection; i++) {
                freePool.add(createConnection());
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    private Connection createConnection() throws SQLException {
        return DriverManager.getConnection(dbUrl, dbUsername, dbPassword);
    }

    public synchronized Connection getConnection() throws SQLException {
        if (connectionNumber>=maxConnection) {
            throw new SQLException("Pool is full");
        }
        return getConnectionFromPool();

    }
    private Connection getConnectionFromPool() throws SQLException{
        if (freePool.size()<=minConnection) {
            return createConnection();
        }
        Connection connection = freePool.get(freePool.size() - 1);
        freePool.remove(freePool.size()-1);
        occupiedPool.add(connection);
        connectionNumber++;
        return connection;
    }

    public synchronized boolean close(Connection connection){
        freePool.add(connection);
        connectionNumber--;
        return occupiedPool.remove(connection);
    }

}
