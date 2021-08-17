package web.service;

import web.models.User;

import java.util.List;

public interface UserService {
    List<User> getAllUsers();

    User showUserById(int id);

    void addUser(User user);

    void updateUser(User user);

    void removeUser(int id);
}