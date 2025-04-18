package com.jeffrey.springbootrabbitmq.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

/**
 * 路径：com.example.demo.rabbitmq.service.callback
 * 类名：
 * 功能：增加回调处理，这里不再使用application.properties默认配置的方式，会在程序中显示的使用文件中的配置信息。
 * 备注：
 * 创建人：typ
 * 创建时间：2018/9/24 20:09
 * 修改人：
 * 修改备注：
 * 修改时间：
 */
@Configuration
public class RabbitConfig2 {

    private static final Logger log = LoggerFactory.getLogger(RabbitConfig.class);

    @Value("${spring.rabbitmq.host}")
    private String addresses;

    @Value("${spring.rabbitmq.port}")
    private String port;

    @Value("${spring.rabbitmq.username}")
    private String username;

    @Value("${spring.rabbitmq.password}")
    private String password;

    @Value("${spring.rabbitmq.virtual-host}")
    private String virtualHost;

    @Value("${spring.rabbitmq.publisher-confirms}")
    private boolean publisherConfirms;

    @Bean
    public ConnectionFactory connectionFactory() {

        CachingConnectionFactory connectionFactory = new CachingConnectionFactory();
        connectionFactory.setAddresses(addresses+":"+port);
        connectionFactory.setUsername(username);
        connectionFactory.setPassword(password);
        connectionFactory.setVirtualHost(virtualHost);

        //如果要进行消息回调，则这里必须要设置为true
        connectionFactory.setPublisherConfirms(publisherConfirms);
        return connectionFactory;
    }

    //因为要设置回调类，所以应是prototype类型，如果是singleton类型，则回调类为最后一次设置
    @Bean
    @Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
    public RabbitTemplate rabbitTemplatenew() {
        RabbitTemplate template = new RabbitTemplate(connectionFactory());
        return template;
    }

}