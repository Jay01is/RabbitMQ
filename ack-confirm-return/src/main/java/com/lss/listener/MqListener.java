package com.lss.listener;

import com.rabbitmq.client.Channel;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/**
 * ClassName: MqListener
 *
 * @author wyk
 * @version 1.0
 * @date 2023/2/2 16:48
 */
@Slf4j
@Component
@SuppressWarnings("all")
public class MqListener {

    /**
     * 监听消费
     * @param msg 接收的消息
     * @param channel 信道
     * @param message 信息
     * @throws Exception
     */
    @RabbitListener(queues = "return-queue")
    public void msg(String msg, Channel channel, Message message) throws Exception {
        // 手动应答
        channel.basicAck(message.getMessageProperties().getDeliveryTag(),
                false // 批量删除
        );
        log.info("消息为：{}", msg);
    }

}
