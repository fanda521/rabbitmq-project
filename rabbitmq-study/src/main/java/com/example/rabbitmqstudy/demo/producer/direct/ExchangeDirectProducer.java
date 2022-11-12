package com.example.rabbitmqstudy.demo.producer.direct;

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
public class ExchangeDirectProducer {
    public static void main(String[] args) throws IOException, TimeoutException {
        //获取连接
        Connection connection = AMQPUtil.getConnection();
        //mq提供Channel来将处理消息
        //创建Channel
        Channel channel = connection.createChannel();
        String msg = "ExchangeDirect";
        String msg2 = "ExchangeDirect2";
        String msg3 = "ExchangeDirect3";
        String queueName1 ="ExchangeDirect-Queue-One";
        String queueName2 ="ExchangeDirect-Queue-Two";
        String queueName3 ="ExchangeDirect-Queue-Three";
        String exchangeName = "Direct-Exchange";
        String routingKey1 ="Direct-Key-One";
        String routingKey2 ="Direct-Key-Two";
        String routingKey3 ="Direct-Key-Three";
        AMQP.Queue.DeclareOk queueDeclare = channel.queueDeclare(queueName1, true, false, false, null);
        AMQP.Queue.DeclareOk queueDeclare2 = channel.queueDeclare(queueName2, true, false, false, null);
        AMQP.Queue.DeclareOk queueDeclare3 = channel.queueDeclare(queueName3, true, false, false, null);

        AMQP.Exchange.DeclareOk exchangeDeclare = channel.exchangeDeclare(exchangeName,"direct");

        channel.queueBind(queueName1,exchangeName,routingKey1,null);
        channel.queueBind(queueName2,exchangeName,routingKey2,null);
        channel.queueBind(queueName3,exchangeName,routingKey3,null);
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
        channel.basicPublish(exchangeName, routingKey1, true,false,null, msg.getBytes());
        channel.basicPublish(exchangeName, routingKey2, true,false,null, msg2.getBytes());
        channel.basicPublish(exchangeName, routingKey3, true,false,null, msg3.getBytes());
        System.out.println("ExchangeDirect-Queue Producer messgae:" + msg);
        System.out.println("ExchangeDirect-Queue Producer messgae:" + msg2);
        System.out.println("ExchangeDirect-Queue Producer messgae:" + msg3);

        //关闭连接
        //channel.close();
        //connection.close();
    }
}
