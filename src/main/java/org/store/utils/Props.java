package org.store.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.store.products.ProductMapper;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class Props {
    protected static Logger logger = LoggerFactory.getLogger(ProductMapper.class);

    protected List<String> getKeys() {
        List<String> keys = new ArrayList<>();
        {
            try {
                Properties properties;
                properties = readPropertiesFile("data.properties");
                keys.add(properties.getProperty("url"));
                keys.add(properties.getProperty("username"));
                keys.add("password: " + properties.getProperty("pwd"));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return keys;
    }

    private Properties readPropertiesFile(String fileName) throws IOException {
        FileInputStream inputStream = null;
        Properties properties = null;
        try {
            inputStream = new FileInputStream(fileName);
            properties = new Properties();
            properties.load(inputStream);
        } catch (IOException fileNotFoundException) {
            fileNotFoundException.printStackTrace();
        } finally {
            assert inputStream != null;
            inputStream.close();
        }
        return properties;
    }
}
