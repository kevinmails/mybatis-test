package com.kevin.listener;

import com.kevin.dao.User;
import lombok.*;
import org.springframework.context.ApplicationEvent;

/**
 * @author kevin
 */
@Getter
@Setter
@ToString
public class OrderCreatedEvent extends ApplicationEvent {

    private User user;

    @Override
    public Object getSource() {
        return super.getSource();
    }

    public OrderCreatedEvent(Object source, User user) {
        super(source);
        this.user = user;
    }
}
