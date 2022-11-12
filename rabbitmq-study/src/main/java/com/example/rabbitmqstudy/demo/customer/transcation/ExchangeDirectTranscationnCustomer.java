package com.example.rabbitmqstudy.demo.customer.transcation;

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
public class ExchangeDirectTranscationnCustomer {
    public static void main(String[] args) throws IOException, TimeoutException {
        //获取连接
        Connection connection = AMQPUtil.getConnection();
        //mq提供Channel来将处理消息
        //创建Channel
        Channel channel = connection.createChannel();

        String queueName1 ="ExchangeDirectTranscation-Queue";
        String exchangeName = "Direct-Transcation-Exchange";
        String routingKey1 ="Direct-Transcation-Key";

        AMQP.Queue.DeclareOk queueDeclare = channel.queueDeclare(queueName1, true, false, false, null);
        AMQP.Exchange.DeclareOk exchangeDeclare = channel.exchangeDeclare(exchangeName, "direct");
        channel.queueBind(queueName1,exchangeName,routingKey1,null);
        try {
            channel.txSelect();
            int i = 0;
            channel.basicConsume(queueName1,true,"",false,false,null,new DefaultConsumer(channel) {
                @Override
                public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
                    String s = new String(body, "utf-8");
                    System.out.println("ExchangeDirectTranscation-Queue Consumer message:" + s);
                    try {
                        if (i == 1) {
                            int j=10/0;
                        }
                        channel.txCommit();
                    }catch (Exception e) {
                        System.out.println("异常开始回滚");
                        Channel channel1 = this.getChannel();
                        channel1.txRollback();
                    }
                }
            });
        }catch (Exception e) {
            System.out.println("异常回滚");
            channel.txRollback();
        }finally {
            //关闭连接
            //channel.close();
            //connection.close();
        }
    }
}
