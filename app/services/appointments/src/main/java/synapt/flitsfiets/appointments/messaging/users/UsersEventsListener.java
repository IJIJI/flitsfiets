package synapt.flitsfiets.appointments.messaging.users;

import org.modelmapper.ModelMapper;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import synapt.flitsfiets.appointments.model.UserTrack;
import synapt.flitsfiets.appointments.service.UserTrackService;
import synapt.flitsfiets.common.dto.user.UserExtendedDTO;
import synapt.flitsfiets.common.events.EventEnvelope;
import synapt.flitsfiets.common.events.users.UserCreated;


@Component
public class UsersEventsListener {

    @Autowired
    private ModelMapper modelMapper;
    @Autowired
    private UserTrackService userTrackService;

    @RabbitListener(queues = UsersEventsConsumerConfig.Q_USER_CREATED)
    public void onUserCreated(EventEnvelope<UserCreated> envelope)
    {
        // TODO: idempotency (store envelope.eventID() in a table; ignore duplicates)

        UserExtendedDTO userIn = envelope.data().user();

        UserTrack newUser = modelMapper.map(userIn, UserTrack.class);
        userTrackService.addUser(newUser);


        System.out.println("(APPOINTMENTS) User created with ID: " + envelope.data().user().getId());

    }
}
