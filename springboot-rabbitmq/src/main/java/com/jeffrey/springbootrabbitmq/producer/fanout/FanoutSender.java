package com.jeffrey.springbootrabbitmq.producer.fanout;
 
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
 
/**
 * 路径：com.example.demo.rabbitmq.service.fanout
 * 类名：
 * 功能：fanout ExChange示例---生产者
 * 备注：Fanout 就是我们熟悉的广播模式或者订阅模式，给Fanout转发器发送消息，绑定了这个转发器的所有队列都收到这个消息。
 * 创建人：typ
 * 创建时间：2018/9/24 21:10
 * 修改人：
 * 修改备注：
 * 修改时间：
 */
@Component
public class FanoutSender {
 
    private static final Logger log = LoggerFactory.getLogger(FanoutSender.class);
 
    @Autowired
    private AmqpTemplate rabbitTemplate;
 
    public void send() {
        String msg="fanoutSender :hello i am fanout";
        log.info(msg);
        this.rabbitTemplate.convertAndSend("fanoutExchange","abcd.ee", msg);
    }
}