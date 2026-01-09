package synapt.flitsfiets.mailing.messaging.appointments;

import jakarta.mail.MessagingException;
import org.modelmapper.ModelMapper;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import synapt.flitsfiets.common.dto.appointment.AppointmentDTO;
import synapt.flitsfiets.common.events.EventEnvelope;
import synapt.flitsfiets.common.events.appointments.AppointmentCreated;
import synapt.flitsfiets.mailing.service.MailingService;
import synapt.flitsfiets.mailing.service.UserTrackService;

import java.io.IOException;

@Component
public class AppointmentsEventsListener
{
    @Autowired
    MailingService mailingService;
    @Autowired
    private ModelMapper modelMapper;
    @Autowired
    private UserTrackService userTrackService;

    @RabbitListener(queues = AppointmentsEventsConsumerConfig.Q_APPOINTMENT_CREATED)
    public void onAppointmentCreated(EventEnvelope<AppointmentCreated> envelope) throws MessagingException, IOException
    {
        // TODO: idempotency (store envelope.eventID() in a table; ignore duplicates)

        AppointmentDTO appointment = envelope.data().appointment();


        System.out.println("(MAILING) Appointment received with ID: " + appointment.getId());
        mailingService.sendAppointmentConfirmation(appointment); // TOODO

    }
}
