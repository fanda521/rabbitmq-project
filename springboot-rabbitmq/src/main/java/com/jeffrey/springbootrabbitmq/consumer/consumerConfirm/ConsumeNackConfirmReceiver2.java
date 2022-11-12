package com.jeffrey.springbootrabbitmq.consumer.consumerConfirm;


import com.rabbitmq.client.*;
import lombok.SneakyThrows;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeoutException;

/**
 * 这是java原生类支持RabbitMQ，直接运行该类
 */
public class ConsumeNackConfirmReceiver2 {

    private final static String QUEUE_NAME = "consumer_nack2_confirm";

    public static void main(String[] argv) throws IOException, InterruptedException, TimeoutException {

        ConnectionFactory factory = new ConnectionFactory();

        factory.setUsername("guest");
        factory.setPassword("guest");
        factory.setHost("127.0.0.1");
        factory.setVirtualHost("/");
        factory.setPort(5672);
        // 打开连接和创建频道，与发送端一样

        Connection connection = factory.newConnection();
        Channel channel = connection.createChannel();

        // 声明队列，主要为了防止消息接收者先运行此程序，队列还不存在时创建队列。
        channel.queueDeclare(QUEUE_NAME, false, false, false, null);
        System.out.println("ConfirmReceiver1 waiting for messages. To exit press CTRL+C");
        //将Qos预取值设置为1,这表示设置broker每次只推送队列里面的一条消息到消费者,只有在确认这条消息"成功消费"后,才会继续推送
        channel.basicQos(0,1,false);
        // 创建队列消费者
        final Consumer consumer = new DefaultConsumer(channel) {
            @SneakyThrows
            @Override
            public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties,
                                       byte[] body) throws IOException {
                SimpleDateFormat time = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss:SSSS");
                Thread.sleep(2000);
                String message = new String(body, "UTF-8");
                channel.basicNack(envelope.getDeliveryTag(),false,true);
                System.out.println(" ConfirmReceiver1  : "  + message);
                System.out.println(" ConfirmReceiver1 Done! at " + time.format(new Date()));
            }
        };
        channel.basicConsume(QUEUE_NAME, false, consumer);
    }
}
