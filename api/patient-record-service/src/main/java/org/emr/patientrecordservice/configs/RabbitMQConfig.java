package org.emr.patientrecordservice.configs;

import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.core.*;
import org.springframework.amqp.support.converter.MessageConverter;
@Configuration
@EnableRabbit
public class RabbitMQConfig {



    @Bean
    public Queue newPatientQueue() {
        return new Queue("newPatientQueue", true);  // true makes the queue durable
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
    public Binding newPatientQueueBinding(Queue newPatientQueue, DirectExchange exchange) {
        return BindingBuilder.bind(newPatientQueue).to(exchange).with("patient.create");
    }

    @Bean
    public Binding invoiceCreationQueueBinding(Queue invoiceCreationQueue, DirectExchange exchange) {
        return BindingBuilder.bind(invoiceCreationQueue).to(exchange).with("patient.invoice");
    }
    @Bean
    public MessageConverter jsonMessageConverter() {
        return new Jackson2JsonMessageConverter();
    }
}
