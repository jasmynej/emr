package org.emr.billingservice.configs;

import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.context.annotation.Bean;

import org.springframework.amqp.core.*;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMQConfig {

    @Bean
    public Queue notificationQueue() {
        return new Queue("notificationQueue");
    }

    @Bean
    public Queue invoiceCreationQueue() {
        return new Queue("invoiceCreationQueue", true);
    }

    @Bean
    public DirectExchange exchange() {
        return new DirectExchange("patientExchange");
    }

    @Bean
    public MessageConverter jsonMessageConverter() {
        return new Jackson2JsonMessageConverter();
    }

    @Bean
    public Binding notificationBinding(Queue notificationQueue, DirectExchange exchange) {
        return BindingBuilder.bind(notificationQueue).to(exchange).with("patient.notification");
    }
    @Bean
    public Binding invoiceCreationQueueBinding(Queue invoiceCreationQueue, DirectExchange exchange) {
        return BindingBuilder.bind(invoiceCreationQueue).to(exchange).with("patient.invoice");
    }

}
