package com.ash.preproject.dao;

import com.ash.preproject.model.User;

import java.util.List;

public interface UserDao {
    List<User> getAllUsers();

    void save(User user);

    void update(long id, User user);

    User getUserById(long id);

    void delete(long id);
}