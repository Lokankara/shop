package org.store.web.security.email;

import org.postgresql.ds.PGSimpleDataSource;
import org.store.web.utils.PropertiesReader;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import java.util.Properties;

public class ServiceLocator {

    private static Cache cache = new Cache();

    public static MessagingService getService(String serviceName) throws NamingException {

        MessagingService messagingService = cache.getService(serviceName);

        if (messagingService != null) {
            return messagingService;
        }

        InitialContext context = new InitialContext();
        MessagingService service = (MessagingService) context.lookup(serviceName);
        cache.addService(service);
        return service;
    }

    public static PGSimpleDataSource dataSource() {
        PropertiesReader properties = new PropertiesReader("application.properties");
        Properties props = properties.getProps();
        String url = props.getProperty("db.url");
        String username = props.getProperty("db.username");
        String password = props.getProperty("db.password");

        PGSimpleDataSource dataSource = new PGSimpleDataSource();
        dataSource.setURL(url);
        dataSource.setUser(username);
        dataSource.setPassword(password);
        return dataSource;
    }
}