package com.kevin.service;

import com.kevin.dao.User;
import com.kevin.dao.UserDao;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

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
}
