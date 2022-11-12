package com.jeffrey.springbootrabbitmq.consumer.fanout;
 
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;
 
/**
 * 路径：com.example.demo.rabbitmq.service.fanout
 * 类名：
 * 功能：fanout ExChange示例
 * 备注：消费者A
 * 创建人：typ
 * 创建时间：2018/9/24 21:10
 * 修改人：
 * 修改备注：
 * 修改时间：
 */
@Component
@RabbitListener(queues = "fanout.A")
public class FanoutReceiverA {
 
    private static final Logger log = LoggerFactory.getLogger(FanoutReceiverA.class);
 
    @RabbitHandler
    public void process(String msg) {
        log.info("FanoutReceiverA  : " + msg);
    }
 
}