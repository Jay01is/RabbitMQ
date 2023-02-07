package com.lss;

import org.junit.jupiter.api.Test;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.IOException;

/**
 * ClassName: DemoTest
 *
 * @author wyk
 * @version 1.0
 * @date 2023/2/2 17:05
 */
@SpringBootTest
public class DemoTest {

    @Autowired
    private RabbitTemplate rabbitTemplate;

    /**
     * 发送消息
     * @throws IOException
     */
    @Test
    public void sendTest() throws IOException {
        rabbitTemplate.convertAndSend("confirm-exchange","wyk.lss","6666");
        System.in.read();
    }

}
