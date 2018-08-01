package com.topic.exchange.producer.config;

import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMQConfig {

    String queueOne="consumerQueueOne";

    String queueTwo="consumerQueueTwo";

    String exchange="trailExchangeName";

    //String routingKey="consumer*";
    //String routingKey="consumer.*";
    String routingKey="consumer";
    //String routingKey="consumer.#";
    //String routingKey="consumer.#.";

    @Bean
    Queue firstQueue() {
        return new Queue(queueOne, false);
    }

    @Bean
    Queue secondQueue() {
        return new Queue(queueTwo, false);
    }

    @Bean
    TopicExchange topicExchange() {
        return new TopicExchange(exchange);
    }

    @Bean
    Binding bindingOne(Queue firstQueue, TopicExchange exchange) {
        return BindingBuilder.bind(firstQueue).to(exchange).with(routingKey);
    }

    @Bean
    Binding bindingTwo(Queue secondQueue, TopicExchange exchange) {
        return BindingBuilder.bind(secondQueue).to(exchange).with(routingKey);
    }

    /*@Bean
    public MessageConverter jsonMessageConverter() {
        return new Jackson2JsonMessageConverter();
    }

    @Bean
    public AmqpTemplate rabbitTemplate(ConnectionFactory connectionFactory) {
        final RabbitTemplate rabbitTemplate = new RabbitTemplate(connectionFactory);
        rabbitTemplate.setMessageConverter(jsonMessageConverter());
        return rabbitTemplate;
    }*/

}
