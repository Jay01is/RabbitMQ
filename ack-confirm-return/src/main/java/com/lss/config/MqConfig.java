package com.lss.config;

import org.springframework.amqp.core.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * ClassName: MqConfig
 *
 * @author wyk
 * @version 1.0
 * @date 2023/2/2 16:34
 */
@Configuration
public class MqConfig {

    /**
     * confirm交换机
     * @return
     */
    @Bean
    public CustomExchange confirmExchange(){
        return new CustomExchange("confirm-exchange", ExchangeTypes.TOPIC, true, false);
    }

    /**
     * return队列
     * @return
     */
    @Bean
    public Queue returnQueue(){
        return new Queue("return-queue", true, false, false);
    }

    /**
     * 绑定
     * @return
     */
    @Bean
    public Binding returnBinding(){
        return BindingBuilder.bind(returnQueue()).to(confirmExchange()).with("#.lss").noargs();
    }

}
