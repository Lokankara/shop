package org.store.product.web.template;

import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import org.store.product.web.domain.Product;
import org.store.secure.config.PropertiesReader;

import java.io.IOException;
import java.io.StringWriter;
import java.io.Writer;
import java.util.List;
import java.util.Map;
import java.util.Properties;

public class PageGenerator {

    //TODO env  /webapp/WEB-INF/view/
    private static final String path = "/src/main/webapp/WEB-INF/view/";
    PropertiesReader propertiesReader = new PropertiesReader("application.properties");
    Properties properties = propertiesReader.getProperties();

    private final Configuration configuration = new Configuration(Configuration.VERSION_2_3_19);

    public String getPage(String file, Map<String, List<Product>> data) {
        try {
            Writer stream = new StringWriter();
            Template template = configuration.getTemplate(path + file);
            template.process(data, stream);
            return stream.toString();
        } catch (IOException | TemplateException exception) {
            throw new RuntimeException(exception.getMessage(), exception);
        }
    }
}
