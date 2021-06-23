package com.kevin.dao;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Component;

/**
 * @author kevin
 */
@Component
public class UserDao {


    private final SqlSession sqlSession;

    public UserDao(SqlSession sqlSession) {
        this.sqlSession = sqlSession;
    }


    public User getUserById(long id) {
        return sqlSession.selectOne("UserMapper.selectUser", id);

    }

    public boolean addUser(User user) {
        return sqlSession.insert("UserMapper.addUser", user) == 1 ? true : false;
    }

    public boolean updateUserInfo(User user) {
        return sqlSession.update("UserMapper.updateUserInfo", user) == 1 ? true : false;
    }
}
