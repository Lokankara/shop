package org.store.product.web.template;

import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;

import java.io.IOException;
import java.io.StringWriter;
import java.io.Writer;
import java.util.Map;

public class PageGenerator {
    private static final String HTML_DIR = "src/main/resources/webapp/static/templates";
    private final Configuration configuration = new Configuration(Configuration.VERSION_2_3_19);

    public String getPage(String filename, Map<String, Object> data) {
        try {
            Writer stream = new StringWriter();
            Template template = configuration.getTemplate(HTML_DIR + "/" + filename);
            template.process(data, stream);
            return stream.toString();
        } catch (IOException | TemplateException e) {
            throw new RuntimeException(e);
        }
    }
}
