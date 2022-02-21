package org.store.secure.registry;

import org.store.user.web.domain.User;

public interface RegistryDao {

    int saveUser(User user);

}
