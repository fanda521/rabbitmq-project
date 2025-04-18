package com.jeffrey.springbootrabbitmq.controller;
 

import com.jeffrey.springbootrabbitmq.producer.HelloSender1;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
 
/**
 * 路径：com.example.demo.rabbitmq.controller
 * 类名：
 * 功能：《用一句描述一下》
 * 备注：单生产者-多消费者
 * 创建人：typ
 * 创建时间：2018/9/23 22:35
 * 修改人：
 * 修改备注：
 * 修改时间：
 */
@RestController
public class RabbitOneToManyTest {
 
    @Autowired
    private HelloSender1 helloSender;
    
    /**
     * 方法名：
     * 功能：单生产者-多消费者
     * 描述：
     * 创建人：typ
     * 创建时间：2018/9/23 22:46
     * 修改人：
     * 修改描述：
     * 修改时间：
     */
    @PostMapping("/oneToMany")
    public void ontToMany(){
        for (int i=0;i<10;i++){
            helloSender.send("hello smg:"+i);
        }
    }
}