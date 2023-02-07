package com.lss.consumer.scheduled;

import com.alibaba.fastjson2.JSON;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.lss.consumer.entity.SendMailError;
import com.lss.consumer.mapper.SendMailErrorMapper;
import com.lss.consumer.utils.EmailUtils;
import com.lss.entity.Email;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * ClassName: ErrorSendMail
 *
 * @author wyk
 * @version 1.0
 * @date 2023/2/6 19:41
 */
@Slf4j
@Component
public class ErrorSendMail {

    @Autowired
    private SendMailErrorMapper mapper;

    @Autowired
    private EmailUtils emailUtils;

    /**
     * 定时任务 10秒跑一次把未发成功的邮件重新发送
     */
    @Scheduled(cron = "10 * * * * *")
    public void sendErrorMail() {
        log.info("定时任务启动!!!!!");
        List<SendMailError> sendMailErrors = mapper.selectList(
                new LambdaQueryWrapper<SendMailError>().eq(SendMailError::getStatus, 0));

        for (SendMailError sendMailError : sendMailErrors) {
            try {
                emailUtils.send(JSON.parseObject(sendMailError.getContent(), Email.class));
                // 发送成功修改为1 如果异常就不会修改 继续下一次定时任务中
                mapper.update(
                        sendMailError,
                        new LambdaUpdateWrapper<SendMailError>().set(SendMailError::getStatus, 1));
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

}
