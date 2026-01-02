package synapt.flitsfiets.appointments.messaging.users;

import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.config.SimpleRabbitListenerContainerFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.DefaultJacksonJavaTypeMapper;
import org.springframework.amqp.support.converter.JacksonJavaTypeMapper;
import org.springframework.amqp.support.converter.JacksonJsonMessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class UsersEventsConsumerConfig
{


    public static final String USERS_EVENTS_EXCHANGE = "ff.users.events";
    public static final String RK_USER_CREATED = "users.user.created.v1";
    //    Mailing owned queue for user created events
    public static final String Q_USER_CREATED = "ff.appointments.q.users.created";


    @Bean
    public TopicExchange usersEventsExchange()
    {
        return ExchangeBuilder.topicExchange(USERS_EVENTS_EXCHANGE).durable(true).build();
    }

    @Bean
    public Queue usersCreatedQueue()
    {
        return QueueBuilder.durable(Q_USER_CREATED).build();
    }

    @Bean
    public Binding bindUsersCreated(Queue mailingUsersCreatedQueue, TopicExchange usersEventsExchange)
    {
        return BindingBuilder
                .bind(mailingUsersCreatedQueue)
                .to(usersEventsExchange)
                .with(RK_USER_CREATED);
    }

    @Bean
    public JacksonJsonMessageConverter jacksonJsonMessageConverter()
    {
        var converter = new JacksonJsonMessageConverter();

        // Security: only allow your packages for deserialization
//        var classMapper = new DefaultClassMapper();
//        classMapper.setTrustedPackages(
//                "synapt.flitsfiets.common.events",
//                "synapt.flitsfiets.common.events.users",
//                "synapt.flitsfiets.common.dto"
//        );
//        converter.setClassMapper(classMapper);

        var typeMapper = new DefaultJacksonJavaTypeMapper();
        typeMapper.setTrustedPackages(
                "synapt.flitsfiets.common.events",
                "synapt.flitsfiets.common.events.users",
                "synapt.flitsfiets.common.dto"
        );

        // Use the @RabbitListener method signature (EventEnvelope<UserCreated>) as the target type
        typeMapper.setTypePrecedence(JacksonJavaTypeMapper.TypePrecedence.INFERRED);
        converter.setJavaTypeMapper(typeMapper);

        return converter;
    }

    @Bean
    public SimpleRabbitListenerContainerFactory rabbitListenerContainerFactory(
            ConnectionFactory connectionFactory,
            JacksonJsonMessageConverter converter
    )
    {
        var factory = new SimpleRabbitListenerContainerFactory();
        factory.setConnectionFactory(connectionFactory);
        factory.setMessageConverter(converter);

        // sensible defaults you can tune later
        factory.setDefaultRequeueRejected(false); // don’t infinite-loop poison messages

        return factory;
    }

    // Optional: only needed if THIS service also publishes
    @Bean
    public RabbitTemplate rabbitTemplate(ConnectionFactory cf, JacksonJsonMessageConverter converter)
    {
        var template = new RabbitTemplate(cf);
        template.setMessageConverter(converter);
        return template;
    }
}