package com.kevin.service;

import com.kevin.dao.User;
import com.kevin.dao.UserDao;
import org.springframework.stereotype.Service;

/**
 * @author kevin
 */
@Service
public class UserService {

    private final UserDao userDao;

    public UserService(UserDao userDao) {
        this.userDao = userDao;
    }


    public User getUser(long id) {
        return userDao.getUserById(id);
    }

    public boolean addUser(User user) {
        return userDao.addUser(user);
    }

    public boolean updateUserInfo(User user) {

        return userDao.updateUserInfo(user);
    }
}
