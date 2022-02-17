package org.store.utils;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class PropertiesHolder implements AutoCloseable {

    private static final String DB_URL_PROPERTY_NAME = "db.url";
    private static final String DB_NAME_PROPERTY_NAME = "db.username";
    private static final String DB_PASSWORD_PROPERTY_NAME = "db.password";
    private static final String filename = "src/main/resources/application.properties";

    private Properties properties;

    public PropertiesHolder() {
        loadProperties();
    }

    private void loadProperties() {
        try {
            properties = new Properties();
            FileInputStream inputStream = new FileInputStream(filename);
            properties.load(inputStream);
        } catch (IOException exception) {
            throw new RuntimeException(exception.getMessage(), exception);
        }
    }

    private String getDBUrl() {
        return properties.getProperty(DB_URL_PROPERTY_NAME);
    }

    private String getDBName() {
        return properties.getProperty(DB_NAME_PROPERTY_NAME);
    }

    private String getDBPassword() {
        return properties.getProperty(DB_PASSWORD_PROPERTY_NAME);
    }

    public Connection getConnection() throws SQLException {
        return DriverManager.getConnection(getDBUrl(), getDBName(), getDBPassword());
    }

    @Override
    public void close() {
    }
}
