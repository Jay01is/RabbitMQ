package com.lss.consumer.listener;

import com.alibaba.fastjson2.JSON;
import com.lss.consumer.entity.SendMailError;
import com.lss.consumer.service.ISendMailErrorService;
import com.lss.entity.Email;
import com.lss.consumer.utils.EmailUtils;
import com.rabbitmq.client.Channel;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;

/**
 * ClassName: MailListener
 *
 * @author wyk
 * @version 1.0
 * @date 2023/2/6 11:27
 */
@Slf4j
@Component
@SuppressWarnings("all")
public class MailListener {

    @Autowired
    private EmailUtils emailUtils;

    @Autowired
    private ISendMailErrorService sendMailErrorService;;

    /**
     * 监听邮件队列发送邮件
     * @param email
     * @param channel
     * @param message
     */
    @RabbitListener(queues = "email-queue")
    public void mail(Email email, Channel channel, Message message) {
        try {
            System.out.println(email.toString());
            // 发送邮件 如果异常就持久化邮件信息
            emailUtils.send(email);
            channel.basicAck(message.getMessageProperties().getDeliveryTag(), false);
        } catch (Exception e) {
            try {
                // 发生异常持久化到数据库
                e.printStackTrace();
                SendMailError sendMailError = new SendMailError();
                sendMailError.setStatus(0); // 0 为未消费
                sendMailError.setContent(JSON.toJSONString(email));
                sendMailErrorService.save(sendMailError);
                channel.basicAck(message.getMessageProperties().getDeliveryTag(), false);
            } catch (Exception ex) {
                ex.printStackTrace();
                try {
                    // 如果持久化数据库失败就重新压入队列
                    channel.basicNack(message.getMessageProperties().getDeliveryTag(), false, true);
                } catch (IOException exc) {
                    exc.printStackTrace();
                }
            }
        }
    }

}
