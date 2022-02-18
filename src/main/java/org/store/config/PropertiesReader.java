package org.store.config;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class PropertiesReader {
    private final Properties properties;

    public Properties getProperties() {
        return properties;
    }

    public PropertiesReader() {
        properties = new Properties();
        loadProperties();
    }

    private void loadProperties() {
        try {
            InputStream input = PropertiesReader.class.getClassLoader()
                    .getResourceAsStream("application.properties");
            properties.load(input);
        } catch (IOException exception) {
            throw new RuntimeException(exception.getMessage(), exception);
        }
    }
}