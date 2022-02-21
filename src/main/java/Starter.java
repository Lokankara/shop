import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;
import org.store.product.dao.jdbc.ConnectionFactory;
import org.store.product.dao.jdbc.JdbcProductDao;
import org.store.product.dao.jdbc.JdbcProductTemplate;
import org.store.product.service.ProductService;
import org.store.product.web.controller.ProductServlet;
import org.store.secure.config.PropertiesReader;
import org.store.secure.registry.JdbcRegistryDao;
import org.store.secure.registry.RegistryService;
import org.store.user.dao.jdbc.JdbcUserDao;
import org.store.user.dao.jdbc.JdbcUserTemplate;
import org.store.user.service.UserService;
import org.store.user.web.controller.UserServlet;

import java.util.Properties;

public class Starter {
    public static void main(String[] args) throws Exception {
        Properties db = new PropertiesReader("application.properties").getProperties();
//        DataSource dataSource = null;
//        Flyway flyway = Flyway.configure().dataSource(dataSource).load();
//        flyway.migrate();

        ConnectionFactory connectionFactory = new ConnectionFactory(db);
        JdbcProductTemplate jdbcProductTemplate = new JdbcProductTemplate(connectionFactory);
        JdbcUserTemplate jdbcUserTemplate = new JdbcUserTemplate(connectionFactory);

        JdbcProductDao productDao = new JdbcProductDao(jdbcProductTemplate);
        ProductService productService = new ProductService(productDao);

        JdbcUserDao userDao = new JdbcUserDao(jdbcUserTemplate);
        UserService userService = new UserService(userDao);

        JdbcRegistryDao registryDao = new JdbcRegistryDao(jdbcProductTemplate, jdbcUserTemplate);
        RegistryService registryService = new RegistryService(registryDao);

        ServletContextHandler contextHandler =
                new ServletContextHandler(ServletContextHandler.SESSIONS);
        contextHandler.addServlet(new ServletHolder(new UserServlet(userService, registryService)), "/users");
        contextHandler.addServlet(new ServletHolder(new UserServlet(userService, registryService)), "/login");
        contextHandler.addServlet(new ServletHolder(new ProductServlet(productService, userService)), "/products/*");
        contextHandler.addServlet(new ServletHolder(new ProductServlet(productService, userService)), "/products/add");
        contextHandler.addServlet(new ServletHolder(new UserServlet(userService, registryService)), "/");

        Server server = new Server(8080);
        server.setHandler(contextHandler);
        server.start();
    }
}