package synapt.flitsfiets.users.messaging;

import org.springframework.amqp.core.*;
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
}
