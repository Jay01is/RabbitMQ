package com.lss.consumer.listener;

import com.rabbitmq.client.Channel;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.connection.PublisherCallbackChannel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;

/**
 * ClassName: RepeatListener
 *
 * @author wyk
 * @version 1.0
 * @date 2023/2/2 18:57
 */
@Slf4j
@Component
public class RepeatListener {

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    /**
     * 监听队列
     * @param msg 发送来的消息
     * @param channel
     * @param message
     * @throws Exception
     */
    @RabbitListener(queues = "repeat-queue")
    public void xf(String msg , Channel channel , Message message) throws Exception {
        // 获取消息唯一的标识
        String msgId = message
                .getMessageProperties()
                .getHeader(PublisherCallbackChannel.RETURNED_MESSAGE_CORRELATION_KEY);
        log.info("msg = {}",msgId);
        // 用Redis的setnx结构来给此消息设置一个token 并设置十分钟过期时间 如果设置成功就代表此消息未消费
        if (stringRedisTemplate.opsForValue().setIfAbsent(msgId,"0",5, TimeUnit.MINUTES)) {
            log.info("第一条消息Ack");
            // 手动Ack
            channel.basicAck(message.getMessageProperties().getDeliveryTag(),false);
            // 消费完成则设置完成 0未消费 1已消费
            stringRedisTemplate.opsForValue().set(msgId,"1",5, TimeUnit.MINUTES);
        } else if ("1".equalsIgnoreCase(stringRedisTemplate.opsForValue().get(msgId))) {
            // 重复消息输出
            log.info("重复消息直接Ack");
            // 如果消费过直接Ack
            channel.basicAck(message.getMessageProperties().getDeliveryTag(),false);
        } else {
            log.info("未消费消息Nack");
            // 未消费重压入队列
            channel.basicNack(message.getMessageProperties().getDeliveryTag(),false,true);
        }
    }

}
