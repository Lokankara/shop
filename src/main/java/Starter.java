import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;
import org.store.dao.ProductDao;
import org.store.dao.jdbc.ConnectionFactory;
import org.store.dao.jdbc.JdbcProductDao;
//import org.store.dao.jdbc.JdbcSecurityDao;
import org.store.dao.jdbc.template.JdbcProductTemplate;
import org.store.security.config.PropertiesReader;
import org.store.service.ProductService;
import org.store.web.servlet.GetProductServlet;
import org.store.web.servlet.ProductServlet;
//import org.store.web.servlet.SecurityServlet;

import java.util.Properties;

public class Starter {
    public static void main(String[] args) throws Exception {

        Properties properties = new PropertiesReader("application.properties").getProps();
        ConnectionFactory connectionFactory = new ConnectionFactory(properties);
        JdbcProductTemplate jdbcProductTemplate = new JdbcProductTemplate(connectionFactory);

        ProductDao productDao = new JdbcProductDao(jdbcProductTemplate);
        ProductService productService = new ProductService(productDao);

//        JdbcSecurityDao userDao = new JdbcSecurityDao(jdbcUserTemplate);
//        SecurityService securityService = new SecurityService(userDao);

        ServletContextHandler contextHandler =
                new ServletContextHandler(ServletContextHandler.SESSIONS);
        contextHandler.addServlet(new ServletHolder(new GetProductServlet(productService)), "/static/*");
        contextHandler.addServlet(new ServletHolder(new GetProductServlet(productService)), "/products");
        contextHandler.addServlet(new ServletHolder(new ProductServlet(productService)), "/products/add");
        contextHandler.addServlet(new ServletHolder(new ProductServlet(productService)), "/products/edit");
        contextHandler.addServlet(new ServletHolder(new ProductServlet(productService)), "/products/remove");
//        contextHandler.addServlet(new ServletHolder(new SecurityServlet(securityService)), "/login");
//        contextHandler.addServlet(new ServletHolder(new SecurityServlet(securityService)), "/");

        Server server = new Server(8080);
        server.setHandler(contextHandler);
        server.start();
    }
}