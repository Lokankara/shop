package org.store;

import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;
import org.store.dao.ProductDao;
import org.store.dao.jdbc.template.ConnectionFactory;
import org.store.dao.jdbc.JdbcProductDao;
import org.store.dao.jdbc.JdbcUserDao;
import org.store.dao.jdbc.template.JdbcProductTemplate;
import org.store.dao.jdbc.template.JdbcUserTemplate;
import org.store.web.utils.PropertiesReader;
import org.store.service.ProductService;
import org.store.service.SecurityService;
import org.store.service.UserService;
import org.store.web.servlet.AllProductServlet;
import org.store.web.servlet.ProductServlet;
import org.store.web.servlet.SecurityServlet;

import java.util.Properties;

public class Starter {
    public static void main(String[] args) throws Exception {

        Properties properties = new PropertiesReader("application.properties").getProps();
        ConnectionFactory connectionFactory = new ConnectionFactory(properties);
        JdbcProductTemplate jdbcProductTemplate = new JdbcProductTemplate(connectionFactory);
        JdbcUserTemplate jdbcUserTemplate = new JdbcUserTemplate(connectionFactory);

        ProductDao productDao = new JdbcProductDao(jdbcProductTemplate);
        ProductService productService = new ProductService(productDao);

        JdbcUserDao userDao = new JdbcUserDao(jdbcUserTemplate);
        UserService userService = new UserService(userDao);
        SecurityService securityService = new SecurityService();

        ServletContextHandler contextHandler =
                new ServletContextHandler(ServletContextHandler.SESSIONS);
        contextHandler.addServlet(new ServletHolder(new AllProductServlet(productService)), "/static/*");
        contextHandler.addServlet(new ServletHolder(new AllProductServlet(productService)), "/products");
        contextHandler.addServlet(new ServletHolder(new AllProductServlet(productService)), "/products/add");
        contextHandler.addServlet(new ServletHolder(new ProductServlet(productService)), "/products/edit");
        contextHandler.addServlet(new ServletHolder(new ProductServlet(productService)), "/products/remove");
//        contextHandler.addServlet(new ServletHolder(new SecurityServlet(securityService, userService)), "/");
//        contextHandler.addServlet(new ServletHolder(new SecurityServlet(securityService, userService)), "/login");

        Server server = new Server(8080);
        server.setHandler(contextHandler);
        server.start();
    }
}