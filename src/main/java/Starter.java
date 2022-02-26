import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;
import org.store.dao.jdbc.ConnectionFactory;
import org.store.dao.jdbc.JdbcProductDao;
import org.store.dao.jdbc.JdbcSecurityDao;
import org.store.dao.jdbc.template.JdbcProductTemplate;
import org.store.dao.jdbc.template.JdbcUserTemplate;
import org.store.security.config.PropertiesReader;
import org.store.service.ProductService;
import org.store.service.SecurityService;
import org.store.web.servlet.ProductServlet;
import org.store.web.servlet.ProductsServlet;

import java.util.Properties;

public class Starter {
    public static void main(String[] args) throws Exception {

        Properties properties = new PropertiesReader("application.properties").getProps();
        ConnectionFactory connectionFactory = new ConnectionFactory(properties);

        JdbcProductTemplate jdbcProductTemplate = new JdbcProductTemplate(connectionFactory);
        JdbcUserTemplate jdbcUserTemplate = new JdbcUserTemplate(connectionFactory);

        JdbcProductDao productDao = new JdbcProductDao(jdbcProductTemplate);
        ProductService productService = new ProductService(productDao);

        JdbcSecurityDao userDao = new JdbcSecurityDao(jdbcUserTemplate);
        SecurityService securityService = new SecurityService(userDao);

        ServletContextHandler contextHandler =
                new ServletContextHandler(ServletContextHandler.SESSIONS);
        contextHandler.addServlet(new ServletHolder(new ProductServlet(productService)), "/");
//        contextHandler.addServlet(new ServletHolder(new SecurityServlet(securityService)), "/login/");
//        contextHandler.addServlet(new ServletHolder(new ProductServlet(productService)), "/products/add/");
//        contextHandler.addServlet(new ServletHolder(new SecurityServlet(securityService)), "/");

        Server server = new Server(8080);
        server.setHandler(contextHandler);
        server.start();
    }
}