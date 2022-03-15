package org.store.service;

import lombok.AllArgsConstructor;
import org.store.dao.UserDao;
import org.store.web.entity.Session;
import org.store.web.entity.User;

import java.util.List;
import java.util.Optional;

@AllArgsConstructor
public class UserService {

    private final UserDao userDao;

    public boolean saveUser(User user) {
        return userDao.saveUser(user);
    }

    public Optional<User> findUserByName(String username) {
        return userDao.findUserByName(username);
    }

    public void addToCart(Session session, Long id) {
        List<Long> products = session.getProducts();
        products.add(id);
        session.setProducts(products);
    }
}
