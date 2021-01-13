package com.baifukuan.activemq.config;

import org.apache.activemq.ActiveMQConnectionFactory;
import org.apache.activemq.command.ActiveMQQueue;
import org.apache.activemq.command.ActiveMQTopic;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jms.config.JmsListenerContainerFactory;
import org.springframework.jms.config.SimpleJmsListenerContainerFactory;
import org.springframework.jms.core.JmsMessagingTemplate;

import javax.jms.ConnectionFactory;
import javax.jms.Queue;
import javax.jms.Topic;

@Configuration
public class BeanConfig {
    
    @Value("${spring.activemq.broker-url}")
    private String brokerUrl;
    
    @Value("${spring.activemq.user}")
    private String username;
    
    @Value("${spring.activemq.topic-name}")
    private String password;
    
    @Value("${spring.activemq.queue-name}")
    private String queueName;
    
    @Value("${spring.activemq.topic-name}")
    private String topicName;
    
    @Bean(name = "queue")
    public Queue queue() {
        return new ActiveMQQueue(queueName);
    }
    
    @Bean(name = "topic")
    public Topic topic() {
        return new ActiveMQTopic(topicName);
    }

    /**
     * 定义ActiveMQ 连接工厂
     *
     * @return
     */
    @Bean
    public ConnectionFactory connectionFactory() {
        return new ActiveMQConnectionFactory(username, password, brokerUrl);
    }

    @Bean
    public JmsMessagingTemplate jmsMessageTemplate() {
        return new JmsMessagingTemplate(connectionFactory());
    }

    /**
     * Queue模式中，对消息的监听需要对containerFactory进行配置
     *
     * @param connectionFactory
     * @return
     */
    @Bean("queueListener")
    public JmsListenerContainerFactory<?> queueJmsListenerContainerFactory(ConnectionFactory connectionFactory) {
        SimpleJmsListenerContainerFactory factory = new SimpleJmsListenerContainerFactory();
        factory.setConnectionFactory(connectionFactory);
        // 此处禁用 topic 模式
        factory.setPubSubDomain(false);
        return factory;
    }

    /**
     * Topic模式中，对消息的监听需要对containerFactory进行配置
     *
     * @param connectionFactory
     * @return
     */
    @Bean("topicListener")
    public JmsListenerContainerFactory<?> topicJmsListenerContainerFactory(ConnectionFactory connectionFactory) {
        SimpleJmsListenerContainerFactory factory = new SimpleJmsListenerContainerFactory();
        factory.setConnectionFactory(connectionFactory);
        // 开启 topic 模式
        factory.setPubSubDomain(true);
        return factory;
    }
}
        