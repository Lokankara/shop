package org.store.utils;

import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import org.store.products.Product;

import java.io.File;
import java.io.IOException;
import java.io.StringWriter;
import java.io.Writer;
import java.util.List;

public class PageMaker implements AutoCloseable {

    private static final String HTML_DIR = "templates";

    private static PageMaker pageMaker;
    private final Configuration configuration;

    public static PageMaker instance() {
        if (pageMaker == null) {
            pageMaker = new PageMaker();
        }
        return pageMaker;
    }

    public String getPage(String filename, List<Product> data) {
        Writer stream = new StringWriter();
        try {
            Template template = configuration.getTemplate(HTML_DIR + File.separator + filename);
            template.process(data, stream);
        } catch (IOException | TemplateException e) {
            throw new RuntimeException(e);
        }
        return stream.toString();
    }

    private PageMaker() {
        configuration = new Configuration(Configuration.VERSION_2_3_19);
    }

    @Override
    public void close() {
    }
}
