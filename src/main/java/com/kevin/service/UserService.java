package com.kevin.service;

import com.kevin.dao.User;
import com.kevin.dao.UserDao;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

/**
 * @author kevin
 */
@Slf4j
@Service
public class UserService {

    private final UserDao userDao;

    public UserService(UserDao userDao) {
        this.userDao = userDao;
    }


    public User getUser(long id) {
        return userDao.getUserById(id);
    }

    /**
     * 测试嵌套事务的传播属性
     *
     * @param user
     * @return
     */
    @Transactional(propagation = Propagation.REQUIRES_NEW, rollbackFor = Throwable.class)
    public boolean addUser(User user) {

        this.addUserTA(new User(12345, "kevin", "abc", new Date()));

        return userDao.addUser(user);
    }


    @Transactional(propagation = Propagation.REQUIRES_NEW, rollbackFor = Throwable.class)
    public boolean addUserTA(User user) {
        return userDao.addUser(user);
    }

    public boolean updateUserInfo(User user) {

        return userDao.updateUserInfo(user);
    }
}
