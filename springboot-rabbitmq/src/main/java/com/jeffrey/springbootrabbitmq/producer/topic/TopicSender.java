package com.jeffrey.springbootrabbitmq.producer.topic;
 
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
 
/**
 * 路径：com.example.demo.rabbitmq.service.topic
 * 类名：
 * 功能：topic ExChange示例------生产者
 * 备注：topic 是RabbitMQ中最灵活的一种方式，可以根据binding_key自由的绑定不同的队列
 * 创建人：typ
 * 创建时间：2018/9/24 20:12
 * 修改人：
 * 修改备注：
 * 修改时间：
 */
@Component
public class TopicSender {
 
    private static final Logger log = LoggerFactory.getLogger(TopicSender.class);
 
    @Autowired
    private AmqpTemplate rabbitTemplate;
 
    public void send() {
        String msg1 = "I am topic.mesaage msg1!";
        log.info("sender1 : " + msg1);
        this.rabbitTemplate.convertAndSend("exchange", "topic.message", msg1);
 
        String msg2 = "I am topic.mesaages msg2!";
        log.info("sender2 : " + msg2);
        this.rabbitTemplate.convertAndSend("exchange", "topic.messages", msg2);
    }
}