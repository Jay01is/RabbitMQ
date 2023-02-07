package com.lss.consumer.utils;

import com.lss.entity.Email;
import com.sun.xml.internal.messaging.saaj.packaging.mime.MessagingException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;

import javax.mail.internet.MimeMessage;
import java.util.concurrent.ThreadPoolExecutor;

/**
 * ClassName: EmailUtils
 *
 * @author wyk
 * @version 1.0
 * @date 2023/2/3 19:39
 */
@Slf4j
@Component
@SuppressWarnings("all")
public class EmailUtils {

    @Autowired
    private JavaMailSender javaMailSender;

    public static Email setEmail() {
        // 设置邮箱工具类
        String to = "2370908537@qq.com";
        String title = "Lss";
        String content = "WykLssZZ";
        String from = "Jay0l1s0214@163.com";
        return new Email(to, title, content, from);
    }

    public void send(Email email){
        // 1.创建一个发送邮件的对象 MimeMessage
        MimeMessage mimeMessage = javaMailSender.createMimeMessage();
        try {
            // 2.创建一个邮件发送的帮助类
            //把邮件的数据封装到一个对象中
            MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage,true);
            mimeMessageHelper.setSubject(email.getTitle()); // 这是设置邮件的标题
            mimeMessageHelper.setText(email.getContent(),true); // 设置邮件的内容,true是代表支持html格式
            mimeMessageHelper.setTo(email.getTo()); // 设置收件人
            mimeMessageHelper.setFrom(email.from); // 设置发件人
        } catch (Exception e) {
            // 失败
            e.printStackTrace();
            log.error("邮箱发送失败!");
        }
        // 3.发送
        // 把封装好的信息发送出去
        javaMailSender.send(mimeMessage);
    }

}
