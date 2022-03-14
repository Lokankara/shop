package org.store.service;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.store.dao.UserDao;
import org.store.dao.jdbc.JdbcUserDao;
import org.store.web.entity.User;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.*;

class UserServiceTest {

    private static UserService userService;
    private static UserDao mockDao;
    private static Optional<User> user;

    @BeforeAll
    static void beforeAll() {
        mockDao = mock(JdbcUserDao.class);
        userService = new UserService(mockDao);
        user = Optional.of(User.builder().user_id(1L).username("user").password("test").salt("secret").build());
    }

    @Test
    @DisplayName(value = "Test save User invokes and return true")
    void saveUser() {
        when(mockDao.saveUser(user.get())).thenReturn(true);
        boolean isSaved = userService.saveUser(user.orElseThrow());
        verify(mockDao).saveUser(user.get());
        assertTrue(isSaved);
    }

    @Test
    @DisplayName(value = "Test get user by name and return optional users")
    void findUserById() {
        String username = user.orElseThrow().getUsername();
        when(mockDao.findUserByName(username)).thenReturn(user);
        Optional<User> actual = userService.findUserByName(username);
        assertEquals(user, actual);
        verify(mockDao).findUserByName(username);
    }
}
