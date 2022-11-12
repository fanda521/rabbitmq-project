package com.jeffrey.springbootrabbitmq.producer.entity;
 
import com.jeffrey.springbootrabbitmq.entity.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
 
/**
 * 路径：com.example.demo.rabbitmq.service.entity
 * 类名：
 * 功能：实体类传输
 * 备注：生产者
 * 创建人：typ
 * 创建时间：2018/9/24 20:01
 * 修改人：
 * 修改备注：
 * 修改时间：
 */
@Component
public class UserSender {
 
    private static final Logger log = LoggerFactory.getLogger(UserSender.class);
 
    @Autowired
    private AmqpTemplate amqpTemplate;
 
    public void send() {
        User user = new User();
        user.setName("test");
        user.setPass("123456");
        log.info("user Sender:" + user.getName() + "," + user.getPass());
        amqpTemplate.convertAndSend("user", user);
    }
}