package org.store.secure.config;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class PropertiesReader implements AutoCloseable{
    private final Properties properties = new Properties();

    public Properties getProperties() {
        return new Properties(properties);
    }

    public PropertiesReader(String propsFile) { loadProperties(propsFile); }

    private void loadProperties(String propsFile) {
        try {
            InputStream input = PropertiesReader
                    .class.getClassLoader()
                    .getResourceAsStream(propsFile);
            properties.load(input);
        } catch (IOException exception) {
            throw new RuntimeException(exception.getMessage(), exception);
        }
    }
    @Override
    public void close() {}
}