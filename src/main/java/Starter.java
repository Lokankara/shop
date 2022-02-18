import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;
import org.store.config.PropertiesReader;
import org.store.product.dao.JdbcTemplate;
import org.store.product.dao.ProductJdbcDao;
import org.store.product.service.ProductService;
import org.store.product.web.controller.ProductServlet;
import org.store.product.dao.ConnectionFactory;

public class Starter {
    public static void main(String[] args) throws Exception {

        ConnectionFactory connectionFactory =
                new ConnectionFactory(
                        new PropertiesReader().getProperties());

        ServletContextHandler contextHandler =
                new ServletContextHandler(ServletContextHandler.SESSIONS);

        contextHandler
                .addServlet(
                    new ServletHolder(
                        new ProductServlet(
                            new ProductService(
                                new ProductJdbcDao(
                                    new JdbcTemplate(connectionFactory))))), "/products/*");

        Server server = new Server(8080);
        server.setHandler(contextHandler);
        server.start();
    }
}
