package com.kevin;

import com.kevin.dao.User;
import com.kevin.listener.OrderCreatedEvent;
import com.kevin.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Map;
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
    @RequestMapping(value = "queryUser/{id}")
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

        if (addUser){
            return "success";
        }
        return "error";
    }

    @RequestMapping(value = "index")
    public String getIndex(Model model) {
        Map<String, String> osEnv = System.getenv();
        String sysUser = osEnv.get("USERNAME");
        osEnv.entrySet().forEach(entry -> log.info("{}", entry));
        User u = User.builder().id(this.getRandomLongInRange(10000, 99999)).userName(sysUser)
                .orderNo(UUID.randomUUID().toString()).build();
        model.addAttribute("user", u);
        return "index";
    }

    private long getRandomLongInRange(int min, int max) {
        return ThreadLocalRandom.current().longs(min, (max + 1)).limit(1).findFirst().getAsLong();
    }

    @RequestMapping(value = "update", method = RequestMethod.GET)
    public String pageUpdateInit(Model model) {
        model.addAttribute("user2", User.builder().build());
        return "update";
    }

    @RequestMapping(value = "updateUserInfo", method = RequestMethod.POST)
    public String updateUserInfo(@ModelAttribute User user) {
        log.info("user:{}", user);
        boolean updated = service.updateUserInfo(user);
        if (updated) {
            return "success";
        }
        return "error";
    }

}
