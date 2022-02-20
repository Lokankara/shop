package org.store.product.web.template;

import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import org.store.product.web.domain.Product;

import java.io.IOException;
import java.io.StringWriter;
import java.io.Writer;
import java.util.List;
import java.util.Map;

public class PageGenerator {
    //    TODO request.getRequestURL().toString();
    private static final String path = "src/main/resources/webapp/static/templates/";

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
