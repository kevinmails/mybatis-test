package com.kevin;

import com.kevin.dao.User;
import com.kevin.listener.OrderCreatedEvent;
import com.kevin.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;
import java.util.concurrent.ThreadLocalRandom;

/**
 * @author kevin
 */
@Slf4j
@Controller
@RequestMapping(value = "/", method = {RequestMethod.GET, RequestMethod.POST})
public class UserController {

    private final UserService service;
    private final ApplicationContext context;

    public UserController(UserService service, ApplicationContext context) {
        this.service = service;
        this.context = context;
    }


    @ResponseBody
    @RequestMapping(value = "getUser/{id}")
    public User getUser(@PathVariable long id) {
        User user = service.getUser(id);
        System.out.println(user);
        return user;
    }

    @RequestMapping(value = "addUser", method = RequestMethod.POST)
    public String addUser(@ModelAttribute User user) {
        log.info("接到下单请求:{}", user);
        boolean addUser = service.addUser(user);

        context.publishEvent(new OrderCreatedEvent(this, user));
        log.info("用户：{}下单结束！", user.getUserName());
        return "success";
    }

    @RequestMapping(value = "index")
    public String getIndex(Model model) {
        User u = User.builder().id(this.getRandomIntInRange(10000, 99999)).userName("kevin")
                .orderNo(UUID.randomUUID().toString()).build();
        model.addAttribute("user", u);
        return "index";
    }

    private long getRandomIntInRange(int min, int max) {
        return ThreadLocalRandom.current().longs(min, (max + 1)).limit(1).findFirst().getAsLong();
    }
}
