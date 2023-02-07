package com.lss.provider.config;

import org.springframework.amqp.core.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * ClassName: RabbitMqConfig
 * 队列
 * @author wyk
 * @version 1.0
 * @date 2023/2/1 16:46
 */
@Configuration
@SuppressWarnings("all")
public class RabbitMqConfig {

    /**
     * hello world 队列
     * @return
     */
    @Bean
    public Queue hello() {
        return new Queue(
                "hello", // 队列的名字
                true, // 是否持久化
                false, // 是否是唯我自己可见
                false // 是否自动删除
        );
    }

    /**
     * work 队列
     * @return
     */
    @Bean
    public Queue work() {
        return new Queue(
                "work", // 队列的名字
                true, // 是否持久化
                false, // 是否是唯我自己可见
                false // 是否自动删除
        );
    }

    /**
     * 广播交换机
     * @return
     */
    @Bean
    public FanoutExchange exchangeFanout() {
        return new FanoutExchange("publish",true,false);
    }

    /**
     * publish 队列
     * @return
     */
    @Bean
    public Queue publish1() {
        return new Queue(
                "publish-1", // 队列的名字
                true, // 是否持久化
                false, // 是否是唯我自己可见
                false // 是否自动删除
        );
    }

    /**
     * publish 队列
     * @return
     */
    @Bean
    public Queue publish2() {
        return new Queue(
                "publish-2", // 队列的名字
                true, // 是否持久化
                false, // 是否是唯我自己可见
                false // 是否自动删除
        );
    }

    /**
     * publish队列绑定交换机
     */
    @Bean
    public Binding publish1Binding() {
        return BindingBuilder.bind(publish1()).to(exchangeFanout());
    }
    @Bean
    public Binding publish2Binding() {
        return BindingBuilder.bind(publish2()).to(exchangeFanout());
    }

    /**
     * 路由键交换机
     * @return
     */
    @Bean
    public DirectExchange exchangeDirect() {
        return new DirectExchange("Routing",true,false);
    }

    /**
     * routing 队列
     * @return
     */
    @Bean
    public Queue routing1() {
        return new Queue(
                "routing-1", // 队列的名字
                true, // 是否持久化
                false, // 是否是唯我自己可见
                false // 是否自动删除
        );
    }

    /**
     * routing 队列
     * @return
     */
    @Bean
    public Queue routing2() {
        return new Queue(
                "routing-2", // 队列的名字
                true, // 是否持久化
                false, // 是否是唯我自己可见
                false // 是否自动删除
        );
    }

    /**
     * routing 队列绑定交换机
     */
    @Bean
    public Binding routing1Binding() {
        return BindingBuilder.bind(routing1()).to(exchangeDirect()).with("key1");
    }

    @Bean
    public Binding routing2Binding() {
        return BindingBuilder.bind(routing2()).to(exchangeDirect()).with("key2");
    }

    /**
     * topic 交换机
     * @return
     */
    @Bean
    public TopicExchange exchangeTopic() {
        return new TopicExchange("topic",true,false);
    }

    /**
     * topic 队列
     * @return
     */
    @Bean
    public Queue topic1() {
        return new Queue(
                "topic-1", // 队列的名字
                true, // 是否持久化
                false, // 是否是唯我自己可见
                false // 是否自动删除
        );
    }

    /**
     * topic 队列
     * @return
     */
    @Bean
    public Queue topic2() {
        return new Queue(
                "topic-2", // 队列的名字
                true, // 是否持久化
                false, // 是否是唯我自己可见
                false // 是否自动删除
        );
    }

    /**
     * topic 队列绑定交换机
     */
    @Bean
    public Binding topic1Binding() {
        return BindingBuilder.bind(routing1()).to(exchangeTopic()).with("#.key1");
    }

    @Bean
    public Binding topic2Binding() {
        return BindingBuilder.bind(routing2()).to(exchangeTopic()).with("key2.*");
    }

    /**
     * rcp 队列
     * @return
     */
    @Bean
    public Queue rcp1(){
        return new Queue("rcp", true, false, false);
    }

    /**
     * rcp 队列
     * @return
     */
    @Bean
    public Queue rcp2(){
        return new Queue("rcp-back", true, false, false);
    }
}
