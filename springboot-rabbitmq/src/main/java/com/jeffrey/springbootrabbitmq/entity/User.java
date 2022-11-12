package com.jeffrey.springbootrabbitmq.entity;
 
import java.io.Serializable;
 
/**
 * 路径：com.example.demo.rabbitmq.service.entity
 * 类名：
 * 功能：《用一句描述一下》
 * 备注：
 * 创建人：typ
 * 创建时间：2018/9/24 19:59
 * 修改人：
 * 修改备注：
 * 修改时间：
 */
public class User implements Serializable{
 
    private String name;
    private String pass;
 
    public String getName() {
        return name;
    }
 
    public void setName(String name) {
        this.name = name;
    }
 
    public String getPass() {
        return pass;
    }
 
    public void setPass(String pass) {
        this.pass = pass;
    }
}