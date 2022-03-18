package org.store.web.security.email;

public class InitialContext {
    public Object lookup(String serviceName) {
        if (serviceName.equalsIgnoreCase("EmailService")) {
            return new EmailService();
        }
        return null;
    }
}