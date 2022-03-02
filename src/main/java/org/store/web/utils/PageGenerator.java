package org.store.web.utils;

import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import org.store.web.entity.Product;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.Map;

public class PageGenerator {

    private static final Configuration CONFIGURATION = new Configuration(Configuration.VERSION_2_3_19);
    private static final String HTML_DIR = "src/main/webapp/WEB-INF/views/";

    public static String getPage(String filename, PrintWriter printWriter, Map<String, List<Product>> data) {
        try {
            Template template = CONFIGURATION.getTemplate(HTML_DIR + filename);
            template.process(data, printWriter);
            return printWriter.toString();
        } catch (IOException | TemplateException exception) {
            throw new RuntimeException(exception.getMessage(), exception);
        }
    }
}