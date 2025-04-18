package com.jeffrey.springbootrabbitmq.producer;
 
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
 
import java.util.Date;
 
/**
 * 路径：com.example.demo.rabbitmq.service
 * 类名：
 * 功能：生产者
 * 备注：单生产者-单消费者
 * 创建人：typ
 * 创建时间：2018/9/23 21:49
 * 修改人：
 * 修改备注：
 * 修改时间：
 */
@Component
public class HelloSender {
 
    private static final Logger log = LoggerFactory.getLogger(HelloSender.class);
 
    @Autowired
    public AmqpTemplate amqpTemplate;
 
    public void send(){
        String context = "hello " + new Date();
        log.info("Sender:" + context);
        System.out.println("Sender:" + context);
        this.amqpTemplate.convertAndSend("hello",context);
    }
}