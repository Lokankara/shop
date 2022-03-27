//package org.store.web.config;
//
//import org.springframework.web.WebApplicationInitializer;
//import org.springframework.web.context.ContextLoaderListener;
//import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
//import org.springframework.web.context.support.GenericWebApplicationContext;
//import org.springframework.web.context.support.XmlWebApplicationContext;
//import org.springframework.web.servlet.DispatcherServlet;
//
//import javax.servlet.ServletContext;
//import javax.servlet.ServletRegistration;
//
//public class MainWebAppInitializer implements WebApplicationInitializer {
//    @Override
//    public void onStartup(ServletContext servletContext) {
//        XmlWebApplicationContext appContext = new XmlWebApplicationContext();
//        appContext.setConfigLocation("/WEB-INF/*.xml");
//
//        AnnotationConfigWebApplicationContext root =
//                new AnnotationConfigWebApplicationContext();
//
//        root.scan("org.store");
//        servletContext.addListener(new ContextLoaderListener(root));
//
//        ServletRegistration.Dynamic appServlet =
//                servletContext.addServlet("mvc", new DispatcherServlet(appContext));
//        appServlet.setLoadOnStartup(1);
//        appServlet.addMapping("/");
//    }
//}
