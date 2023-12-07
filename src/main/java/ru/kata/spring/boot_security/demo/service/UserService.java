package ru.kata.spring.boot_security.demo.service;

import org.springframework.stereotype.Service;
import ru.kata.spring.boot_security.demo.model.User;

import java.sql.SQLException;
import java.util.List;

@Service
public interface UserService {
    List<User> getAllUsers();

    void saveUser(User user) throws SQLException;

    void deleteUser(long id);

    void editUser(User user) throws SQLException;

    User showUserById(long id);

}
