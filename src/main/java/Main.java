import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;
import org.store.products.ProductJdbcDao;
import org.store.products.ProductService;
import org.store.products.ProductServlet;

public class Main {
    public static void main(String[] args) throws Exception {
        ServletContextHandler contextHandler =
                new ServletContextHandler(ServletContextHandler.SESSIONS);

        contextHandler
                .addServlet(
                    new ServletHolder(
                        new ProductServlet(
                            new ProductService(
                                new ProductJdbcDao()))), "/products/*");

        Server server = new Server(8080);
        server.setHandler(contextHandler);
        server.start();
    }
}
