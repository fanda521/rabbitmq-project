package com.jeffrey.springbootrabbitmq.consumer.entity;
 
import com.jeffrey.springbootrabbitmq.entity.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;
 
/**
 * 路径：com.example.demo.rabbitmq.service.entity
 * 类名：
 * 功能：实体类传输
 * 备注：消费者
 * 创建人：typ
 * 创建时间：2018/9/24 20:07
 * 修改人：
 * 修改备注：
 * 修改时间：
 */
@Component
@RabbitListener(queues = "user")
public class UserReceiver {
 
    private static final Logger log = LoggerFactory.getLogger(UserReceiver.class);
 
    @RabbitHandler
    public void process(User user) {
        log.info("user Receive:" + user.getName() + "," + user.getPass());
    }
}