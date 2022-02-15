package org.store.products;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

public class ProductMapper {
    Logger logger = LoggerFactory.getLogger(ProductMapper.class);
    String[] keys = {"method", "URL", "pathInfo", "sessionId", "parameters"};

    public ProductMapper(HttpServletRequest request, HttpServletResponse response) {

        Map<String, String> pageVariables = createPageVariablesMap(request);
        for (String key : keys) {
            try {
                response.getWriter().write(pageVariables.get(key) + "\n");
            } catch (IOException e) {
                e.printStackTrace();
                logger.error("IOException " + e.getMessage());
            }
        }
        response.setStatus(HttpServletResponse.SC_OK);
    }

    private Map<String, String> createPageVariablesMap(HttpServletRequest request) {
        Map<String, String> pageVariables = new HashMap<>();
        pageVariables.put(keys[0], request.getMethod());
        pageVariables.put(keys[1], String.valueOf(request.getRequestURL()));
        pageVariables.put(keys[2], request.getPathInfo());
        pageVariables.put(keys[3], request.getSession().getId());
        pageVariables.put(keys[4], toString(request.getParameterMap()));
        return pageVariables;
    }

    private String toString(Map<String, String[]> map) {
        return map.entrySet().stream().map(entry -> {
            String page = String.join(" and" + (Arrays.toString(entry.getValue())));
            return entry.getKey() + " => " + page;
        }).collect(Collectors.joining());
    }
}
