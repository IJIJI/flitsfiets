package synapt.flitsfiets.mailing.messaging.users;

import jakarta.mail.MessagingException;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import synapt.flitsfiets.common.events.EventEnvelope;
import synapt.flitsfiets.common.events.users.UserCreated;
import synapt.flitsfiets.mailing.service.MailingService;

import java.io.IOException;

@Component
public class UsersEventsListener {

    @Autowired
    MailingService mailingService;

    @RabbitListener(queues = UsersEventsConsumerConfig.Q_USER_CREATED)
    public void onUserCreated(EventEnvelope<UserCreated> envelope) throws MessagingException, IOException
    {
        // TODO: idempotency (store envelope.eventID() in a table; ignore duplicates)
        System.out.println("(MAILING) User created with ID: " + envelope.data().user().getId());
        mailingService.sendRegistrationConfirmation(envelope.data().user(), "AAAA");

    }
}
