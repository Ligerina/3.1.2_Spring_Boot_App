package com.antonsuhankin.spring.springboot.dao;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;

import com.antonsuhankin.spring.springboot.model.User;

import java.util.List;


@Repository
public class UserDaoImpl implements UserDao {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<User> getListUser() {
        return entityManager.createQuery("SELECT u from User u", User.class).getResultList();
    }

    @Override
    public void addUser(String name, String surname, String department, String location) {
        User user = new User(name, surname, department, location);
        entityManager.persist(user);
    }

    @Override
    public User getUser(Long id) {
        return entityManager.find(User.class,id);
    }

    @Override
    public void updateUser(User user) {
        entityManager.merge(user);
    }

    @Override
    public void deleteUser(User user) {
        entityManager.remove(entityManager.contains(user) ? user : entityManager.merge(user));
    }

}
