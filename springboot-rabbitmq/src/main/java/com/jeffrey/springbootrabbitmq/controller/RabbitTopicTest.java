package com.jeffrey.springbootrabbitmq.controller;
 

import com.jeffrey.springbootrabbitmq.producer.topic.TopicSender;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
 
/**
 * 路径：com.example.demo.rabbitmq.controller
 * 类名：
 * 功能：topic ExChange示例
 * 备注：
 * 创建人：typ
 * 创建时间：2018/9/24 20:21
 * 修改人：
 * 修改备注：
 * 修改时间：
 */
@RestController
public class RabbitTopicTest {
 
    @Autowired
    private TopicSender topicSender;
 
    @PostMapping("/topicTest")
    public void topicTest(){
        topicSender.send();
    }
}