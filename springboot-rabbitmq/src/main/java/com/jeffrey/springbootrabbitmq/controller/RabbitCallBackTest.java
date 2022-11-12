package com.jeffrey.springbootrabbitmq.controller;
 

import com.jeffrey.springbootrabbitmq.producer.callback.CallBackSender;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
 
/**
 * 路径：com.example.demo.rabbitmq.controller
 * 类名：
 * 功能：callback的消息发送
 * 备注：
 * 创建人：typ
 * 创建时间：2018/9/24 22:20
 * 修改人：
 * 修改备注：
 * 修改时间：
 */
@RestController
public class RabbitCallBackTest {
 
    @Autowired
    private CallBackSender callBackSender;
 
    //执行代码可以看出callbackSender发出的UUID，收到了回应，又传回来了。
    @PostMapping("/callback")
    public void callbak() {
        callBackSender.send();
    }
}