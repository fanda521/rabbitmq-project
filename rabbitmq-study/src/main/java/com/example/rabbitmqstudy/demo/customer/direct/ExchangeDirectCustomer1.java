package com.example.rabbitmqstudy.demo.customer.direct;

import com.example.rabbitmqstudy.demo.utils.AMQPUtil;
import com.rabbitmq.client.*;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 * @version 1.0
 * @Aythor lucksoul 王吉慧
 * @date 2022/8/16 20:35
 * @description
 */
public class ExchangeDirectCustomer1 {
    public static void main(String[] args) throws IOException, TimeoutException {
        //获取连接
        Connection connection = AMQPUtil.getConnection();
        //mq提供Channel来将处理消息
        //创建Channel
        Channel channel = connection.createChannel();

        String queueName1 ="ExchangeDirect-Queue-One";
        String exchangeName = "Direct-Exchange";
        String routingKey1 ="Direct-Key-One";

        AMQP.Queue.DeclareOk queueDeclare = channel.queueDeclare(queueName1, true, false, false, null);
        AMQP.Exchange.DeclareOk exchangeDeclare = channel.exchangeDeclare(exchangeName, "direct");
        channel.queueBind(queueName1,exchangeName,routingKey1,null);


        channel.basicConsume(queueName1,true,"",false,false,null,new DefaultConsumer(channel) {
            @Override
            public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
                String s = new String(body, "utf-8");
                System.out.println("ExchangeDirect-Queue Consumer1 message:" + s);
            }
        });
        //关闭连接
        //channel.close();
        //connection.close();
    }
}
