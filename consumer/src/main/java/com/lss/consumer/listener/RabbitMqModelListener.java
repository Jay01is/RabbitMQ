package com.lss.consumer.listener;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * ClassName: RabbitMqModelListener
 * 监听消息
 * @author wyk
 * @version 1.0
 * @date 2023/2/1 16:50
 */
@Slf4j
@Component
public class RabbitMqModelListener {

    /**
     * hello world 模型
     * @param msg
     */
    @RabbitListener(queues = "hello")
    public void hello(String msg) {
        System.out.println("消费的消息为:" + msg);
    }

    /**
     * work 模型
     * @param msg
     */
    @RabbitListener(queues = "work")
    public void work(String msg) {
        System.out.println("消费的消息为:" + msg);
    }

    /**
     * Publish/Subscribe 模型
     * @param msg
     */
    @RabbitListener(queues = "publish-1")
    public void publish1(String msg) {
        System.out.println("消费的消息为:" + msg);
    }

    /**
     * Publish/Subscribe 模型
     * @param msg
     */
    @RabbitListener(queues = "publish-2")
    public void publish2(String msg) {
        System.out.println("消费的消息为:" + msg);
    }

    /**
     * routing 模型
     * @param msg
     */
    @RabbitListener(queues = "routing-1")
    public void routing1(String msg) {
        System.out.println("消费的消息为:" + msg);
    }

    /**
     * routing 模型
     * @param msg
     */
    @RabbitListener(queues = "routing-2")
    public void routing2(String msg) {
        System.out.println("消费的消息为:" + msg);
    }

    /**
     * topic 模型
     * @param msg
     */
    @RabbitListener(queues = "topic-1")
    public void topic1(String msg) {
        System.out.println("消费的消息为:" + msg);
    }

    /**
     * topic 模型
     * @param msg
     */
    @RabbitListener(queues = "topic-2")
    public void topic2(String msg) {
        System.out.println("消费的消息为:" + msg);
    }

    @Autowired
    private RabbitTemplate rabbitTemplate;

    /**
     * rcp 模型
     * @param msg
     */
    @RabbitListener(queues = "rcp")
    public void rcp(String msg) {
        System.out.println("rcp " + msg);
        rabbitTemplate.convertAndSend("","rcp-back", msg);
    }
}
