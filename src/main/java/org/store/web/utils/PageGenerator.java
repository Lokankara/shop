package org.store.web.utils;
import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import org.store.web.entity.Product;

import java.io.IOException;
import java.io.StringWriter;
import java.io.Writer;
import java.util.List;
import java.util.Map;

public class PageGenerator {
//    private static final String HTML_DIR = PageGenerator.class.getResource(File.separator).getPath();
    private static final Configuration CONFIGURATION = new Configuration(Configuration.VERSION_2_3_19);
    private static final String HTML_DIR = "src/main/webapp/WEB-INF/views/";


    public String getPage(String filename, Map<String, List<Product>> data) {
        Writer stream = new StringWriter();
        try {
            Template template = CONFIGURATION.getTemplate(HTML_DIR + filename);
            template.process(data, stream);
        } catch (IOException | TemplateException exception) {
            throw new RuntimeException(exception.getMessage(),exception);
        }
        return stream.toString();
    }
}