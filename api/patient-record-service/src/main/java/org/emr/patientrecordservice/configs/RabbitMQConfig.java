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

    public static final String QUEUE_NAME = "newPatientQueue";
    public static final String EXCHANGE_NAME = "patientExchange";

    @Bean
    public Queue queue() {
        return new Queue(QUEUE_NAME, true);  // true makes the queue durable
    }

    @Bean
    public DirectExchange exchange() {
        return new DirectExchange(EXCHANGE_NAME);
    }

    @Bean
    public Binding binding(Queue queue, DirectExchange exchange) {
        return BindingBuilder.bind(queue).to(exchange).with("patient.create");
    }

    @Bean
    public MessageConverter jsonMessageConverter() {
        return new Jackson2JsonMessageConverter();
    }
}
