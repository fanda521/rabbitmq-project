package com.jeffrey.springbootrabbitmq.consumer.topic;
 
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;
 
/**
 * 路径：com.example.demo.rabbitmq.service.topic
 * 类名：
 * 功能：topic ExChange示例
 * 备注：消费者2（topic.messages）
 * 创建人：typ
 * 创建时间：2018/9/24 20:12
 * 修改人：
 * 修改备注：
 * 修改时间：
 */
@Component
@RabbitListener(queues = "topic.messages")
public class TopicReceiver2 {
 
    private static final Logger log = LoggerFactory.getLogger(TopicReceiver2.class);
 
    @RabbitHandler
    public void process(String msg) {
        log.info("topicReceiver2 : " +msg);
    }
 
}