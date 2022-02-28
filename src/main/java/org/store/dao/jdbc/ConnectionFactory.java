package org.store.dao.jdbc;

import javax.sql.DataSource;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.SQLFeatureNotSupportedException;
import java.util.Properties;
import java.util.logging.Logger;

public class ConnectionFactory {
    private final String url;
    private final String username;
    private final String password;

    private static final String DB_URL_PROPERTY_NAME = "db.url";
    private static final String DB_NAME_PROPERTY_NAME = "db.username";
    private static final String DB_PASSWORD_PROPERTY_NAME = "db.password";

    public ConnectionFactory(Properties properties) {
        this.url = properties.getProperty(DB_URL_PROPERTY_NAME);
        this.username = properties.getProperty(DB_NAME_PROPERTY_NAME);
        this.password = properties.getProperty(DB_PASSWORD_PROPERTY_NAME);
    }

    public Connection getConnection() {
        try {
            return DriverManager.getConnection(url, username, password);
        } catch (SQLException exception) {
            throw new RuntimeException(exception.getMessage(), exception);
        }
    }
}
