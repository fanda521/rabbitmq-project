package net.anumbrella.rabbitmq.sender;


import com.rabbitmq.client.*;
import org.apache.commons.lang.StringUtils;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.IOException;
import java.util.Collections;
import java.util.HashMap;
import java.util.UUID;
import java.util.concurrent.TimeoutException;

/**
 * 这是java原生类支持RabbitMQ，直接运行该类
 *
 * 不能像传统的“手动确认”，就是不能代码上处理，但是可以通过addConfirmListener和waitForConfirms得知是否接收成功
 */
public class ConfirmSender1 {

    private final static String QUEUE_NAME = "confirm";

    public static void main(String[] args) throws IOException, TimeoutException, InterruptedException {
        /**
         * 创建连接连接到RabbitMQ
         */
        ConnectionFactory factory = new ConnectionFactory();

        // 设置MabbitMQ所在主机ip或者主机名
        factory.setUsername("guest");
        factory.setPassword("guest");
        factory.setHost("127.0.0.1");
        factory.setVirtualHost("/");
        factory.setPort(5672);

        // 创建一个连接
        Connection connection = factory.newConnection();

        // 创建一个频道
        Channel channel = connection.createChannel();

        // 指定一个队列
        channel.queueDeclare(QUEUE_NAME, false, false, false, null);
        // 发送的消息
        String message = "This is a confirm message！";

        channel.confirmSelect();
        final long start = System.currentTimeMillis();

        channel.addConfirmListener(new ConfirmListener() {

            @Override
            public void handleNack(long deliveryTag, boolean multiple) throws IOException {
                System.out.println("nack: deliveryTag = " + deliveryTag + " multiple: " + multiple);
            }

            @Override
            public void handleAck(long deliveryTag, boolean multiple) throws IOException {
                System.out.println("ack: deliveryTag = " + deliveryTag + " multiple: " + multiple);
//                try {
//                    Thread.sleep(2000);
//                } catch (InterruptedException e) {
//                    e.printStackTrace();
//                }
            }
        });


        //发送持久化消息
        for (int i = 0; i < 5; i++) {
            //第一个参数是exchangeName(默认情况下代理服务器端是存在一个""名字的exchange的,
            //因此如果不创建exchange的话我们可以直接将该参数设置成"",如果创建了exchange的话
            //我们需要将该参数设置成创建的exchange的名字),第二个参数是路由键;
            // 使用 BasicProperties.Builder 来构建消息属性
            AMQP.BasicProperties.Builder builder = new AMQP.BasicProperties.Builder();
            builder.deliveryMode(2); // 设置消息为持久化
            UUID messageId = UUID.randomUUID();
            builder.messageId(messageId.toString());

            AMQP.BasicProperties properties = builder.build();

            channel.basicPublish("", QUEUE_NAME, properties, (" Confirm， " + (i + 1) + "message").getBytes());
            try {
                if (channel.waitForConfirms(1000)) {
                    System.out.println("send success! ");
                } else {
                    // 进行消息重发
                }
            } catch (Exception e) {
                System.out.println("send failed！" + e.getMessage());
            }

        }


        System.out.println("执行waitForConfirms耗费时间: " + (System.currentTimeMillis() - start) + "ms");

        // 关闭频道和连接
        channel.close();
        connection.close();
        Thread.sleep(1000000);
    }

}
