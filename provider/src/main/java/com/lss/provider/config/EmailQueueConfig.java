package com.lss.provider.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * ClassName: EmailQueueConfig
 *
 * @author wyk
 * @version 1.0
 * @date 2023/2/3 20:01
 */
@Configuration
public class EmailQueueConfig {

    @Bean
    public DirectExchange emailExchange() {
        return new DirectExchange("email-exchange", true, false);
    }

    @Bean
    public Queue email() {
        return new Queue("email-queue", true);
    }

    @Bean
    public Queue msg() {
        return new Queue("msg-queue", true);
    }

    @Bean
    public Binding emailBinding() {
        return BindingBuilder.bind(email()).to(emailExchange()).with("email");
    }

    @Bean
    public Binding msgBinding() {
        return BindingBuilder.bind(msg()).to(emailExchange()).with("msg");
    }

}
