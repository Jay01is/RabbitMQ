package com.lss.provider.controller;

import com.lss.entity.Email;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * ClassName: SendMailController
 *
 * @author wyk
 * @version 1.0
 * @date 2023/2/3 19:21
 */
@Slf4j
@RestController
public class SendMailController {

    @Autowired
    private RabbitTemplate rabbitTemplate;

    /**
     * 发送邮件
     */
    @GetMapping("/sendMail")
    public void sendMail(){
        rabbitTemplate.convertAndSend("email-exchange","email", setEmail());
    }

    /**
     * 封装邮件信息
     * @return
     */
    public  Email setEmail() {
        // 设置邮箱工具类
        String to = "2370908537@qq.com";
        String title = "Lss";
        String content = "WykLssZZ";
        String from = "Jay0l1s0214@163.com";
        return new Email(from,to,title, content);
    }

}
