package com.lss.consumer;

import com.lss.consumer.utils.EmailUtils;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.EnableScheduling;

/**
 * ClassName: EmailApplication
 *
 * @author wyk
 * @version 1.0
 * @date 2023/2/6 17:23
 */
@EnableScheduling
@SpringBootApplication
public class EmailApplication {

    public static void main(String[] args) {
        SpringApplication.run(EmailApplication.class, args);
    }

}
