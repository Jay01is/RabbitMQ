package com.lss.provider;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

/**
 * ClassName: ProviderApplication
 *
 * @author wyk
 * @version 1.0
 * @date 2023/2/1 16:44
 */
@SpringBootApplication
public class ProviderApplication {

    public static void main(String[] args) {
        SpringApplication.run(ProviderApplication.class, args);
    }

    /**
     * 防止消息重复消费
     */

    @Bean
    public TopicExchange topicExchange() {
        return new TopicExchange("repeat-exchange", true, false);
    }

    @Bean
    public Queue queue() {
        return new Queue("repeat-queue", true, false, false);
    }

    @Bean
    public Binding binding() {
        return BindingBuilder.bind(queue()).to(topicExchange()).with("#.lss");
    }
}
