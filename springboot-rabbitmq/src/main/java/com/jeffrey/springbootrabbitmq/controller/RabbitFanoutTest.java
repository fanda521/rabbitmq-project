package com.jeffrey.springbootrabbitmq.controller;
 

import com.jeffrey.springbootrabbitmq.producer.fanout.FanoutSender;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
 
/**
 * 路径：com.example.demo.rabbitmq.controller
 * 类名：
 * 功能：fanout ExChange示例
 * 备注：
 * 创建人：typ
 * 创建时间：2018/9/24 22:11
 * 修改人：
 * 修改备注：
 * 修改时间：
 */
@RestController
public class RabbitFanoutTest {
 
    @Autowired
    private FanoutSender fanoutSender;
 
    @PostMapping("/fanoutTest")
    public void fanoutTest() {
        fanoutSender.send();
    }
}