package com.lss.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.connection.CorrelationData;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

/**
 * ClassName: ConfirmAndReturn
 * ConfirmAndReturn配置
 * @author wyk
 * @version 1.0
 * @date 2023/2/2 16:51
 */
@Slf4j
@Component
public class ConfirmAndReturn implements RabbitTemplate.ConfirmCallback , RabbitTemplate.ReturnCallback {

    @Autowired
    private RabbitTemplate rabbitTemplate;

    /**
     * @PostConstruct 生命周期注解 在依赖注入之后执行
     * Spring的生命周期:
     * 1.实例化
     * 2.属性赋值(依赖注入)
     * 3.初始化
     * 告诉 rabbitTemplate Confirm 和 Return
     */
    @PostConstruct
    public void init() {
        rabbitTemplate.setConfirmCallback(this);
        rabbitTemplate.setReturnCallback(this);
    }


    /**
     * 到交换机
     * @param correlationData
     * @param ack
     * @param cause
     */
    @Override
    public void confirm(CorrelationData correlationData, boolean ack, String cause) {
        if (ack) {
            log.info("消息已经到达交换机!");
        } else {
            log.info("correlationData={},ack={},cause={}",correlationData,ack,cause);
            log.info("消息未到达交换机");
        }
    }

    /**
     * 到达队列
     * @param message
     * @param replyCode
     * @param replyText
     * @param exchange
     * @param routingKey
     */
    @Override
    public void returnedMessage(Message message, int replyCode, String replyText, String exchange, String routingKey) {
        log.info("消息没有到达队列: Message={}, replyCode={}, replyText={}, exchange={}, routingKey={}",
                message, replyCode, replyText, exchange, routingKey);
    }
}
