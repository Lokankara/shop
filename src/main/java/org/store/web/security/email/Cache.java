package org.store.web.security.email;

import java.util.ArrayList;
import java.util.List;

public class Cache {
    private List<MessagingService> services = new ArrayList<>();

    public MessagingService getService(String serviceName) {
        return services.get(0);
    }

    public void addService(MessagingService newService) {
        services.add(newService);
    }
}