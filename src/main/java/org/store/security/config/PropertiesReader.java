package org.store.security.config;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class PropertiesReader {
    Properties properties = new Properties();

    public PropertiesReader(String propsFile) {
        try (InputStream inputStream = PropertiesReader
                .class.getClassLoader()
                .getResourceAsStream(propsFile)) {
            properties.load(inputStream);
        } catch (IOException exception) {
            throw new RuntimeException(
                    exception.getMessage(), exception);
        }
    }

    public Properties getProps() {
        return properties;
    }
}