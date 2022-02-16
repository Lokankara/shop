package org.store.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.store.products.ProductMapper;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Props implements AutoCloseable {
    private final String url = "jdbc:postgresql://ec2-18-235-114-62.compute-1.amazonaws.com:5432/dbgo3fr277jh86";
    private final String name = "xhhiprtyikbzhv";
    private final String pwd = "7e80cfaecb05a3a2ed082c914d07d5245f12fb7c41f0714047a38096481ae7f8";
    protected final Logger logger = LoggerFactory.getLogger(ProductMapper.class);

    protected Connection getConnection() throws SQLException {
        return DriverManager.getConnection(url, name, pwd);
    }

    @Override
    public void close() {
        logger.info("Closed connection");
    }
}
