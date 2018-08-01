package com.topic.exchange.producer.delegate;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageBuilder;
import org.springframework.amqp.core.MessageProperties;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProducerDelegate {

    private static final Logger LOGGER = LoggerFactory.getLogger(ProducerDelegate.class);

    //String routingKey="consumer*";
    //String routingKey="consumer.*";
    String routingKey="consumer.sample.data";
    //String routingKey="consumer.#";
    //String routingKey="consumer.#.";

    String exchangeName="trailExchangeName";

    @Autowired
    private RabbitTemplate rabbitTemplate;

    private final ObjectMapper objectMapper = new ObjectMapper();


    public boolean sendMessage(String msg) {
        try {
            Message message = MessageBuilder.withBody(objectMapper.writeValueAsBytes(msg)).setContentType(MessageProperties.CONTENT_TYPE_JSON).build();
            rabbitTemplate.convertAndSend(exchangeName, routingKey, message);
            LOGGER.info("Inside ProducerDelegate::sendMessage:::after sending rabbitmq message");
            return true;
        } catch (Exception ex) {
            LOGGER.error("Unable to serialize product entry", ex);
            return false;
        }

    }
}
