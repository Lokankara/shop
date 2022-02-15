package org.store.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.List;

public class GetConnection extends Props implements AutoCloseable {

    public Connection connect() {
        List<String> key = getKeys();
        Connection connection = null;
        try {
            connection = DriverManager.getConnection(key.get(0), key.get(1), key.get(2));
            logger.info("Connected to the PostgreSQL server successfully");
        } catch (SQLException e) {
            logger.error("IOException " + e.getMessage());
        }
        return connection;
    }

    @Override
    public void close() {
        logger.error("Closed connection");
    }
}