package com.lss.provider.config;

import com.lss.provider.controller.SendMailController;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.connection.CorrelationData;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

/**
 * ClassName: ConfirmAndReturn
 *
 * @author wyk
 * @version 1.0
 * @date 2023/2/2 18:58
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
     * confirm 到达交换机
     * @param correlationData
     * @param ack
     * @param cause
     */
    @Override
    public void confirm(CorrelationData correlationData, boolean ack, String cause) {
        if (ack) {
            log.info("到达交换机!");
        } else {
            log.info("没到交换机!");
        }
    }

    /**
     * return 到达队列 没出错就不会触发
     * @param message
     * @param replyCode
     * @param replyText
     * @param exchange
     * @param routingKey
     */
    @Override
    public void returnedMessage(Message message, int replyCode, String replyText, String exchange, String routingKey) {
        log.info("出事情了:message={},replyCode={},replyText={},exchange={},routingKey={}",message,replyCode,replyText,exchange,routingKey);
    }
}
