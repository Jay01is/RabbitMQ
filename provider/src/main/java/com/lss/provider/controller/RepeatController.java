package com.lss.provider.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.connection.CorrelationData;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

/**
 * ClassName: RepeatController
 * 消息重复消费的提供者
 * @author wyk
 * @version 1.0
 * @date 2023/2/2 18:53
 */
@Slf4j
@RestController
@SuppressWarnings("all")
public class RepeatController {

    @Autowired
    private RabbitTemplate rabbitTemplate;

    /**
     * 发送消息
     * 防止消息的重复消费就是保证幂等性
     */
    @GetMapping("/repeat")
    public void send(String msg) {
//        for (int i = 0; i < 5; i++) {
            // 给消息设置一个唯一标识
//            CorrelationData c = new CorrelationData(UUID.randomUUID().toString());
            CorrelationData c = new CorrelationData("y");
            rabbitTemplate.convertAndSend(
                    "repeat-exchange",
                    "lss.lss",
                    msg,
                    c // 需要有Confirm机制才能带过去
            );
//        }
        log.info("消息发送成功!");
    }

}
