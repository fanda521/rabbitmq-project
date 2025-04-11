package net.anumbrella.rabbitmq.sender.failedexample;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

public class BlockingProducerExample {

    private final static String QUEUE_NAME = "blocking_queue";

    public static void main(String[] args) throws Exception {
        ConnectionFactory factory = new ConnectionFactory();
        // 设置MabbitMQ所在主机ip或者主机名
        factory.setUsername("guest");
        factory.setPassword("guest");
        factory.setHost("127.0.0.1");
        factory.setVirtualHost("/");
        factory.setPort(5672);
        Connection connection = factory.newConnection();
        Channel channel = connection.createChannel();

        // 声明队列（持久化、非排他、非自动删除）
        channel.queueDeclare(QUEUE_NAME, true, false, false, null);

        // 模拟发送消息失败的情况（例如网络问题）
        boolean simulateFailure = true;

        for (int i = 0; i < 10; i++) {
            String message = "Message " + i;
            int retryCount = 0;
            while (true) {
                try {
                    System.out.println("Sending: " + message);
                    // 发送消息，但不处理发送失败的情况
                    channel.basicPublish("", QUEUE_NAME, null, message.getBytes());
                    // 如果发送失败，此处会抛出异常，但并未处理
                    // 如果 simulateFailure 为 true，模拟发送失败并抛出异常
                    if (simulateFailure && i % 2 == 0) {
                        throw new RuntimeException("Simulated send failure");
                    }

                    System.out.println("Sent: " + message);
                    break;
                } catch (Exception e) {
                    // 未实现重试逻辑，直接打印异常
                    System.err.println("Send failed: " + e.getMessage());
                    // 未处理失败，继续尝试发送下一条消息（可能导致阻塞）
                    if (retryCount == 2) {
                        System.out.println(message + ", send failed! retryCount =" + retryCount);
                        break;
                    }
                    retryCount += 1;
                    Thread.sleep(2000);
                }
            }
        }

        // 关闭连接
        //channel.close();
        //connection.close();
        Thread.sleep(10000000);
    }
}