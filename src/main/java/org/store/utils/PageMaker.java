package org.store.utils;

import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;

import java.io.IOException;
import java.io.StringWriter;
import java.io.Writer;
import java.util.Map;

public class PageMaker {

    private static final String HTML_DIR = "src/main/resources/webapp/static/templates";

    private static PageMaker pageMaker;
    private final Configuration configuration;

    public static PageMaker instance() {
        if (pageMaker == null) {
            pageMaker = new PageMaker();
        }
        return pageMaker;
    }

    public String getPage(String filename, Map<String, Object> data) {
        Writer stream = new StringWriter();
        try {
            Template template = configuration.getTemplate(HTML_DIR + "/" + filename);
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
