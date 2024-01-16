package com.ash.preproject.dao;

import com.ash.preproject.model.User;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class UserDaoImpl implements UserDao {

    @PersistenceContext
    private EntityManager entityManager;

    public List<User> getAllUsers() {
        TypedQuery<User> query = entityManager.createQuery("SELECT u FROM User u", User.class);
        return query.getResultList();
    }

    public void save(User user) {
        entityManager.merge(user);
    }

    public void update(long id, User user) {
        User userForUpdate = getUserById(id);
        userForUpdate.setName(user.getName());
        userForUpdate.setEmail(user.getEmail());
        userForUpdate.setLast_name(user.getLast_name());
    }

    public User getUserById(long id) {
        return entityManager.find(User.class, id);
    }

    public void delete(long id) {
        User user = getUserById(id);
        if (user != null) {
            entityManager.remove(user);
        }
    }
}