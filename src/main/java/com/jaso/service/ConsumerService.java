package com.jaso.service;

import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.TextMessage;

/**
 * 消息消费者服务
 */
@Service
public class ConsumerService {

    @Resource(name = "jmsTemplate")
    private JmsTemplate jmsTemplate;

    public TextMessage receive(Destination destination){
        TextMessage message = (TextMessage) jmsTemplate.receive(destination);
        if (message!= null){
            try {
                System.out.println("接受到消息: "+message.getText());
            } catch (JMSException e) {
                e.printStackTrace();
            }
        }
        return message;
    }

}
