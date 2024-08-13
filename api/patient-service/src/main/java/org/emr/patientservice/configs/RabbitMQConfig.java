package org.emr.patientservice.configs;

import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.amqp.core.*;
import org.springframework.amqp.support.converter.MessageConverter;


@Configuration
public class RabbitMQConfig {


    @Bean
    public Queue newPatientQueue() {
        return new Queue("newPatientQueue");
    }

    @Bean
    public Queue notificationQueue(){
        return new Queue("notificationQueue");
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
    public Binding binding(Queue newPatientQueue, DirectExchange exchange) {
        return BindingBuilder.bind(newPatientQueue).to(exchange).with("patient.create");
    }

    @Bean
    public Binding notificationBinding(Queue notificationQueue, DirectExchange exchange) {
        return BindingBuilder.bind(notificationQueue).to(exchange).with("patient.notification");
    }



    @Bean
    public RabbitTemplate rabbitTemplate(ConnectionFactory connectionFactory) {
        RabbitTemplate rabbitTemplate = new RabbitTemplate(connectionFactory);
        rabbitTemplate.setMessageConverter(jsonMessageConverter());
        return rabbitTemplate;
    }
}
