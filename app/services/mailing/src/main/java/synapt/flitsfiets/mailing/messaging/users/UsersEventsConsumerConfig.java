package synapt.flitsfiets.mailing.messaging.users;

import org.springframework.amqp.core.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class UsersEventsConsumerConfig
{
    public static final String USERS_EVENTS_EXCHANGE = "ff.users.events";
    public static final String RK_USER_CREATED = "users.user.created.v1";
    //    Mailing owned queue for user created events
    public static final String Q_USER_CREATED = "ff.mailing.q.users.created";


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
}