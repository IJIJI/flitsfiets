package synapt.flitsfiets.users.messaging;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Component;
import synapt.flitsfiets.common.events.EventEnvelope;
import synapt.flitsfiets.common.events.users.UserCreated;

import java.time.Instant;
import java.util.UUID;

@Component
public class UsersEventPublisher {

    private final RabbitTemplate rabbitTemplate;

    public UsersEventPublisher(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    public void publishUserCreated(UserCreated event) {
        var envelope = new EventEnvelope<>(
                UUID.randomUUID(),
                Instant.now(),
                UsersMessagingConfig.RK_USER_CREATED,
                event
        );

        rabbitTemplate.convertAndSend(
                UsersMessagingConfig.USERS_EVENTS_EXCHANGE,
                UsersMessagingConfig.RK_USER_CREATED,
                envelope
        );
    }
}