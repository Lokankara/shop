import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;
import org.store.config.PropertiesReader;
import org.store.product.dao.jdbc.ConnectionFactory;
import org.store.product.dao.jdbc.JdbcProductDao;
import org.store.product.dao.jdbc.JdbcRegistryDao;
import org.store.product.dao.jdbc.JdbcTemplate;
import org.store.product.service.ProductService;
import org.store.product.service.RegistryService;
import org.store.product.web.controller.ProductRegistryServlet;
import org.store.product.web.controller.ProductServlet;

import java.util.Properties;

public class Starter {
    public static void main(String[] args) throws Exception {
        Properties db = new PropertiesReader("application.properties").getProperties();

        ConnectionFactory connectionFactory = new ConnectionFactory(db);
        JdbcTemplate jdbcTemplate = new JdbcTemplate(connectionFactory);

        JdbcProductDao productDao = new JdbcProductDao(jdbcTemplate);
        ProductService productService = new ProductService(productDao);

        JdbcRegistryDao registryDao = new JdbcRegistryDao(jdbcTemplate);
        RegistryService registryService = new RegistryService(registryDao);

        ServletContextHandler contextHandler =
                new ServletContextHandler(ServletContextHandler.SESSIONS);
        contextHandler.addServlet(new ServletHolder(new ProductServlet(productService)), "/products/*");
        contextHandler.addServlet(new ServletHolder(new ProductRegistryServlet(productService)), "/products/add");
        contextHandler.addServlet(new ServletHolder(new ProductServlet(productService)), "/");

        Server server = new Server(8080);
        server.setHandler(contextHandler);
        server.start();
    }
}