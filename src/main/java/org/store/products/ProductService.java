package org.store.products;

import org.store.utils.GetConnection;

import java.sql.Connection;

public class ProductService {
    private GetConnection getConnection;

    public ProductService(GetConnection getConnection) {
        this.getConnection = getConnection;
    }
    Connection connection = getConnection.connect();

}
