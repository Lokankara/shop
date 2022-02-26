package org.store.web.template;
import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import org.store.web.entity.Product;

import java.io.IOException;
import java.io.StringWriter;
import java.io.Writer;
import java.util.List;
import java.util.Map;

public class PageMaker {

    private static final String HTML_DIR = "src/main/webapp/WEB-INF/views/";

    private static PageMaker pageMaker;
    private final Configuration configuration;

    public static PageMaker instance() {
        if (pageMaker == null) {
            pageMaker = new PageMaker();
        }
        return pageMaker;
    }

    public String getPage(String filename, Map<String, List<Product>> data) {
        Writer stream = new StringWriter();
        try {
            Template template = configuration.getTemplate(HTML_DIR + filename);
            template.process(data, stream);
        } catch (IOException | TemplateException e) {
            throw new RuntimeException(e);
        }
        return stream.toString();
    }

    private PageMaker() {
        configuration = new Configuration(Configuration.VERSION_2_3_19);
    }
}