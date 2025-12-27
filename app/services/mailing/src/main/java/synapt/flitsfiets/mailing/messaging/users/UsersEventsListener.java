package synapt.flitsfiets.mailing.messaging.users;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;
import synapt.flitsfiets.common.events.EventEnvelope;
import synapt.flitsfiets.common.events.users.UserCreated;

@Component
public class UsersEventsListener {

    @RabbitListener(queues = UsersEventsConsumerConfig.Q_USER_CREATED)
    public void onUserCreated(EventEnvelope<UserCreated> envelope) {
        // TODO: idempotency (store envelope.eventID() in a table; ignore duplicates)
        System.out.println("(MAILING) User created with ID: " + envelope.data().user().getId());
    }
}
