package org.store.web.security.email;

import javax.naming.InitialContext;
import javax.naming.NamingException;

public class ServiceLocator {

    private static Cache cache = new Cache();

    public static MessagingService getService(String serviceName) throws NamingException {

        MessagingService service = cache.getService(serviceName);

        if (service != null) {
            return service;
        }

        InitialContext context = new InitialContext();
        MessagingService service2 = (MessagingService) context.lookup(serviceName);
        cache.addService(service2);
        return service2;
    }
}