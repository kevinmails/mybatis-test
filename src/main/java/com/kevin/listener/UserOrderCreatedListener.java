package com.kevin.listener;

import com.kevin.dao.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

/**
 * @author kevin
 */
@Slf4j
@Component
public class UserOrderCreatedListener implements ApplicationListener<OrderCreatedEvent> {

    @Async
    @Override
    public void onApplicationEvent(OrderCreatedEvent orderCreatedEvent) {
        Object source = orderCreatedEvent.getSource();
        User user = orderCreatedEvent.getUser();
        log.info("发布事件源:{}", source);
        log.info("用户通知：您好（{}）,下单啦！订单：{}}", user.getUserName(), user.getOrderNo());
    }
}
