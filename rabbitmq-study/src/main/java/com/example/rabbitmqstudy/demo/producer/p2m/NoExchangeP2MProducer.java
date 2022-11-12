package com.example.rabbitmqstudy.demo.producer.p2m;

import com.example.rabbitmqstudy.demo.utils.AMQPUtil;
import com.rabbitmq.client.AMQP;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 * @version 1.0
 * @Aythor lucksoul 王吉慧
 * @date 2022/8/16 19:32
 * @description 没有交换机的 p2m的生产者
 */
public class NoExchangeP2MProducer {
    public static void main(String[] args) throws IOException, TimeoutException {
        //获取连接
        Connection connection = AMQPUtil.getConnection();
        //mq提供Channel来将处理消息
        //创建Channel
        Channel channel = connection.createChannel();
        String msg = "NoExchangeP2M";
        AMQP.Queue.DeclareOk queueDeclare = channel.queueDeclare("NoExchangeP2M-Queue", true, false, false, null);
        //basicPublish将消息发送到指定的交换机
        /**
         * 1、exchange： 交换机的名称
         *
         * 2、routingKey： 路由键
         *
         * 3、mandatory： true，exchange根据自身类型和消息routingKey没有找到合适的队列queue，那么就会调用basic.return将消息返回给生产者，若为false，则broker把消息丢弃
         *
         * 4、immediate： true，当消息通过exchange路由到queue的时候，发现这个queue上面没有消费者，那么就不会进入这个queue，如果根据routingKey匹配到的所有的队列都是没有消费者的，那么就会调用basic.return方法返回给生产者
         *
         * 5、props： 表示消息的持久化，配合channel和queue的durable使用
         *
         * 6、body： 发送的消息
         */

        /**
         * 交换机：没有则留空
         * 队列名：key 后者队列名 ，这里记得指定队列名，否则队列的消息为空 total 0
         * 其他属性：
         * 消息内容：
         */
        for (int i = 0; i < 10 ; i ++) {
            channel.basicPublish("", "NoExchangeP2M-Queue", true,false,null, (msg +" " +(i+1)).getBytes());
            System.out.println("NoExchangeP2M-Queue Producer messgae:" + msg +" " +(i+1));
        }
        //关闭连接
        //channel.close();
        //connection.close();
    }
}
