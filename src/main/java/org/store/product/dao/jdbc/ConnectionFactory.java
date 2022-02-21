package org.store.product.dao.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class ConnectionFactory {

    private static final String DB_URL_PROPERTY_NAME = "db.url";
    private static final String DB_NAME_PROPERTY_NAME = "db.username";
    private static final String DB_PASSWORD_PROPERTY_NAME = "db.password";

    private final Properties properties;

    public ConnectionFactory(Properties properties) {
        this.properties = properties;
    }

    public Connection getConnection() throws SQLException {
        return DriverManager.getConnection(
                        properties.getProperty(DB_URL_PROPERTY_NAME),
                        properties.getProperty(DB_NAME_PROPERTY_NAME),
                        properties.getProperty(DB_PASSWORD_PROPERTY_NAME));
    }
}
