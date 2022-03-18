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
    private static UserDao mockUserDao;
    private static Optional<User> user;

    @BeforeAll
    static void beforeAll() {
        mockUserDao = mock(JdbcUserDao.class);
        userService = new UserService(mockUserDao);
        user = Optional.of(User.builder().user_id(1L).username("user").password("test").salt("secret").build());
    }

    @Test
    @DisplayName(value = "Test save User invokes and return true")
    void saveUser() {
        when(mockUserDao.saveUser(user.get())).thenReturn(true);
        boolean isSaved = userService.saveUser(user.orElseThrow());
        verify(mockUserDao).saveUser(user.get());
        assertTrue(isSaved);
    }

    @Test
    @DisplayName(value = "Test get user by name and return optional users")
    void findUserById() {
        Long id = user.orElseThrow().getUser_id();
        when(mockUserDao.findUserById(id)).thenReturn(user);
        Optional<User> actual = userService.findUserById(id);
        assertEquals(user, actual);
        verify(mockUserDao).findUserById(id);
    }
}
