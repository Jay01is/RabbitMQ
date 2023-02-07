package com.lss.provider.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * ClassName: RabbitMqModelController
 * MQ模型练习
 * @author wyk
 * @version 1.0
 * @date 2023/2/1 16:48
 */
@Slf4j
@RestController
public class RabbitMqModelController {

    @Autowired
    private RabbitTemplate rabbitTemplate;

    /**
     * hello world 模型
     * @return
     */
    @GetMapping("hello")
    public String hello(String name) {
        // 没有路由键 那路由键就是队列名
        rabbitTemplate.convertAndSend("","hello",name);
        return "hello " + name;
    }

    /**
     * work 模型
     * @param name
     * @return
     */
    @GetMapping("work")
    public String work(String name) {
        // 没有路由键 那路由键就是队列名
        rabbitTemplate.convertAndSend("","work",name);
        return "work " + name;
    }

    /**
     * Publish/Subscribe 模型
     * @param name
     * @return
     */
    @GetMapping("publish")
    public String publish(String name) {
        rabbitTemplate.convertAndSend("publish","",name);
        return "publish " + name;
    }

    /**
     * 路由键Routing 模型
     * @param name
     * @return
     */
    @GetMapping("routing")
    public String routing(String name, String routingKey) {
        rabbitTemplate.convertAndSend("Routing", routingKey, name);
        return "Routing " + name;
    }

    /**
     * topic 模型
     * @param name
     * @return
     */
    @GetMapping("topic")
    public String topic(String name, String routingKey) {
        rabbitTemplate.convertAndSend("topic",routingKey,name);
        return "topic " + name;
    }

    /**
     * rcp 模型
     * @param name
     * @return
     */
    @GetMapping("rcp")
    public String rcp(String name) {
        rabbitTemplate.convertAndSend("","rcp",name);
        return "消息已成功发送! : " + name;
    }

    /**
     * rcp 监听
     * @param msg
     */
    @RabbitListener(queues = "rcp-back")
    public void rcpListener(String msg) {
        log.info("rcp-back " + msg);
    }


}
