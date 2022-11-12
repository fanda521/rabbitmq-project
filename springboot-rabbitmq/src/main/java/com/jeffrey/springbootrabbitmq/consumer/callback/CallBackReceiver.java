package com.jeffrey.springbootrabbitmq.consumer.callback;
 
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;
 
/**
 * 路径：com.example.demo.rabbitmq.service.callback
 * 类名：
 * 功能：callback的消息发送
 * 备注：消费者
 * 创建人：typ
 * 创建时间：2018/9/24 20:12
 * 修改人：
 * 修改备注：
 * 修改时间：
 */
@Component
@RabbitListener(queues = "topic.messages")
public class CallBackReceiver {
 
    private static final Logger log = LoggerFactory.getLogger(CallBackReceiver.class);
 
    @RabbitHandler
    public void process(String msg) {
        log.info("CallBackReceiver : " +msg);
    }
 
}