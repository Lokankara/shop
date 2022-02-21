package org.store.secure.registry;

import org.store.user.web.domain.User;

public class RegistryService implements RegistryDao {
    private final RegistryDao registryDao;

    public RegistryService(RegistryDao registryDao) {
        this.registryDao = registryDao;
    }

    @Override
    public int saveUser(User user) {
        return registryDao.saveUser(user);
    }
}
