package com.antonsuhankin.spring.springboot.service;

import com.antonsuhankin.spring.springboot.dao.UserDao;
import com.antonsuhankin.spring.springboot.model.User;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Service
public class UserServiceImpl implements UserService{

    private final UserDao userDao;

    public UserServiceImpl(UserDao userDao) {
        this.userDao = userDao;
    }

    @Override
    @Transactional(readOnly = true)
    public List<User> getListUser() {
        return userDao.getListUser();
    }

    @Override
    @Transactional
    public void addUser(String name, String surname, String department, String location) {
        userDao.addUser(name, surname, department, location);
    }

    @Override
    @Transactional(readOnly = true)
    public User getUser(Long id) {
        return userDao.getUser(id);
    }

    @Override
    @Transactional
    public void updateUser(User user) {
        userDao.updateUser(user);
    }

    @Override
    @Transactional
    public void deleteUser(User user) {
        userDao.deleteUser(user);
    }


}
