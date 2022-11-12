package com.example.rabbitmqstudy.demo.utils;


import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 * @version 1.0
 * @Aythor lucksoul 王吉慧
 * @date 2022/8/16 19:34
 * @description
 */
public class AMQPUtil {
    public static Connection getConnection() throws IOException, TimeoutException {
        //1.创建连接工厂
        ConnectionFactory factory = new ConnectionFactory();//MQ采用工厂模式来完成连接的创建
        //2.在工厂对象中设置连接信息(ip,port,virtualhost,username,password)
        factory.setHost("192.168.31.166");//设置MQ安装的服务器ip地址
        factory.setPort(5672);//设置端口号
        factory.setVirtualHost("/");//设置虚拟主机名称
        //MQ通过用户来管理
        factory.setUsername("admin");//设置用户名称
        factory.setPassword("admin");//设置用户密码
        //3.通过工厂对象获取连接
        Connection connection = factory.newConnection();
        return connection;
    }

}
