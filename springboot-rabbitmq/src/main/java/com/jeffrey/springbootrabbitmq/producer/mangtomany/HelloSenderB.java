package com.jeffrey.springbootrabbitmq.producer.mangtomany;
 
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
 
import java.util.Date;
 
/**
 * 路径：com.example.demo.rabbitmq.service
 * 类名：
 * 功能：生产者2
 * 备注：多生产者-多消费者
 * 创建人：typ
 * 创建时间：2018/9/23 21:49
 * 修改人：
 * 修改备注：
 * 修改时间：
 */
@Component
public class HelloSenderB {
 
    private static final Logger log = LoggerFactory.getLogger(HelloSenderB.class);
 
    @Autowired
    public AmqpTemplate amqpTemplate;
 
    public void send(String msg){
        String context = msg + new Date();
        log.info("SenderB:" + context);
        this.amqpTemplate.convertAndSend("hello",context);
    }
}