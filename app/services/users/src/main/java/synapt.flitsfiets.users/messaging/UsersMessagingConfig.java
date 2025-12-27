package synapt.flitsfiets.users.messaging;

import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.config.SimpleRabbitListenerContainerFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.DefaultClassMapper;
import org.springframework.amqp.support.converter.JacksonJsonMessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class UsersMessagingConfig {

    public static final String USERS_EVENTS_EXCHANGE = "ff.users.events";
    public static final String RK_USER_CREATED = "users.user.created.v1";

    @Bean
    public TopicExchange usersEventsExchange() {
        return ExchangeBuilder.topicExchange(USERS_EVENTS_EXCHANGE).durable(true).build();
    }

    @Bean
    public JacksonJsonMessageConverter jacksonJsonMessageConverter() {
        var converter = new JacksonJsonMessageConverter();

        var classMapper = new DefaultClassMapper();
        classMapper.setTrustedPackages(
                "synapt.flitsfiets.common.events",
                "synapt.flitsfiets.common.events.users"
        );

        converter.setClassMapper(classMapper);
        return converter;
    }

    @Bean
    public RabbitTemplate rabbitTemplate(ConnectionFactory cf, JacksonJsonMessageConverter c) {
        var template = new RabbitTemplate(cf);
        template.setMessageConverter(c);
        return template;
    }

    @Bean
    public SimpleRabbitListenerContainerFactory rabbitListenerContainerFactory(
            ConnectionFactory cf,
            JacksonJsonMessageConverter c
    ) {
        var factory = new SimpleRabbitListenerContainerFactory();
        factory.setConnectionFactory(cf);
        factory.setMessageConverter(c);
        return factory;
    }
}
