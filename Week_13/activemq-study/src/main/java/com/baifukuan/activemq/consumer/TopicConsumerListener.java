package com.baifukuan.activemq.consumer;

import com.baifukuan.activemq.config.BeanConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.jms.core.JmsMessagingTemplate;
import org.springframework.stereotype.Component;

import javax.jms.ConnectionFactory;
import java.applet.AppletContext;

/**
 * 发布-订阅模式
 *
 */
@Component
public class TopicConsumerListener {
    @Autowired
    private JmsMessagingTemplate jmsMessagingTemplate;

    // topic模式的消费者
    @JmsListener(destination = "${spring.activemq.topic-name}", containerFactory = "topicListener")
    public void readActiveQueue1(String message) {
        ConnectionFactory connectionFactory = jmsMessagingTemplate.getConnectionFactory();
        System.out.println("消费者topic 1 接受到：" + message);
    }

    @JmsListener(destination = "${spring.activemq.topic-name}", containerFactory = "topicListener")
    public void readActiveQueue2(String message) {
        System.out.println("消费者topic 2 接受到：" + message);
    }
    @JmsListener(destination = "${spring.activemq.topic-name}", containerFactory = "topicListener")
    public void readActiveQueue3(String message) {
        System.out.println("消费者topic 3 接受到：" + message);
    }
}