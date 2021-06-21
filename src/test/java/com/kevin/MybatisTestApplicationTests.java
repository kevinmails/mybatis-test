package com.kevin;

import com.kevin.service.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class MybatisTestApplicationTests {

    @Autowired
    UserService userService;

    @Test
    void contextLoads() {
    }


    @Test
    public void testUser() {

        userService.getUser(1);
    }

}
