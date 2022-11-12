package com.example.rabbitmqstudy.demo.producer.confirm;

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
public class DirectConfirmOneProducer {
    public static void main(String[] args) throws IOException, TimeoutException {
        //获取连接
        Connection connection = AMQPUtil.getConnection();
        //mq提供Channel来将处理消息
        //创建Channel
        Channel channel = connection.createChannel();
        String msg = "ExchangeDirectConfirmOne";
        String queueName1 ="DirectConfirmOne-Queue";
        String exchangeName = "Direct-ConfirmOne-Exchange";
        String routingKey1 ="Direct-ConfirmOne-Key";

        AMQP.Queue.DeclareOk queueDeclare = channel.queueDeclare(queueName1, true, false, false, null);
        AMQP.Exchange.DeclareOk exchangeDeclare = channel.exchangeDeclare(exchangeName,"direct");
        channel.queueBind(queueName1,exchangeName,routingKey1,null);

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

        try {
            //开启确认模式
            channel.confirmSelect();
            channel.basicPublish(exchangeName, routingKey1, true,false,null, msg.getBytes());
            //阻塞确认服务器是否接受成功，参数是超时时间，没有则不超时，一直阻塞直到服务器成功接收为止
            boolean b = channel.waitForConfirms(1000);
            System.out.println("是否确认成功：" + b);
            System.out.println("DirectConfirmOne-Queue Producer messgae:" + msg);
        }catch (Exception e){
            System.out.println("异常");
        }finally {
            //关闭连接
            channel.close();
            connection.close();
        }
    }
}
