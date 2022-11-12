package com.example.rabbitmqstudy.demo.customer.confirm;

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
public class DirectAckCustomer {
    public static void main(String[] args) throws IOException, TimeoutException {
        //获取连接
        Connection connection = AMQPUtil.getConnection();
        //mq提供Channel来将处理消息
        //创建Channel
        Channel channel = connection.createChannel();

        String queueName1 ="DirectAck-Queue";
        String exchangeName = "Direct-Ack-Exchange";
        String routingKey1 ="Direct-Ack-Key";

        AMQP.Queue.DeclareOk queueDeclare = channel.queueDeclare(queueName1, true, false, false, null);
        AMQP.Exchange.DeclareOk exchangeDeclare = channel.exchangeDeclare(exchangeName, "direct");
        channel.queueBind(queueName1,exchangeName,routingKey1,null);
        try {
            channel.txSelect();
            channel.basicConsume(queueName1,false,"",false,false,null,new DefaultConsumer(channel) {
                @Override
                public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
                    String s = new String(body, "utf-8");
                    System.out.println("DirectAck-Queue Consumer message:" + s);
                    //消息的编号
                    long deliveryTag = envelope.getDeliveryTag();
                    Channel channel1 = this.getChannel();
                    channel1.basicAck(deliveryTag,true);
                    //channel1.basicAck(deliveryTag,true); 也能达到同样的效果，
                    //channel channel1 应该是同一个
                    channel1.txCommit();
                }
            });
            //channel.txCommit();//这个是不起作用的
        }catch (Exception e) {
            System.out.println("异常");
            channel.txRollback();
        }finally {
            //关闭连接
            //channel.close();
            //connection.close();
        }
    }
}
